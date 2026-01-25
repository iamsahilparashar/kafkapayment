package com.example.kafkapayment.kafka.event;

public class PaymentInitiatedEvent {
    private Long paymentId;
    public PaymentInitiatedEvent(Long paymentId) {
        this.paymentId = paymentId;
    }
    public Long getPaymentId() {
        return paymentId;
    }
}
