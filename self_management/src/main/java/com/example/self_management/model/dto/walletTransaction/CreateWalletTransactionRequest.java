package com.example.self_management.model.dto.walletTransaction;

import com.example.self_management.enums.walletsTransaction.TransactionType;

import java.math.BigDecimal;

public record CreateWalletTransactionRequest(BigDecimal amount, TransactionType type, String category, String note) {
}
