package com.example.self_management.event.email;


import com.example.self_management.model.domain.MoneyAddedEvent;
import com.example.self_management.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MoneyAddedEventListener {

    private final MailService mailService;

    @Async                       // ← runs in a separate thread, API won't wait
    @EventListener
    public void handleMoneyAdded(MoneyAddedEvent event){
        try {
            log.info("Sending add-money email to: {}", event.getUserEmail());
            mailService.sendMoneyAddedEmail(
                    event.getUserEmail(),
                    event.getUserName(),
                    event.getAmount(),
                    event.getNewBalance(),
                    event.getTransactionId()
            );
        } catch (Exception ex) {
            // Never let email failure crash the main flow
            log.error("Failed to send add-money email to {}: {}", event.getUserEmail(), ex.getMessage());
        }

    }
}
