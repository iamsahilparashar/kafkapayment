package com.example.kafkapayment.channel;

import com.example.kafkapayment.dto.PaymentResponse;
import com.example.kafkapayment.entity.Payment;

public interface PaymentChannel {
    PaymentResponse process(Payment payment);
}
