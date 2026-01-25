package com.example.kafkapayment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private Double amount;
    private String channel; // UPI / CARD / WALLET
}