package com.example.self_management.event.email;
import com.example.self_management.config.RabbitMQConfig;
import com.example.self_management.model.domain.MoneyAddedMessage;
import com.example.self_management.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MoneyAddedEventListener {

    private final MailService mailService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)   // ← replaced @EventListener
    public void handleMoneyAdded(MoneyAddedMessage message) {
        try {
            log.info("Received RabbitMQ message for: {}", message.getUserEmail());

            mailService.sendMoneyAddedEmail(
                    message.getUserEmail(),
                    message.getUserName(),
                    message.getAmount(),
                    message.getNewBalance(),
                    message.getTransactionId()
            );

        } catch (Exception ex) {
            log.error("Failed to send email to {}: {}", message.getUserEmail(), ex.getMessage());
        }
    }
}