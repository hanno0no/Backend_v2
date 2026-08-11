package com.example.HNN.exception;

public class MaterialNotFoundException extends RuntimeException {
    public MaterialNotFoundException(String material) {
        super("사용할 수 없는 재질입니다: " + material);
    }
}