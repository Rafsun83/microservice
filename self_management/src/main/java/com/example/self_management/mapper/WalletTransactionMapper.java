package com.example.self_management.mapper;

import com.example.self_management.model.domain.WalletTransaction;
import com.example.self_management.model.dto.walletTransaction.CreateWalletTransactionRequest;
import com.example.self_management.persistence.entity.WalletTransactionEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class WalletTransactionMapper {

    public WalletTransaction entityToWalletTransactionDomain(WalletTransactionEntity walletTransactionEntity) {
        WalletTransaction walletTransaction = new WalletTransaction();
        BeanUtils.copyProperties(walletTransactionEntity, walletTransaction);
        return walletTransaction;


    }

    public WalletTransactionEntity createWalletTransactionToEntity(CreateWalletTransactionRequest createWalletTransactionRequest) {
        WalletTransactionEntity walletTransactionEntity = new WalletTransactionEntity();
        walletTransactionEntity.setCreatedAt(LocalDateTime.now());
//        walletTransactionEntity.setWalletEntity();
        BeanUtils.copyProperties(createWalletTransactionRequest, walletTransactionEntity);
        return walletTransactionEntity;

    }
}
