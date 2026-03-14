package com.example.self_management.persistence.repository;

import com.example.self_management.model.domain.Wallet;
import com.example.self_management.persistence.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity,Long> {
    List<WalletEntity> findByUserId(Long userId);
    Optional<WalletEntity> findByIdAndUserId(Long id, Long userId);
}
