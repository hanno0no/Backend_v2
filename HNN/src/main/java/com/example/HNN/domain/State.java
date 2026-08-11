package com.example.HNN.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class State {

    @Id
    @Column(name = "state_num")
    private Integer stateNum;

    private String state;   // "submitted", "accepted", "design_complete", "print_complete", "failed", "picked_up"

    protected State() {}

    public Integer getStateNum() { return stateNum; }
    public String getState() { return state; }
}