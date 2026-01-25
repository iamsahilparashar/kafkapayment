package com.example.kafkapayment.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ReconciliationService {

    @Scheduled(fixedDelay = 60000)
    public void reconcile() {
        // compare DB vs gateway
        // mark stuck payments FAILED or RETRY
    }
}
