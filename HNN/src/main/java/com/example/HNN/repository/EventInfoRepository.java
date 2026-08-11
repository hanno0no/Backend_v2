package com.example.HNN.repository;

import com.example.HNN.domain.EventInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventInfoRepository extends JpaRepository<EventInfo, Long> {
    Optional<EventInfo> findByIsOpenTrue();
}