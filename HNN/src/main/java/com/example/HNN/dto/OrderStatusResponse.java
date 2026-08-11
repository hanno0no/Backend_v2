package com.example.HNN.dto;

public record OrderStatusResponse(
        Long orderId,
        String material,
        String status,
        String orderTime
) {}