package com.example.HNN.dto;

public record MaterialUpdateResponse(
        Long orderId,
        String material,
        String fileName,
        String message
) {}