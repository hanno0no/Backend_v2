package com.example.HNN.domain;

import jakarta.persistence.*;

@Entity
public class Material {

    @Id
    @Column(name = "material_num")
    private Integer materialNum;

    private String material;

    @Column(name = "file_code")
    private String fileCode;      // 파일명 접미사 (M, A 등). 없으면 null

    @Column(name = "is_active")
    private boolean isActive;

    protected Material() {}

    public Integer getMaterialNum() { return materialNum; }
    public String getMaterial() { return material; }
    public String getFileCode() { return fileCode; }
    public boolean isActive() { return isActive; }
}