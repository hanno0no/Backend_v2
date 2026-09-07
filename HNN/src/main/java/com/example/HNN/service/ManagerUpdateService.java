package com.example.HNN.service;

import com.example.HNN.domain.AdminUser;
import com.example.HNN.domain.Orders;
import com.example.HNN.dto.ManagerUpdateRequest;
import com.example.HNN.dto.ManagerUpdateResponse;
import com.example.HNN.exception.AdminNotFoundException;
import com.example.HNN.exception.OrderNotFoundException;
import com.example.HNN.repository.AdminUserRepository;
import com.example.HNN.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManagerUpdateService {

    private final OrderRepository orderRepository;
    private final AdminUserRepository adminUserRepository;

    public ManagerUpdateService(OrderRepository orderRepository,
                                AdminUserRepository adminUserRepository) {
        this.orderRepository = orderRepository;
        this.adminUserRepository = adminUserRepository;
    }

    @Transactional
    public ManagerUpdateResponse updateManager(Long orderId, ManagerUpdateRequest request) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        if (request.managerId() == null) {
            order.updateAdmin(null);
            return new ManagerUpdateResponse(orderId, null, null, "담당자가 미지정으로 변경되었습니다.");
        }

        AdminUser admin = adminUserRepository.findById(request.managerId())
                .orElseThrow(() -> new AdminNotFoundException(request.managerId()));

        order.updateAdmin(admin);

        return new ManagerUpdateResponse(
                orderId,
                admin.getAdminId(),
                admin.getUsername(),
                "담당자가 변경되었습니다."
        );
    }
}