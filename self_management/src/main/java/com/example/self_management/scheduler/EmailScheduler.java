package com.example.self_management.scheduler;


import com.example.self_management.config.RabbitMQConfig;
import com.example.self_management.enums.email.EmailType;
import com.example.self_management.model.domain.EmailMessage;
import com.example.self_management.persistence.entity.UserEntity;
import com.example.self_management.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailScheduler {

    private final UserRepository userRepository;
    private final RabbitTemplate rabbitTemplate;


    // Runs every 5 minutes 0 */5 * * * *
    // First day at every month at midnight
    @Scheduled(cron = "0 0 0 1 * *", zone = "Asia/Dhaka")
    public void sendPeriodicEmails() {
        log.info("Starting scheduled email job at {}", LocalDateTime.now());

        List<UserEntity> users = userRepository.findAll();

        userRepository.findAll().forEach(user -> {
            EmailMessage message = EmailMessage.builder()
                    .type(EmailType.PERIODIC_UPDATE)   // ← key part
                    .userEmail(user.getEmail())
                    .userName(user.getName())
                    .build();

            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.EMAIL_EXCHANGE,      // your exchange name
                    RabbitMQConfig.EMAIL_ROUTING_KEY,   // your routing key
                    message
            );
        });


        log.info("Queued {} emails for delivery", users.size());
    }
}
