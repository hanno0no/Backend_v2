package com.example.HNN.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Orders {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "ordered_at")
    private LocalDateTime orderedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_num")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material")
    private Material material;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_num")
    private State state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private AdminUser adminUser;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "hidden_from_dashboard")
    private boolean hiddenFromDashboard;

    protected Orders() {}

    public Long getOrderId() { return orderId; }
    public Team getTeam() { return team; }
    public State getState() { return state; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public boolean isHiddenFromDashboard() { return hiddenFromDashboard; }
}