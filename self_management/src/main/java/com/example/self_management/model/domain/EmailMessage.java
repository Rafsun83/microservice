package com.example.self_management.model.domain;

import com.example.self_management.enums.email.EmailType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailMessage implements Serializable {
    private EmailType type;            // ← which email to send
    private String userEmail;
    private String userName;

    // Transaction-specific fields (nullable for non-transaction emails)
    private BigDecimal amount;
    private BigDecimal newBalance;
    private String transactionId;

    // Generic fields (used by periodic update, welcome, etc.)
    private String subject;
    private String body;
}
