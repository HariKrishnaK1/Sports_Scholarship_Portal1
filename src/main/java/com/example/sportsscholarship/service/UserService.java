package com.example.sportsscholarship.service;

import com.example.sportsscholarship.dto.LoginRequest;
import com.example.sportsscholarship.dto.RegisterRequest;
import com.example.sportsscholarship.dto.UserDto;
import com.example.sportsscholarship.entity.User;
import com.example.sportsscholarship.entity.UserRole;
import com.example.sportsscholarship.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    
    public Map<String, Object> register(RegisterRequest request) {
        // Sample response - in real implementation, you would save to database
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "User registered successfully");
        response.put("user", new UserDto(1L, request.getName(), request.getEmail(), request.getRole()));
        return response;
    }
    
    public Map<String, Object> login(LoginRequest request) {
        // Sample response - in real implementation, you would validate credentials
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Login successful");
        response.put("user", new UserDto(1L, "John Doe", request.getEmail(), UserRole.APPLICANT));
        response.put("token", "sample-jwt-token-12345");
        return response;
    }
    
    public UserDto getUserById(Long id) {
        // Sample response - in real implementation, you would fetch from database
        return new UserDto(id, "John Doe", "john@example.com", UserRole.APPLICANT);
    }
}
