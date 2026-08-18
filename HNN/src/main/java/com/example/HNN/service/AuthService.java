package com.example.HNN.service;

import com.example.HNN.domain.AdminUser;
import com.example.HNN.dto.LoginRequest;
import com.example.HNN.dto.LoginResponse;
import com.example.HNN.exception.InvalidCredentialsException;
import com.example.HNN.repository.AdminUserRepository;
import com.example.HNN.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(AdminUserRepository adminUserRepository,
                       PasswordEncoder passwordEncoder,
                       JwtTokenProvider jwtTokenProvider) {
        this.adminUserRepository = adminUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        AdminUser admin = adminUserRepository.findByUsername(request.username())
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(request.password(), admin.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }

        String token = jwtTokenProvider.createToken(
                admin.getAdminId(),
                admin.getUsername(),
                admin.getRole()
        );

        return new LoginResponse(token);
    }
}