package com.example.HNN.controller;

import com.example.HNN.dto.AdminOrderResponse;
import com.example.HNN.service.AdminOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminOrderController {

    private final AdminOrderService adminOrderService;

    public AdminOrderController(AdminOrderService adminOrderService) {
        this.adminOrderService = adminOrderService;
    }

    @GetMapping("/admin/view")
    public ResponseEntity<Page<AdminOrderResponse>> view(
            @RequestParam(required = false) String teamNum,
            @RequestParam(required = false) String material,
            @RequestParam(required = false) String admin,
            @RequestParam(required = false) String state,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "orderedAt") String sort,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        Sort.Direction dir = "asc".equalsIgnoreCase(direction)
                ? Sort.Direction.ASC : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(dir, mapSortField(sort)));

        return ResponseEntity.ok(
                adminOrderService.getOrders(teamNum, material, admin, state, pageable));
    }

    // sort 파라미터(API 명세상 이름) → 실제 엔티티 필드 경로 변환
    private String mapSortField(String sort) {
        return switch (sort) {
            case "teamNum" -> "team.teamNum";
            case "state" -> "state.state";
            case "updatedAt" -> "updatedAt";
            default -> "orderedAt";
        };
    }
}