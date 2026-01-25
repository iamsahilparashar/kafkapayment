package com.example.kafkapayment.exception;

import com.example.kafkapayment.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handle(Exception ex) {
        return ResponseEntity.internalServerError()
                .body(new ApiResponse<>("ERROR", ex.getMessage()));
    }
}

