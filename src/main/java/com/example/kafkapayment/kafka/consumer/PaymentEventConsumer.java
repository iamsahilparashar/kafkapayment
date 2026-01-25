package com.example.kafkapayment.kafka.consumer;

import com.example.kafkapayment.channel.PaymentChannel;
import com.example.kafkapayment.channel.PaymentChannelFactory;
import com.example.kafkapayment.dto.PaymentResponse;
import com.example.kafkapayment.entity.Payment;
import com.example.kafkapayment.enums.PaymentStatus;
import com.example.kafkapayment.kafka.event.PaymentInitiatedEvent;
import com.example.kafkapayment.repository.PaymentRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component
public class PaymentEventConsumer {

    private final PaymentRepository paymentRepository;
    private final PaymentChannelFactory channelFactory;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PaymentEventConsumer(PaymentRepository paymentRepository,
                                PaymentChannelFactory channelFactory,
                                KafkaTemplate<String, Object> kafkaTemplate) {
        this.paymentRepository = paymentRepository;
        this.channelFactory = channelFactory;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "payment-initiate-topic", groupId = "payment-group")
    @Transactional
    public void process(PaymentInitiatedEvent event) {

        Payment payment = paymentRepository
                .findById(event.getPaymentId())
                .orElseThrow();

        payment.setStatus(PaymentStatus.PROCESSING);

        PaymentChannel channel =
                channelFactory.getChannel(payment.getChannel());

        PaymentResponse response = channel.process(payment);

        if (response.isSuccess()) {
            payment.setStatus(PaymentStatus.SUCCESS);
            payment.setReferenceId(response.getReferenceId());
        } else {
            payment.setStatus(PaymentStatus.RETRY);
            kafkaTemplate.send("payment-retry-topic", event);
        }

        paymentRepository.save(payment);
    }
}