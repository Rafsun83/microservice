package com.example.self_management.model.dto.user;

public record CreateUserRequest(String email, String name, String password, String username, String location) {
}
