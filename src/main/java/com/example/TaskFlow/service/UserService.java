package com.example.TaskFlow.service;

import com.example.TaskFlow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean isValidUser(String name, String password) {
        return true;
    }

}
