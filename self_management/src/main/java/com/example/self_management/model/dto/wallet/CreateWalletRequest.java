package com.example.self_management.model.dto.wallet;


import com.example.self_management.enums.wallets.WalletType;
import java.math.BigDecimal;

public record CreateWalletRequest(WalletType type, BigDecimal totalAmount, String category, String walletName, String note) {
}
