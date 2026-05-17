package com.example.self_management.event.email;
import com.example.self_management.config.RabbitMQConfig;
import com.example.self_management.model.domain.EmailMessage;
import com.example.self_management.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailConsumer {

    private final MailService mailService;

    @RabbitListener(queues = RabbitMQConfig.EMAIL_QUEUE)
    public void consume(EmailMessage message) {
        log.info("Received email task: type={}, to={}", message.getType(), message.getUserEmail());

        switch (message.getType()) {
            case MONEY_ADDED -> mailService.sendMoneyAddedEmail(
                    message.getUserEmail(),
                    message.getUserName(),
                    message.getAmount(),
                    message.getNewBalance(),
                    message.getTransactionId()
            );
            case PERIODIC_UPDATE -> mailService.sendPeriodicUpdateEmail(
                    message.getUserEmail(),
                    message.getUserName()
            );

            default -> log.warn("Unknown email type: {}", message.getType());
        }
    }
}