package com.example.HNN.service;

import com.example.HNN.domain.Orders;
import com.example.HNN.dto.OrderStatusResponse;
import com.example.HNN.exception.TeamNotFoundException;
import com.example.HNN.repository.OrderRepository;
import com.example.HNN.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CheckStatusService {

    private static final DateTimeFormatter ISO_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private final OrderRepository orderRepository;
    private final TeamRepository teamRepository;

    public CheckStatusService(OrderRepository orderRepository,
                              TeamRepository teamRepository) {
        this.orderRepository = orderRepository;
        this.teamRepository = teamRepository;
    }

    @Transactional(readOnly = true)
    public List<OrderStatusResponse> getOrdersByTeam(String teamNum) {
        // 등록된 팀인지 먼저 확인
        if (!teamRepository.existsById(teamNum)) {
            throw new TeamNotFoundException(teamNum);
        }

        List<Orders> orders = orderRepository.findByTeam_TeamNumOrderByOrderedAtDesc(teamNum);

        return orders.stream()
                .map(o -> new OrderStatusResponse(
                        o.getOrderId(),
                        o.getMaterial().getMaterial(),
                        o.getState().getState(),
                        o.getOrderedAt().format(ISO_FORMAT)
                ))
                .toList();
    }
}