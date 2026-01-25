package com.example.kafkapayment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponse {

    private boolean success;
    private String referenceId;

    public PaymentResponse(boolean success, String referenceId) {
        this.success = success;
        this.referenceId = referenceId;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getReferenceId() {
        return referenceId;
    }
}
