package com.example.HNN.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoActiveEventException.class)
    public ResponseEntity<Map<String, String>> handleNoActiveEvent(NoActiveEventException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)   // 409: 현재 상태상 처리 불가
                .body(Map.of("error", e.getMessage()));
    }
}