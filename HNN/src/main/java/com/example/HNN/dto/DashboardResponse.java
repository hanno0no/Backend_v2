package com.example.HNN.dto;

import java.util.List;

public record DashboardResponse(
        List<String> completedTeam,
        List<String> waitingTeam,
        String endTime,
        List<String> emergencyMessage,
        List<String> messages
) {}