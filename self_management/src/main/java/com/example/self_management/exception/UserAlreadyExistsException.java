package com.example.self_management.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends BaseException {
    public UserAlreadyExistsException(String message) {
        //Why HttpStatus.CONFLICT? 409 CONFLICT = resource already exists
        super(ErrorCode.USER_ALREADY_EXIST, message, HttpStatus.CONFLICT);
    }
}
