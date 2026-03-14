package com.example.self_management.model.domain;

import com.example.self_management.enums.walletsTransaction.TransactionType;
import com.example.self_management.persistence.entity.WalletEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WalletTransaction {
    private Long id;
    private BigDecimal amount;
    private TransactionType type;
    private String category;
    private String note;
    private LocalDateTime createdAt;

    public WalletTransaction() {}

    public WalletTransaction(BigDecimal amount, TransactionType type, String category, String note, WalletEntity walletEntity) {
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.note = note;
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
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
