package com.example.sportsscholarship.service;

import com.example.sportsscholarship.dto.ApplicationRequest;
import com.example.sportsscholarship.entity.Application;
import com.example.sportsscholarship.entity.ApplicationStatus;
import com.example.sportsscholarship.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    
    private final ApplicationRepository applicationRepository;
    
    public Map<String, Object> applyForScholarship(ApplicationRequest request, Long userId) {
        // Sample response - in real implementation, you would save to database
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Application submitted successfully");
        response.put("applicationId", 1L);
        response.put("status", ApplicationStatus.PENDING);
        return response;
    }
    
    public List<Application> getUserApplications(Long userId) {
        // Sample response - in real implementation, you would fetch from database
        return Arrays.asList(
            new Application(1L, null, null, "documents1.pdf,documents2.pdf", 
                ApplicationStatus.PENDING, null, LocalDateTime.now(), null),
            new Application(2L, null, null, "documents3.pdf", 
                ApplicationStatus.APPROVED, null, LocalDateTime.now().minusDays(5), null)
        );
    }
    
    public Application getApplicationById(Long id) {
        // Sample response - in real implementation, you would fetch from database
        return new Application(id, null, null, "sample-documents.pdf", 
            ApplicationStatus.PENDING, null, LocalDateTime.now(), null);
    }
    
    public Map<String, Object> updateApplicationStatus(Long applicationId, ApplicationStatus status, String rejectionReason) {
        // Sample response - in real implementation, you would update in database
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Application status updated successfully");
        response.put("applicationId", applicationId);
        response.put("status", status);
        if (rejectionReason != null) {
            response.put("rejectionReason", rejectionReason);
        }
        return response;
    }
}
