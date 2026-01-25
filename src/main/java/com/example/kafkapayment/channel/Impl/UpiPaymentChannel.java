package com.example.kafkapayment.channel.Impl;

import com.example.kafkapayment.channel.PaymentChannel;
import com.example.kafkapayment.dto.PaymentResponse;
import com.example.kafkapayment.entity.Payment;
import org.springframework.stereotype.Component;

@Component("UPI")
public class UpiPaymentChannel implements PaymentChannel {

    @Override
    public PaymentResponse process(Payment payment) {
        return new PaymentResponse(true, "UPI_REF_123");
    }
}
