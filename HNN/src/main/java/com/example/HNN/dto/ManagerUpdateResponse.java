package com.example.HNN.dto;

public record ManagerUpdateResponse(
        Long orderId,
        Long managerId,
        String managerName,
        String message
) {}