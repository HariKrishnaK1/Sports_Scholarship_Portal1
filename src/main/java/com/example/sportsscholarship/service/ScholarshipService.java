package com.example.sportsscholarship.service;

import com.example.sportsscholarship.entity.Scholarship;
import com.example.sportsscholarship.repository.ScholarshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class ScholarshipService {
    
    private final ScholarshipRepository scholarshipRepository;
    
    public List<Scholarship> getAllScholarships() {
        // Sample response - in real implementation, you would fetch from database
        return Arrays.asList(
            new Scholarship(1L, "Football Excellence Scholarship", 
                "Full scholarship for outstanding football players", 
                "Must be under 18, excellent academic record", 
                LocalDateTime.now().plusMonths(3), 
                "Academic transcripts, sports certificates, recommendation letters", null),
            new Scholarship(2L, "Basketball Talent Scholarship", 
                "Partial scholarship for basketball talents", 
                "Must be under 20, good academic standing", 
                LocalDateTime.now().plusMonths(2), 
                "Sports achievements, academic records", null)
        );
    }
    
    public Scholarship getScholarshipById(Long id) {
        // Sample response - in real implementation, you would fetch from database
        return new Scholarship(id, "Sample Scholarship", 
            "This is a sample scholarship description", 
            "Sample eligibility criteria", 
            LocalDateTime.now().plusMonths(1), 
            "Sample documents required", null);
    }
    
    public Map<String, Object> createScholarship(Scholarship scholarship) {
        // Sample response - in real implementation, you would save to database
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Scholarship created successfully");
        response.put("scholarship", scholarship);
        return response;
    }
}
