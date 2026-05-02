package com.example.self_management.service;

import com.example.self_management.config.RabbitMQConfig;
import com.example.self_management.enums.walletsTransaction.TransactionType;
import com.example.self_management.mapper.WalletTransactionMapper;
import com.example.self_management.model.domain.MoneyAddedMessage;
import com.example.self_management.model.domain.WalletTransaction;
import com.example.self_management.model.dto.user.AuthenticatedUser;
import com.example.self_management.model.dto.walletTransaction.CreateWalletTransactionRequest;
import com.example.self_management.persistence.entity.WalletEntity;
import com.example.self_management.persistence.entity.WalletTransactionEntity;
import com.example.self_management.persistence.repository.WalletRepository;
import com.example.self_management.persistence.repository.WalletTransactionRepository;
import com.example.self_management.utils.SecurityUtils;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class WalletTransactionService {
    private final WalletTransactionRepository walletTransactionRepository;
    private final WalletTransactionMapper walletTransactionMapper;
    private final WalletRepository walletRepository;
    private final RabbitTemplate rabbitTemplate;


    public WalletTransactionService(WalletTransactionRepository walletTransactionRepository, WalletTransactionMapper walletTransactionMapper, WalletRepository walletRepository,  RabbitTemplate rabbitTemplate) {
        this.walletTransactionRepository = walletTransactionRepository;
        this.walletTransactionMapper = walletTransactionMapper;
        this.walletRepository = walletRepository;
        this.rabbitTemplate = rabbitTemplate;

    }

    public List<WalletTransaction> getAllWalletTransaction(Long walletId) {
        List<WalletTransactionEntity> allTransactionList = walletTransactionRepository.findByWalletEntityId(walletId);
        return allTransactionList.stream().map(walletTransactionMapper :: entityToWalletTransactionDomain).toList();
    }

    @Transactional
    public WalletTransaction createWalletTransaction(Long walletId, CreateWalletTransactionRequest createWalletTransactionRequest){



        // 1️. Find wallet
        WalletEntity wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
        //Convert request entity
       var transaction = walletTransactionMapper.createWalletTransactionToEntity(createWalletTransactionRequest);
       transaction.setWalletEntity(wallet);

       //Update wallet balance
        BigDecimal currentBalance = wallet.getTotalAmount();
        BigDecimal transactionAmount = transaction.getAmount();

        if(transaction.getType() == TransactionType.DEBIT ){
            wallet.setTotalAmount(currentBalance.subtract(transactionAmount));
        }
        else if(transaction.getType() == TransactionType.CREDIT ){
            wallet.setTotalAmount(currentBalance.add(transactionAmount));
        }
        //Save wallet with updated balance
        walletRepository.save(wallet);
        //Create and Save Transaction
       var saveTransaction  = walletTransactionRepository.save(transaction);

        // ─── Publish to RabbitMQ ───────────────────────────────
        AuthenticatedUser user = SecurityUtils.getCurrentUser();
        String txn = generateTransactionId();

        MoneyAddedMessage message = new MoneyAddedMessage(
                user.email(),
                user.name(),
                createWalletTransactionRequest.amount(),
                wallet.getTotalAmount(),
                txn
        );

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,      // exchange
                RabbitMQConfig.ROUTING_KEY,   // routing key
                message                       // payload (auto serialized to JSON)
        );

       return walletTransactionMapper.entityToWalletTransactionDomain(saveTransaction);
    }


    // ─── Private Helper Methods ───────────────────────────────

    private String generateTransactionId() {
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String random = String.valueOf((int)(Math.random() * 900000) + 100000);
        return "TXN-" + timestamp + "-" + random;
    }
}
