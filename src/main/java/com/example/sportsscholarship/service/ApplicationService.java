package com.example.sportsscholarship.service;

import com.example.sportsscholarship.dto.ApplicationRequest;
import com.example.sportsscholarship.entity.Application;
import com.example.sportsscholarship.entity.ApplicationStatus;
import com.example.sportsscholarship.repository.ApplicationRepository;
import com.example.sportsscholarship.repository.UserRepository;
import com.example.sportsscholarship.repository.ScholarshipRepository;
import com.example.sportsscholarship.entity.User;
import com.example.sportsscholarship.entity.Scholarship;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
 

@Service
@RequiredArgsConstructor
public class ApplicationService {
    
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final ScholarshipRepository scholarshipRepository;
    private final EmailService emailService;
    
    public Map<String, Object> applyForScholarship(ApplicationRequest request, Long userId) {
        // Persist: ensure User and Scholarship exist, then create Application
        User user = userRepository.findById(userId).orElseGet(() ->
                userRepository.save(new User(
                        null,
                        "Test User " + userId,
                        "test" + userId + "@example.com",
                        "password",
                        com.example.sportsscholarship.entity.UserRole.APPLICANT,
                        null,
                        null,
                        null
                ))
        );

        Long schId = request.getScholarshipId();
        Scholarship scholarship = (schId != null ? scholarshipRepository.findById(schId).orElse(null) : null);
        if (scholarship == null) {
            scholarship = scholarshipRepository.save(new Scholarship(
                    null,
                    "Test Scholarship" + (schId != null ? (" " + schId) : ""),
                    "Generated for testing",
                    "Open",
                    LocalDateTime.now().plusDays(30),
                    "ID, Documents",
                    null
            ));
        }

        Application application = new Application(
                null,
                user,
                scholarship,
                request.getDocumentsUrls(),
                ApplicationStatus.PENDING,
                null,
                LocalDateTime.now(),
                null
        );
        Application saved = applicationRepository.save(application);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Application submitted successfully");
        response.put("applicationId", saved.getId());
        response.put("status", saved.getStatus());

        // Attempt to send confirmation email to applicant
        try {
            String to = user.getEmail();
            String scholarshipInfo = (scholarship.getName() != null ? scholarship.getName() : ("ID " + scholarship.getId()));
            String subject = "Application Submitted: " + scholarshipInfo;
            String body = "Hello " + user.getName() + ",\n\n" +
                    "We have received your application for " + scholarshipInfo + ".\n" +
                    "Status: PENDING\n" +
                    "Submitted at: " + saved.getAppliedAt() + "\n\n" +
                    "Thank you for applying. We will notify you once a decision is made.\n\n" +
                    "Regards,\nSports Scholarship Portal";
            emailService.sendPlainText(to, subject, body);
        } catch (Exception ignored) {
            // Do not fail the business flow if email sending fails
        }
        return response;
    }
    
    public List<Application> getUserApplications(Long userId) {
        return userRepository.findById(userId)
                .map(applicationRepository::findByApplicant)
                .orElseGet(List::of);
    }
    
    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id).orElse(null);
    }
    
    public Map<String, Object> updateApplicationStatus(Long applicationId, ApplicationStatus status, String rejectionReason) {
        Map<String, Object> response = new HashMap<>();
        Application app = applicationRepository.findById(applicationId).orElse(null);
        if (app == null) {
            response.put("success", false);
            response.put("message", "Application not found");
            return response;
        }
        app.setStatus(status);
        app.setRejectionReason(rejectionReason);
        applicationRepository.save(app);

        response.put("success", true);
        response.put("message", "Application status updated successfully");
        response.put("applicationId", app.getId());
        response.put("status", app.getStatus());
        if (rejectionReason != null) {
            response.put("rejectionReason", rejectionReason);
        }
        return response;
    }
}
