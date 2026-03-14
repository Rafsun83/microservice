package com.example.self_management.model.dto.task;


public record CreateTaskRequest(String title, String description, String TaskType, Number status, String coverImage) {
}
