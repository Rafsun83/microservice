package com.example.self_management.exception;

import com.example.self_management.payload.ApiResponse;
import com.example.self_management.payload.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler  {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.CONFLICT.value()
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFound(ResourceNotFoundException ex) {
        ApiResponse<Object> response =
                new ApiResponse<>(false, ex.getMessage(), null, HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Handle all unexpected errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleInternalServerError(Exception ex) {
        // (Optional) log the real error in console
        ex.printStackTrace();
        ApiResponse<Object> response =
                new ApiResponse<>(
                        false,
                        "Something went wrong. Please try again later.",
                        null,
                        HttpStatus.INTERNAL_SERVER_ERROR.value()
                );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}
