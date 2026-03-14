package com.example.self_management.persistence.entity;

import com.example.self_management.enums.walletsTransaction.TransactionType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "wallet_transaction")
public class WalletTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private String category;
    private String note;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "wallet_id", referencedColumnName = "id")
    private WalletEntity walletEntity;

    public WalletTransactionEntity() {}

    public WalletTransactionEntity(BigDecimal amount, TransactionType type, String category, String note, WalletEntity walletEntity) {
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.note = note;
        this.walletEntity = walletEntity;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public TransactionType getType() {
        return type;
    }
    public void setType(TransactionType type) {
        this.type = type;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public WalletEntity getWalletEntity() {
        return walletEntity;
    }
    public void setWalletEntity(WalletEntity walletEntity) {
        this.walletEntity = walletEntity;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
