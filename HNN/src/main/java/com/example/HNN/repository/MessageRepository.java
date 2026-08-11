package com.example.HNN.repository;

import com.example.HNN.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByIsEmergencyTrueAndIsDisplayTrueOrderByCreatedAtDesc();
    List<Message> findByIsEmergencyFalseAndIsDisplayTrueOrderByCreatedAtDesc();
}