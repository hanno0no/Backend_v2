package com.example.HNN.controller;

import com.example.HNN.dto.MaterialUpdateRequest;
import com.example.HNN.dto.MaterialUpdateResponse;
import com.example.HNN.service.MaterialUpdateService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MaterialUpdateController {

    private final MaterialUpdateService materialUpdateService;

    public MaterialUpdateController(MaterialUpdateService materialUpdateService) {
        this.materialUpdateService = materialUpdateService;
    }

    @PatchMapping("/admin/{orderId}/material")
    public ResponseEntity<MaterialUpdateResponse> updateMaterial(
            @PathVariable Long orderId,
            @Valid @RequestBody MaterialUpdateRequest request) {
        return ResponseEntity.ok(materialUpdateService.updateMaterial(orderId, request));
    }
}