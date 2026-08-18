package com.example.HNN.dto;

public record AdminOrderResponse(
        Long orderId,
        String teamNum,
        String material,
        String fileName,
        String admin,
        String state
) {}