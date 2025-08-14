package com.example.sportsscholarship.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.example.sportsscholarship.dto.LoginRequest;
import com.example.sportsscholarship.dto.RegisterRequest;
import com.example.sportsscholarship.dto.UserDto;
import com.example.sportsscholarship.entity.User;
import com.example.sportsscholarship.repository.UserRepository;
import com.example.sportsscholarship.security.JwtUtil;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public Map<String, Object> register(RegisterRequest request) {
        Map<String, Object> response = new HashMap<>();

        // Check if user already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            response.put("success", false);
            response.put("message", "User with this email already exists");
            return response;
        }

        // Create new user
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        User savedUser = userRepository.save(user);

        response.put("success", true);
        response.put("message", "User registered successfully");
        response.put("user",
                new UserDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getRole()));
        return response;
    }

    public Map<String, Object> login(LoginRequest request) {
        Map<String, Object> response = new HashMap<>();

        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());

        if (userOpt.isPresent() && passwordEncoder.matches(request.getPassword(), userOpt.get().getPassword())) {
            User user = userOpt.get();
            response.put("success", true);
            response.put("message", "Login successful");
            response.put("user", new UserDto(user.getId(), user.getName(), user.getEmail(), user.getRole()));
            Map<String, Object> claims = new HashMap<>();
            claims.put("role", user.getRole().name());
            String token = jwtUtil.generateToken(user.getEmail(), claims);
            response.put("token", token);
        } else {
            response.put("success", false);
            response.put("message", "Invalid email or password");
        }

        return response;
    }

    public UserDto getUserById(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getRole());
        }
        return null;
    }
}
