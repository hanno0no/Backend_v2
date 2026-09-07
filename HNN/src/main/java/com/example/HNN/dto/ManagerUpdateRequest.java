package com.example.HNN.dto;

public record ManagerUpdateRequest(
        Long managerId   // null 허용: 담당자 미지정으로 변경
) {}