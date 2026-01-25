package com.example.kafkapayment.controller;


import com.example.kafkapayment.dto.PaymentRequest;
import com.example.kafkapayment.entity.Payment;
import com.example.kafkapayment.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<?> createPayment(
            @RequestHeader("Idempotency-Key") String idempotencyKey,
            @RequestBody PaymentRequest request) {

        Payment payment = paymentService.createPayment(idempotencyKey, request);
        return ResponseEntity.ok(payment);
    }
}
