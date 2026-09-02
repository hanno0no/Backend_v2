package com.example.HNN.dto;

public record StatusUpdateResponse(
        Long orderId,
        String status,
        String message
) {}