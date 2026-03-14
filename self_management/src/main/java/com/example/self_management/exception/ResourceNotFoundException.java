package com.example.self_management.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BaseException {
    public ResourceNotFoundException(String message) {
        super(ErrorCode.USER_ALREADY_EXIST, message, HttpStatus.CONFLICT);
    }
}
