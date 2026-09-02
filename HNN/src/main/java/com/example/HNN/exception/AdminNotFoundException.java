package com.example.HNN.exception;

public class AdminNotFoundException extends RuntimeException {
    public AdminNotFoundException(Long adminId) {
        super("존재하지 않는 관리자입니다: " + adminId);
    }
}