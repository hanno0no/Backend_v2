package com.example.HNN.service;

import com.example.HNN.repository.OrderRepository;
import com.example.HNN.repository.StateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class OrderStatsService {

    private final OrderRepository orderRepository;
    private final StateRepository stateRepository;

    public OrderStatsService(OrderRepository orderRepository,
                             StateRepository stateRepository) {
        this.orderRepository = orderRepository;
        this.stateRepository = stateRepository;
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getStats() {
        // 1. state 테이블의 모든 상태를 0으로 초기화 (state_num 순서 유지)
        Map<String, Long> stats = new LinkedHashMap<>();
        stateRepository.findAll().forEach(s -> stats.put(s.getState(), 0L));

        // 2. 실제 건수로 덮어쓰기
        orderRepository.countByState().forEach(row ->
                stats.put(row.getState(), row.getCount()));

        return stats;
    }
}