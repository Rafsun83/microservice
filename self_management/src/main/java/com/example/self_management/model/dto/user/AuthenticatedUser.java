package com.example.self_management.model.dto.user;

public record AuthenticatedUser(Long userId, String email, String username, String name) {
}
