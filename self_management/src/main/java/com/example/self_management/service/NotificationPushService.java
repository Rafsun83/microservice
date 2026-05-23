package com.example.self_management.service;


import com.example.self_management.persistence.entity.NotificationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationPushService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public void pushToUser(Long userId, NotificationEntity notificationEntity){
        // ★ Add this log ★
        String target = userId.toString();
        System.out.println("🎯 Push target: '" + target
                + "' | length=" + target.length()
                + " | bytes=" + java.util.Arrays.toString(target.getBytes()));
        simpMessagingTemplate.convertAndSendToUser(
                userId.toString(),
                "/queue/notifications",
                notificationEntity
        );
        System.out.println("Completed push");
    }
}
