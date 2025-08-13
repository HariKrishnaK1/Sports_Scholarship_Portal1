package com.example.sportsscholarship.controller;

import java.util.List;
import java.util.Map;

import com.example.sportsscholarship.dto.ApplicationRequest;
import com.example.sportsscholarship.entity.Application;
import com.example.sportsscholarship.service.ApplicationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> applyForScholarship(
            @RequestBody ApplicationRequest request,
            @RequestParam Long userId) {
        Map<String, Object> response = applicationService.applyForScholarship(request, userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> getUserApplications(@PathVariable Long userId) {
        List<Application> applications = applicationService.getUserApplications(userId);
        Map<String, Object> response = Map.of(
                "success", true,
                "applications", applications,
                "count", applications.size());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getApplicationById(@PathVariable Long id) {
        Application application = applicationService.getApplicationById(id);
        Map<String, Object> response = Map.of(
                "success", true,
                "application", application);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{userId}")
    public ResponseEntity<Map<String, Object>> getApplicationStatus(@PathVariable Long userId) {
        // Sample response for application status tracking
        Map<String, Object> response = Map.of(
                "success", true,
                "userId", userId,
                "applications", applicationService.getUserApplications(userId));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> withdrawApplication(@PathVariable Long id) {
        // Sample response for application withdrawal
        Map<String, Object> response = Map.of(
                "success", true,
                "message", "Application withdrawn successfully",
                "applicationId", id);
        return ResponseEntity.ok(response);
    }
}

