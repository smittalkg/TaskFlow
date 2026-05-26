package com.example.TaskFlow.controller;

import com.example.TaskFlow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    private boolean loginUser(String name, String password) {
        return userService.isValidUser(name, password);
    }



}
