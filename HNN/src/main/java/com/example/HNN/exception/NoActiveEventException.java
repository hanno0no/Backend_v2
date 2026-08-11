package com.example.HNN.exception;

public class NoActiveEventException extends RuntimeException {
    public NoActiveEventException() {
        super("현재 활성화된 이벤트가 없습니다");
    }
}