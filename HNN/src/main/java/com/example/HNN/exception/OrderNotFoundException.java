package com.example.HNN.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long orderId) {
        super("존재하지 않는 주문입니다: " + orderId);
    }
}