package com.example.HNN.exception;

public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException(String teamNum) {
        super("등록되지 않은 팀입니다: " + teamNum);
    }
}