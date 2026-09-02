package com.example.HNN.dto;

import jakarta.validation.constraints.NotBlank;

public record StatusUpdateRequest(
        @NotBlank(message = "상태값은 필수입니다") String status
) {}