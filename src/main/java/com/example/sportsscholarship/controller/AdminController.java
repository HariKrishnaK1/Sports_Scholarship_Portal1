package com.example.sportsscholarship.controller;

import java.util.List;
import java.util.Map;

import com.example.sportsscholarship.entity.Application;
import com.example.sportsscholarship.entity.ApplicationStatus;
import com.example.sportsscholarship.entity.User;
import com.example.sportsscholarship.service.AdminService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/applications")
    public ResponseEntity<Map<String, Object>> getAllApplications() {
        List<Application> applications = adminService.getAllApplications();
        Map<String, Object> response = Map.of(
                "success", true,
                "applications", applications,
                "count", applications.size());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/applications/{applicationId}/approve")
    public ResponseEntity<Map<String, Object>> approveApplication(@PathVariable Long applicationId) {
        Map<String, Object> response = adminService.approveApplication(applicationId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/applications/{applicationId}/reject")
    public ResponseEntity<Map<String, Object>> rejectApplication(
            @PathVariable Long applicationId,
            @RequestParam String rejectionReason) {
        Map<String, Object> response = adminService.rejectApplication(applicationId, rejectionReason);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users")
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        List<User> users = adminService.getAllUsers();
        Map<String, Object> response = Map.of(
                "success", true,
                "users", users,
                "count", users.size());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getDashboardStats() {
        Map<String, Object> response = adminService.getDashboardStats();
        response.put("success", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/applications/status/{status}")
    public ResponseEntity<Map<String, Object>> getApplicationsByStatus(@PathVariable ApplicationStatus status) {
        // Sample response for filtering applications by status
        Map<String, Object> response = Map.of(
                "success", true,
                "status", status,
                "message", "Filtered applications by status");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Long userId) {
        // Sample response for deleting user
        Map<String, Object> response = Map.of(
                "success", true,
                "message", "User deleted successfully",
                "userId", userId);
        return ResponseEntity.ok(response);
    }
}

