package com.example.sportsscholarship.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.sportsscholarship.entity.Application;
import com.example.sportsscholarship.entity.ApplicationStatus;
import com.example.sportsscholarship.entity.User;
import com.example.sportsscholarship.repository.ApplicationRepository;
import com.example.sportsscholarship.repository.ScholarshipRepository;
import com.example.sportsscholarship.repository.UserRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final ApplicationRepository applicationRepository;
    private final ScholarshipRepository scholarshipRepository;
    private final UserRepository userRepository;

    public List<Application> getAllApplications() {
        // Sample response - in real implementation, you would fetch from database
        return Arrays.asList(
                new Application(1L, null, null, "documents1.pdf",
                        ApplicationStatus.PENDING, null, LocalDateTime.now(), null),
                new Application(2L, null, null, "documents2.pdf",
                        ApplicationStatus.APPROVED, null, LocalDateTime.now().minusDays(5), null),
                new Application(3L, null, null, "documents3.pdf",
                        ApplicationStatus.REJECTED, "Incomplete documents", LocalDateTime.now().minusDays(10), null));
    }

    public Map<String, Object> approveApplication(Long applicationId) {
        // Sample response - in real implementation, you would update in database
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Application approved successfully");
        response.put("applicationId", applicationId);
        response.put("status", ApplicationStatus.APPROVED);
        return response;
    }

    public Map<String, Object> rejectApplication(Long applicationId, String rejectionReason) {
        // Sample response - in real implementation, you would update in database
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Application rejected");
        response.put("applicationId", applicationId);
        response.put("status", ApplicationStatus.REJECTED);
        response.put("rejectionReason", rejectionReason);
        return response;
    }

    public List<User> getAllUsers() {
        // Sample response - in real implementation, you would fetch from database
        return Arrays.asList(
                new User(1L, "John Doe", "john@example.com", "password", null, null, null, null),
                new User(2L, "Jane Smith", "jane@example.com", "password", null, null, null, null),
                new User(3L, "Coach Mike", "mike@example.com", "password", null, null, null, null));
    }

    public Map<String, Object> getDashboardStats() {
        // Sample response - in real implementation, you would calculate from database
        Map<String, Object> response = new HashMap<>();
        response.put("totalApplications", 150);
        response.put("pendingApplications", 45);
        response.put("approvedApplications", 85);
        response.put("rejectedApplications", 20);
        response.put("totalUsers", 75);
        response.put("totalScholarships", 12);
        return response;
    }
}


