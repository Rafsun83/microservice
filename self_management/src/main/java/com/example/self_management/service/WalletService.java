package com.example.self_management.service;


import com.example.self_management.exception.ResourceNotFoundException;
import com.example.self_management.mapper.WalletMapper;
import com.example.self_management.model.domain.Wallet;
import com.example.self_management.model.dto.user.AuthenticatedUser;
import com.example.self_management.model.dto.wallet.CreateWalletRequest;
import com.example.self_management.model.dto.wallet.UpdateWalletRequest;
import com.example.self_management.persistence.entity.WalletEntity;
import com.example.self_management.persistence.repository.WalletRepository;
import com.example.self_management.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final WalletMapper  walletMapper;


    public WalletService(WalletRepository walletRepository, WalletMapper walletMapper) {
        this.walletRepository = walletRepository;
        this.walletMapper = walletMapper;
    }



    public List<Wallet> getAllWallet() {
        //Removed pageable from getWallet params because I used custom query
        AuthenticatedUser user = SecurityUtils.getCurrentUser();
        Long userId = user.userId();
        List<WalletEntity>  walletEntityList = walletRepository.findByUserId(userId);
        if (walletEntityList.isEmpty()) {
            throw new ResourceNotFoundException("No wallet found");
        }
        return walletEntityList.stream().map(walletMapper :: entityToWalletDomain).toList();
    }

    public Wallet getWalletById(Long id) {
        AuthenticatedUser user = SecurityUtils.getCurrentUser();
        Long userId = user.userId();
        WalletEntity walletEntity = walletRepository.findByIdAndUserId(id, userId).orElseThrow(() -> new ResourceNotFoundException("No wallet found for this user"));
        return walletMapper.entityToWalletDomain(walletEntity);
    }

    public Wallet addWallet(CreateWalletRequest createWalletRequest) {
        AuthenticatedUser user = SecurityUtils.getCurrentUser();
        Long userId = user.userId();
        var saveWallet = walletMapper.createWalletRequestToEntity(createWalletRequest, userId);
        var saveWalletEntity = walletRepository.save(saveWallet);
        return walletMapper.entityToWalletDomain(saveWalletEntity);
    }

    public void updateWallet(Long id, UpdateWalletRequest updateWalletRequest) {
        AuthenticatedUser user = SecurityUtils.getCurrentUser();
        Long userId = user.userId();
        var walletEntity = walletRepository.findByIdAndUserId(id, userId).get();
        var updateWallet = walletMapper.updateWalletRequestToEntity(updateWalletRequest, walletEntity);
        walletRepository.save(updateWallet);
    }

    public void deleteWallet(Long id) {
        AuthenticatedUser user = SecurityUtils.getCurrentUser();
        Long userId = user.userId();
        WalletEntity wallet = walletRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found for this user"));
        walletRepository.delete(wallet);
    }

}
