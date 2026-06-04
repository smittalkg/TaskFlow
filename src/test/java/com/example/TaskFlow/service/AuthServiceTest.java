package com.example.TaskFlow.service;

import com.example.TaskFlow.dto.RegisterRequest;
import com.example.TaskFlow.model.User;
import com.example.TaskFlow.repository.UserRepository;
import com.example.TaskFlow.security.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    @Test
    void registerUser_whenEmailExists_throwsRuntimeException() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("test@test.com");

        when(userRepository.existsByEmail("test@test.com")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> authService.registerUser(request));
    }

    @Test
    void registerUser_whenEmailNew_returnsToken() {
        User user = new User();
        user.setName("test");
        user.setEmail("test@test.com");


        RegisterRequest request = new RegisterRequest();
        request.setEmail("test@test.com");

        when(userRepository.existsByEmail("test@test.com")).thenReturn(false);
        when(passwordEncoder.encode(any())).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(jwtService.generateToken("test@test.com")).thenReturn("token");

        String token = authService.registerUser(request);

        assertThat(token).isEqualTo("token");
    }

}