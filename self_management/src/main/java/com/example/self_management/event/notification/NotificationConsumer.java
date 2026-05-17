package com.example.self_management.event.notification;

import com.example.self_management.config.RabbitMQConfig;
import com.example.self_management.enums.notification.NotificationType;
import com.example.self_management.model.domain.NotificationMessage;
import com.example.self_management.persistence.entity.NotificationEntity;
import com.example.self_management.persistence.repository.NotificationRepository;
import com.example.self_management.service.NotificationPushService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;
    private final NotificationPushService notificationPushService;

    @RabbitListener(queues = RabbitMQConfig.NOTIFICATION_QUEUE)
    public void handleNotificationEvent(NotificationMessage message) {
        log.info("Received email task: type={}, to={}", message.getNotificationType(), message.getUserEmail());

        try {
            String title = buildTitle(message);
            String description = buildMessage(message);

            NotificationEntity notification = NotificationEntity.builder()
                    .userId(message.getUserId())
                    .title(title)
                    .message(description)
                    .type(NotificationType.TRANSACTION)
                    .build();

            NotificationEntity saved = notificationRepository.save(notification);
            log.info("Notification saved for user {}: {}", message.getUserId(), saved);

            // Push real-time to logged-in users
            notificationPushService.pushToUser(message.getUserId(), saved);

        } catch (Exception e) {
            log.error("Failed to process notification event", e);
            throw new AmqpRejectAndDontRequeueException("Notification failed", e);
        }


    }

    private String buildTitle(NotificationMessage e) {
        return switch (e.getNotificationType()) {
            case TRANSACTION -> "Money Added";
            default -> "Transaction Alert";
        };
    }

    private String buildMessage(NotificationMessage e) {
        return String.format("%s of ৳%s completed. New balance: ৳%s",
                e.getNotificationType(), e.getAmount(), e.getBalanceAfter());
    }

}
