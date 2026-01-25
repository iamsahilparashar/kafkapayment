package com.example.kafkapayment.kafka.consumer;

import com.example.kafkapayment.kafka.event.PaymentInitiatedEvent;
import com.example.kafkapayment.kafka.producer.PaymentEventProducer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentRetryConsumer {

    private final PaymentEventProducer producer;

    public PaymentRetryConsumer(PaymentEventProducer producer) {
        this.producer = producer;
    }

    @KafkaListener(topics = "payment-retry-topic", groupId = "retry-group")
    public void retry(PaymentInitiatedEvent event) {
        producer.sendPaymentInitiated(event.getPaymentId());
    }
}
