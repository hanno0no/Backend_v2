package com.example.HNN.dto;

import jakarta.validation.constraints.NotBlank;

public record MaterialUpdateRequest(
        @NotBlank(message = "재질은 필수입니다") String material
) {}