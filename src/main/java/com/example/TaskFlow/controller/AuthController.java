package com.example.TaskFlow.controller;

import com.example.TaskFlow.dto.LoginRequest;
import com.example.TaskFlow.dto.RegisterRequest;
import com.example.TaskFlow.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    private ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequest request) {
        String token = authService.registerUser(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    private ResponseEntity<String> loginUser(@Valid @RequestBody LoginRequest request) {
        String token = authService.loginUser(request);
        return ResponseEntity.ok(token);
    }



}
