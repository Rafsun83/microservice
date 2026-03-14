package com.example.self_management.controller;

import com.example.self_management.model.domain.User;
import com.example.self_management.model.dto.auth.JwtResponse;
import com.example.self_management.model.dto.auth.LoginRequest;
import com.example.self_management.model.dto.auth.RefreshTokenRequest;
import com.example.self_management.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User request) {
        authService.register(request);
        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request, HttpServletRequest httpRequest) {
        return ResponseEntity.ok(authService.login(request, httpRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refresh(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(authService.refreshToken(request.getRefreshToken()));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody RefreshTokenRequest request) {
        authService.logout(request.getRefreshToken());
        return ResponseEntity.ok("Logged out");
    }

    @PostMapping("/logout-all")
    public ResponseEntity<?> logoutFromAllDevices(@RequestBody RefreshTokenRequest request) {
        authService.logoutAll(request.getRefreshToken());
        return ResponseEntity.ok("Logged out from all devices");
    }
}
