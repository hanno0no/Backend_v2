package com.example.HNN.controller;

import com.example.HNN.dto.AdminUserResponse;
import com.example.HNN.service.AdminUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {

    private final AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @GetMapping("/admins")
    public ResponseEntity<List<AdminUserResponse>> getAdmins() {
        return ResponseEntity.ok(adminUserService.getAllAdmins());
    }
}