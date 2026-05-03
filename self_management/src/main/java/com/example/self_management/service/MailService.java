package com.example.self_management.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Value("${app.mail.from}")
    private String fromEmail;

    @Value("${app.mail.from-name}")
    private String fromName;

    public void sendMoneyAddedEmail(String toEmail, String userName, BigDecimal amount, BigDecimal newBalance, String transactionId){

        try{
            // Build Thymeleaf context
            Context ctx = new Context();
            ctx.setVariable("userName",userName);
            ctx.setVariable("amount",amount);
            ctx.setVariable("newBalance",newBalance);
            ctx.setVariable("transactionId",transactionId);

            String htmlContent = templateEngine.process("emails/money-added", ctx);


            // Build MIME message
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail, fromName);
            helper.setTo(toEmail);
            helper.setSubject("💰 Money Deposit Successfully!");
            helper.setText(htmlContent, true);  // true = HTML
            mailSender.send(message);
            log.info("Email sent successfully to {}", toEmail);

        } catch (MessagingException | java.io.UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage(), e);
        }
    }

    // NEW method — for periodic updates
    public void sendPeriodicUpdateEmail(String email, String name) {
        try{
            String body = String.format(
                    "Hello %s,\n\nHere's your periodic update from E-Wallet.\n" +
                            "Stay safe and keep transacting!\n\n— The E-Wallet Team",
                    name
            );

            // Build MIME message
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(fromEmail, fromName);
            helper.setTo(email);
            helper.setSubject("Weekly transaction update!! ");
            helper.setText(body, true);  // true = HTML
            mailSender.send(message);
            log.info("Email has been sent successfully to {}",email);

        }
        catch (MessagingException  | java.io.UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage(), e);
        }
    }


    // Common helper method
//    private void sendSimpleEmail(String to, String subject, String body) {
//        SimpleMailMessage mail = new SimpleMailMessage();
//        mail.setTo(to);
//        mail.setSubject(subject);
//        mail.setText(body);
//        mailSender.send(mail);
//        log.info("Email sent to {}", to);
//    }
}
