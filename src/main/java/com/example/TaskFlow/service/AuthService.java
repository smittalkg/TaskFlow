package com.example.TaskFlow.service;

import com.example.TaskFlow.dto.LoginRequest;
import com.example.TaskFlow.dto.RegisterRequest;
import com.example.TaskFlow.exception.ResourceNotFoundException;
import com.example.TaskFlow.model.User;
import com.example.TaskFlow.repository.UserRepository;
import com.example.TaskFlow.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exist");
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        String hashPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(hashPassword);
        userRepository.save(user);

        return jwtService.generateToken(request.getEmail());
    }

    public String loginUser(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new ResourceNotFoundException("Email id isn't used"));
        boolean isValid = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!isValid) {
            throw new RuntimeException("Invalid Credentials");
        }

        return jwtService.generateToken(request.getEmail());
    }

}
