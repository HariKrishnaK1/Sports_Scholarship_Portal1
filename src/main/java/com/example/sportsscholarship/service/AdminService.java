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
    private final EmailService emailService;

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
        // Persist: set status APPROVED and save
        Application app = applicationRepository.findById(applicationId).orElse(null);
        if (app != null) {
            app.setStatus(ApplicationStatus.APPROVED);
            app.setRejectionReason(null);
            applicationRepository.save(app);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Application approved successfully");
        response.put("applicationId", applicationId);
        response.put("status", ApplicationStatus.APPROVED);

        // Attempt to notify applicant by email
        try {
            applicationRepository.findById(applicationId).ifPresent(entity -> {
                User applicant = entity.getApplicant();
                if (applicant != null && applicant.getEmail() != null) {
                    String scholarshipName = entity.getScholarship() != null && entity.getScholarship().getName() != null
                            ? entity.getScholarship().getName()
                            : (entity.getScholarship() != null ? ("Scholarship ID " + entity.getScholarship().getId()) : "your scholarship");
                    String subject = "Application Approved: " + scholarshipName;
                    String body = "Hello " + applicant.getName() + ",\n\n" +
                            "Good news! Your application (ID: " + applicationId + ") for " + scholarshipName + " has been APPROVED.\n\n" +
                            "Regards,\nSports Scholarship Portal";
                    emailService.sendPlainText(applicant.getEmail(), subject, body);
                }
            });
        } catch (Exception ignored) {
        }
        return response;
    }

    public Map<String, Object> rejectApplication(Long applicationId, String rejectionReason) {
        // Persist: set status REJECTED with reason and save
        Application app = applicationRepository.findById(applicationId).orElse(null);
        if (app != null) {
            app.setStatus(ApplicationStatus.REJECTED);
            app.setRejectionReason(rejectionReason);
            applicationRepository.save(app);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Application rejected");
        response.put("applicationId", applicationId);
        response.put("status", ApplicationStatus.REJECTED);
        response.put("rejectionReason", rejectionReason);

        // Attempt to notify applicant by email
        try {
            applicationRepository.findById(applicationId).ifPresent(entity -> {
                User applicant = entity.getApplicant();
                if (applicant != null && applicant.getEmail() != null) {
                    String scholarshipName = entity.getScholarship() != null && entity.getScholarship().getName() != null
                            ? entity.getScholarship().getName()
                            : (entity.getScholarship() != null ? ("Scholarship ID " + entity.getScholarship().getId()) : "your scholarship");
                    String subject = "Application Rejected: " + scholarshipName;
                    String body = "Hello " + applicant.getName() + ",\n\n" +
                            "We are sorry to inform you that your application (ID: " + applicationId + ") for " + scholarshipName + " has been REJECTED.\n" +
                            (rejectionReason != null && !rejectionReason.isBlank() ? ("Reason: " + rejectionReason + "\n\n") : "\n") +
                            "You may review your application and apply again if eligible.\n\n" +
                            "Regards,\nSports Scholarship Portal";
                    emailService.sendPlainText(applicant.getEmail(), subject, body);
                }
            });
        } catch (Exception ignored) {
        }
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



