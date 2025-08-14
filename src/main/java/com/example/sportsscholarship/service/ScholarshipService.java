package com.example.sportsscholarship.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.sportsscholarship.entity.Scholarship;
import com.example.sportsscholarship.repository.ScholarshipRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScholarshipService {

    private final ScholarshipRepository scholarshipRepository;

    public List<Scholarship> getAllScholarships() {
        return scholarshipRepository.findAll();
    }

    public Scholarship getScholarshipById(Long id) {
        return scholarshipRepository.findById(id).orElse(null);
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
