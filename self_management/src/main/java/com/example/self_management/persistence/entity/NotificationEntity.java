package com.example.self_management.persistence.entity;

import com.example.self_management.enums.notification.NotificationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String title;

    @Column(length = 1000)
    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private boolean isRead;
    private LocalDateTime createdAt;


    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.isRead = false;
    }

}
