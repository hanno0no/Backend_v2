package com.example.HNN.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AdminUser {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long adminId;

    private String username;

    @Column(name = "password_hash")
    private String passwordHash;

    private String role;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    protected AdminUser() {}

    public Long getAdminId() { return adminId; }
    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }
    public String getRole() { return role; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}