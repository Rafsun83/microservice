package com.example.self_management.controller;

import com.example.self_management.model.dto.user.AuthenticatedUser;
import com.example.self_management.payload.ApiResponse;
import com.example.self_management.persistence.entity.NotificationEntity;
import com.example.self_management.persistence.repository.NotificationRepository;
import com.example.self_management.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationRepository notificationRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<List<NotificationEntity>>> getMyNotifications() {
        AuthenticatedUser user = SecurityUtils.getCurrentUser();
        List<NotificationEntity> notifications = notificationRepository.findByUserIdOrderByCreatedAtDesc(user.userId());
        ApiResponse<List<NotificationEntity>> response = new ApiResponse<>(true, "Notification fetched successfully!!", notifications, HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/unread-count")
    //@AuthenticatedUser user = SecurityUtils.getCurrentUser();
    public ResponseEntity<ApiResponse<List<NotificationEntity>>> unreadCount() {
        AuthenticatedUser user = SecurityUtils.getCurrentUser();
        List<NotificationEntity> notifications = notificationRepository.findByUserIdAndIsReadFalse(user.userId());
        ApiResponse<List<NotificationEntity>> response = new ApiResponse<>(true, "Unread Notification fetched successfully!!", notifications, HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
        notificationRepository.findById(id).ifPresent(n -> {
            n.setRead(true);
            notificationRepository.save(n);
        });
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/allread")
    public ResponseEntity<Void> markAllRead(@PathVariable Long id){
        List<NotificationEntity> notifications = notificationRepository.findAllByUserId(id);
        System.out.println("notifications" + notifications);
        notifications.forEach(notification -> notification.setRead(true));
        notificationRepository.saveAll(notifications);
        return ResponseEntity.ok().build();
    }
}
