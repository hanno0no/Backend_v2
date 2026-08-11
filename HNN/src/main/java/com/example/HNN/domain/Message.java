package com.example.HNN.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Message {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    private String content;

    @Column(name = "is_emergency")
    private boolean isEmergency;

    @Column(name = "is_display")
    private boolean isDisplay;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    protected Message() {}

    public Long getMessageId() { return messageId; }
    public String getContent() { return content; }
    public boolean isEmergency() { return isEmergency; }
    public boolean isDisplay() { return isDisplay; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}