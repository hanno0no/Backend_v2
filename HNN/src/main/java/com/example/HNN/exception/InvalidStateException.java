package com.example.HNN.exception;

public class InvalidStateException extends RuntimeException {
    public InvalidStateException(String status) {
        super("유효하지 않은 상태값입니다: " + status);
    }
}