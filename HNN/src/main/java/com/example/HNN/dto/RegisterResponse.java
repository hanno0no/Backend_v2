package com.example.HNN.dto;

public record RegisterResponse(
        Long orderId,
        String fileName,
        String message
) {}