package com.example.kafkapayment.kafka.producer;

import com.example.kafkapayment.kafka.event.PaymentInitiatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PaymentEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPaymentInitiated(Long paymentId) {
        kafkaTemplate.send("payment-initiate-topic",
                new PaymentInitiatedEvent(paymentId));
    }
}
