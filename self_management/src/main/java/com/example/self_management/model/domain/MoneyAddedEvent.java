package com.example.self_management.model.domain;


import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.math.BigDecimal;

@Getter
public class MoneyAddedEvent extends ApplicationEvent {
    private final String userEmail;
    private final String userName;
    private final BigDecimal amount;
    private final BigDecimal newBalance;
    private final String transactionId;

    public MoneyAddedEvent(Object source, String userEmail, String userName, BigDecimal amount, BigDecimal newBalance, String transactionId) {
        super(source);
        this.userEmail = userEmail;
        this.userName = userName;
        this.amount = amount;
        this.newBalance = newBalance;
        this.transactionId = transactionId;
    }
}
