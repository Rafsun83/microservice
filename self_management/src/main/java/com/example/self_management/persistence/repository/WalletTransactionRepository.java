package com.example.self_management.persistence.repository;

import com.example.self_management.persistence.entity.WalletTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletTransactionRepository extends JpaRepository<WalletTransactionEntity, Long> {
    List<WalletTransactionEntity> findByWalletEntityId (Long walletId);
}
