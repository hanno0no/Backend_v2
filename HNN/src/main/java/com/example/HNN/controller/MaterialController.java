package com.example.HNN.controller;

import com.example.HNN.service.MaterialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/register")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping("/materials")
    public ResponseEntity<List<String>> getActiveMaterials() {
        return ResponseEntity.ok(materialService.getActiveMaterialNames());
    }
}