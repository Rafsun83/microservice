package com.example.self_management.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoneyAddedMessage {
    private String userEmail;
    private String userName;
    private BigDecimal amount;
    private BigDecimal newBalance;
    private String transactionId;
}
