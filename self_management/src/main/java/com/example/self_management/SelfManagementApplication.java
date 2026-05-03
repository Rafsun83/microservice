package com.example.self_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SelfManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(SelfManagementApplication.class, args);
    }
}
