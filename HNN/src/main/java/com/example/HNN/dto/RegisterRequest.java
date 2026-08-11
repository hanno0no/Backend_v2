package com.example.HNN.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank(message = "팀번호는 필수입니다") String teamNum,
        @NotBlank(message = "재질은 필수입니다") String material
) {}