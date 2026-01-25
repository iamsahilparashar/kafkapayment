package com.example.kafkapayment.channel.Impl;

import com.example.kafkapayment.channel.PaymentChannel;
import com.example.kafkapayment.dto.PaymentResponse;
import com.example.kafkapayment.entity.Payment;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("WALLET")
public class WalletPaymentChannel implements PaymentChannel {

    @Override
    public PaymentResponse process(Payment payment) {
        return new PaymentResponse(true, "WALLET_" + UUID.randomUUID());
    }
}
