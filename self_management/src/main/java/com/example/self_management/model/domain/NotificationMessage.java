package com.example.self_management.model.domain;

import com.example.self_management.enums.notification.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationMessage implements Serializable {
    private Long userId;
    private String userEmail;
    private NotificationType notificationType; // DEPOSIT, WITHDRAW, TRANSFER
    private BigDecimal amount;
    private BigDecimal balanceAfter;
    private String referenceId;
    private LocalDateTime timestamp;
}
