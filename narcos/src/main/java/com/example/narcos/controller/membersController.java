package com.example.narcos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class membersController {
    @GetMapping("/api/hello")
    public String sayHello() {
        return "Hello I'm Rafsun! Narcos member";
    }
}
