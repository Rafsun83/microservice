package com.example.self_management.model.dto.blog;



public record CreateBlogRequest(String title, String content, String authorName, String category, String thumbnail) {
}
