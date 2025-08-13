package com.example.sportsscholarship.controller;

import java.util.Map;

import com.example.sportsscholarship.dto.LoginRequest;
import com.example.sportsscholarship.dto.RegisterRequest;
import com.example.sportsscholarship.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody RegisterRequest request) {
        Map<String, Object> response = userService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request) {
        Map<String, Object> response = userService.login(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Map<String, Object>> getUser(@PathVariable Long id) {
        // Sample response
        Map<String, Object> response = Map.of(
                "success", true,
                "user", userService.getUserById(id));
        return ResponseEntity.ok(response);
    }
}

