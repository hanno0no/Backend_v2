package com.example.HNN.domain;

import jakarta.persistence.*;

@Entity
public class Material {

    @Id
    @Column(name = "material_num")
    private Integer materialNum;

    private String material;

    @Column(name = "is_active")
    private boolean isActive;

    protected Material() {}

    public Integer getMaterialNum() { return materialNum; }
    public String getMaterial() { return material; }
    public boolean isActive() { return isActive; }
}