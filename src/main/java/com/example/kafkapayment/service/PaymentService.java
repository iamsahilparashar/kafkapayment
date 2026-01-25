package com.example.kafkapayment.service;

import com.example.kafkapayment.dto.PaymentRequest;
import com.example.kafkapayment.entity.Payment;
import com.example.kafkapayment.enums.PaymentStatus;
import com.example.kafkapayment.kafka.producer.PaymentEventProducer;
import com.example.kafkapayment.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentEventProducer eventProducer;

    public PaymentService(PaymentRepository paymentRepository,
                          PaymentEventProducer eventProducer) {
        this.paymentRepository = paymentRepository;
        this.eventProducer = eventProducer;
    }

    @Transactional
    public Payment createPayment(String idempotencyKey, PaymentRequest request) {

        return paymentRepository
                .findByIdempotencyKey(idempotencyKey)
                .orElseGet(() -> {

                    Payment payment = new Payment();
                    payment.setIdempotencyKey(idempotencyKey);
                    payment.setAmount(request.getAmount());
                    payment.setChannel(request.getChannel());
                    payment.setStatus(PaymentStatus.CREATED);

                    payment = paymentRepository.save(payment);

                    eventProducer.sendPaymentInitiated(payment.getId());

                    return payment;
                });
    }
}