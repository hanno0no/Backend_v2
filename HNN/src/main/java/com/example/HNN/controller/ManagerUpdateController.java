package com.example.HNN.controller;

import com.example.HNN.dto.ManagerUpdateRequest;
import com.example.HNN.dto.ManagerUpdateResponse;
import com.example.HNN.service.ManagerUpdateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagerUpdateController {

    private final ManagerUpdateService managerUpdateService;

    public ManagerUpdateController(ManagerUpdateService managerUpdateService) {
        this.managerUpdateService = managerUpdateService;
    }

    @PatchMapping("/admin/{orderId}/manager")
    public ResponseEntity<ManagerUpdateResponse> updateManager(
            @PathVariable Long orderId,
            @RequestBody ManagerUpdateRequest request) {
        return ResponseEntity.ok(managerUpdateService.updateManager(orderId, request));
    }
}