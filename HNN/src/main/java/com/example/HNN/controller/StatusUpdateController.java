package com.example.HNN.controller;

import com.example.HNN.dto.StatusUpdateRequest;
import com.example.HNN.dto.StatusUpdateResponse;
import com.example.HNN.service.StatusUpdateService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusUpdateController {

    private final StatusUpdateService statusUpdateService;

    public StatusUpdateController(StatusUpdateService statusUpdateService) {
        this.statusUpdateService = statusUpdateService;
    }

    @PatchMapping("/admin/{orderId}/status")
    public ResponseEntity<StatusUpdateResponse> updateStatus(
            @PathVariable Long orderId,
            @Valid @RequestBody StatusUpdateRequest request) {
        return ResponseEntity.ok(statusUpdateService.updateStatus(orderId, request));
    }
}