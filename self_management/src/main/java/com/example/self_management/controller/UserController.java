package com.example.self_management.controller;


import com.example.self_management.model.domain.User;
import com.example.self_management.payload.ApiResponse;
import com.example.self_management.service.UserService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<ApiResponse<User>> getUserDetails(){
        User user = userService.getUserDetails();
        ApiResponse<User> apiResponse = new ApiResponse<>(true, "User fetched successfully!", user, HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers(@ParameterObject Pageable pageable){
        List<User> users = userService.getAllUsers(pageable);
        ApiResponse<List<User>> response = new ApiResponse<>(true, "User fetched successfully!", users, HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

//    @PostMapping("/registration")
//    public Long registration(@RequestBody CreateUserRequest createUserRequest){
//        return userService.createUser(createUserRequest);
//    }
}
