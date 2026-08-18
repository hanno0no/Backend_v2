package com.example.HNN.service;

import com.example.HNN.domain.Orders;
import com.example.HNN.dto.AdminOrderResponse;
import com.example.HNN.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.HNN.repository.OrderSpecifications.*;

@Service
public class AdminOrderService {

    private final OrderRepository orderRepository;

    public AdminOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public Page<AdminOrderResponse> getOrders(String teamNum, String material,
                                              String admin, String state,
                                              Pageable pageable) {

        Specification<Orders> spec = Specification.where(teamNumEquals(teamNum))
                .and(materialEquals(material))
                .and(adminEquals(admin))
                .and(stateEquals(state));

        return orderRepository.findAll(spec, pageable)
                .map(this::toResponse);
    }

    private AdminOrderResponse toResponse(Orders order) {
        return new AdminOrderResponse(
                order.getOrderId(),
                order.getTeam().getTeamNum(),
                order.getMaterial().getMaterial(),
                order.getFileName(),
                order.getAdminUser() != null ? order.getAdminUser().getUsername() : null,
                order.getState().getState()
        );
    }
}