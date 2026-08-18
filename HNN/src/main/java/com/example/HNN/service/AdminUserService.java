package com.example.HNN.service;

import com.example.HNN.domain.AdminUser;
import com.example.HNN.dto.AdminUserResponse;
import com.example.HNN.repository.AdminUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminUserService {

    private final AdminUserRepository adminUserRepository;

    public AdminUserService(AdminUserRepository adminUserRepository) {
        this.adminUserRepository = adminUserRepository;
    }

    @Transactional(readOnly = true)
    public List<AdminUserResponse> getAllAdmins() {
        return adminUserRepository.findAll().stream()
                .map(a -> new AdminUserResponse(a.getAdminId(), a.getUsername()))
                .toList();
    }
}