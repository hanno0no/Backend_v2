package com.example.HNN.service;

import com.example.HNN.domain.Orders;
import com.example.HNN.domain.State;
import com.example.HNN.dto.StatusUpdateRequest;
import com.example.HNN.dto.StatusUpdateResponse;
import com.example.HNN.exception.InvalidStateException;
import com.example.HNN.exception.OrderNotFoundException;
import com.example.HNN.repository.OrderRepository;
import com.example.HNN.repository.StateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StatusUpdateService {

    private final OrderRepository orderRepository;
    private final StateRepository stateRepository;

    public StatusUpdateService(OrderRepository orderRepository,
                               StateRepository stateRepository) {
        this.orderRepository = orderRepository;
        this.stateRepository = stateRepository;
    }

    @Transactional
    public StatusUpdateResponse updateStatus(Long orderId, StatusUpdateRequest request) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        State newState = stateRepository.findByState(request.status())
                .orElseThrow(() -> new InvalidStateException(request.status()));

        order.updateState(newState);

        return new StatusUpdateResponse(
                orderId,
                newState.getState(),
                "상태가 변경되었습니다."
        );
    }
}