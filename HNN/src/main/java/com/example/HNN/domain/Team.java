package com.example.HNN.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Team {

    @Id
    @Column(name = "team_num")
    private String teamNum;

    private int phone;

    protected Team() {}

    public Team(String teamNum, int phone) {
        this.teamNum = teamNum;
        this.phone = phone;
    }

    public String getTeamNum() { return teamNum; }
    public int getPhone() { return phone; }
}