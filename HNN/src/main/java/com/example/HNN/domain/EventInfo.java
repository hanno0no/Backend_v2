package com.example.HNN.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class EventInfo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventId;

    private String name;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "is_open")
    private boolean isOpen;

    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "completed_limit")
    private int completedLimit;

    @Column(name = "waiting_limit")
    private int waitingLimit;

    protected EventInfo() {}

    public Long getEventId() { return eventId; }
    public String getName() { return name; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public boolean isOpen() { return isOpen; }
    public String getDescription() { return description; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public int getCompletedLimit() { return completedLimit; }
    public int getWaitingLimit() { return waitingLimit; }
}