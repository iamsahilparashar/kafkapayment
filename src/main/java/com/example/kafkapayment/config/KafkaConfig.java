package com.example.kafkapayment.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic paymentTopic() {
        return new NewTopic("payment-initiate-topic", 3, (short) 1);
    }

    @Bean
    public NewTopic retryTopic() {
        return new NewTopic("payment-retry-topic", 3, (short) 1);
    }
}

