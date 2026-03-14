package com.example.self_management.mapper;


import com.example.self_management.model.domain.Wallet;
import com.example.self_management.model.dto.wallet.CreateWalletRequest;
import com.example.self_management.model.dto.wallet.UpdateWalletRequest;
import com.example.self_management.persistence.entity.WalletEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class WalletMapper {

    public Wallet entityToWalletDomain(WalletEntity walletEntity) {
        Wallet wallet = new Wallet();
        BeanUtils.copyProperties(walletEntity,wallet);
        return wallet;
    }

    public WalletEntity createWalletRequestToEntity(CreateWalletRequest createWalletRequest, Long userId) {
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setCreatedAt(new Date());
        walletEntity.setUserId(userId);
        BeanUtils.copyProperties(createWalletRequest,walletEntity);
        return walletEntity;
    }

    public WalletEntity updateWalletRequestToEntity(UpdateWalletRequest updateWalletRequest, WalletEntity walletEntity) {
        walletEntity.setTotalAmount(updateWalletRequest.totalAmount());
        walletEntity.setCategory(updateWalletRequest.category());
        walletEntity.setType(updateWalletRequest.type());
        walletEntity.setUpdatedAt(new Date());
        return walletEntity;
    }
}
