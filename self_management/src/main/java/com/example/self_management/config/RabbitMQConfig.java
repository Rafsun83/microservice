package com.example.self_management.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    // ─── Constants ─────────────────────────────────────────────
    public static final String EXCHANGE   = "wallet.exchange";
    public static final String QUEUE      = "wallet.mail.queue";
    public static final String ROUTING_KEY = "wallet.money.added";

    // ─── Exchange ───────────────────────────────────────────────
    @Bean
    public TopicExchange walletExchange() {
        return new TopicExchange(EXCHANGE);
    }

    // ─── Queue ──────────────────────────────────────────────────
    @Bean
    public Queue walletMailQueue() {
        return QueueBuilder.durable(QUEUE).build();  // durable = survives RabbitMQ restart
    }

    // ─── Binding ────────────────────────────────────────────────
    @Bean
    public Binding binding(Queue walletMailQueue, TopicExchange walletExchange) {
        return BindingBuilder
                .bind(walletMailQueue)
                .to(walletExchange)
                .with(ROUTING_KEY);
    }

    // ─── JSON Converter ─────────────────────────────────────────
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();  // serialize objects as JSON
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
