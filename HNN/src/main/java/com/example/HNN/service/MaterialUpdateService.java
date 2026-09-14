package com.example.HNN.service;

import com.example.HNN.domain.Material;
import com.example.HNN.domain.Orders;
import com.example.HNN.dto.MaterialUpdateRequest;
import com.example.HNN.dto.MaterialUpdateResponse;
import com.example.HNN.exception.MaterialNotFoundException;
import com.example.HNN.exception.OrderNotFoundException;
import com.example.HNN.repository.MaterialRepository;
import com.example.HNN.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MaterialUpdateService {

    private final OrderRepository orderRepository;
    private final MaterialRepository materialRepository;

    public MaterialUpdateService(OrderRepository orderRepository,
                                 MaterialRepository materialRepository) {
        this.orderRepository = orderRepository;
        this.materialRepository = materialRepository;
    }

    @Transactional
    public MaterialUpdateResponse updateMaterial(Long orderId, MaterialUpdateRequest request) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        Material newMaterial = materialRepository
                .findByMaterialAndIsActiveTrue(request.material())
                .orElseThrow(() -> new MaterialNotFoundException(request.material()));

        // BR-03: {팀번호}_{주문ID}{재질코드} — 재질이 바뀌면 파일명도 재생성
        String newFileName = generateFileName(
                order.getTeam().getTeamNum(),
                order.getOrderId(),
                newMaterial.getFileCode());

        order.updateMaterial(newMaterial, newFileName);

        return new MaterialUpdateResponse(
                orderId,
                newMaterial.getMaterial(),
                newFileName,
                "재질이 변경되었습니다."
        );
    }

    private String generateFileName(String teamNum, Long orderId, String fileCode) {
        String suffix = (fileCode == null) ? "" : fileCode;
        return teamNum + "_" + orderId + suffix;
    }
}