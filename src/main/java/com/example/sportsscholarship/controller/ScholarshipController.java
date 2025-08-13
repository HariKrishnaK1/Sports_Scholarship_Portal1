package com.example.sportsscholarship.controller;

import java.util.List;
import java.util.Map;

import com.example.sportsscholarship.entity.Scholarship;
import com.example.sportsscholarship.service.ScholarshipService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/scholarships")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ScholarshipController {

    private final ScholarshipService scholarshipService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllScholarships() {
        List<Scholarship> scholarships = scholarshipService.getAllScholarships();
        Map<String, Object> response = Map.of(
                "success", true,
                "scholarships", scholarships,
                "count", scholarships.size());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getScholarshipById(@PathVariable Long id) {
        Scholarship scholarship = scholarshipService.getScholarshipById(id);
        Map<String, Object> response = Map.of(
                "success", true,
                "scholarship", scholarship);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createScholarship(@RequestBody Scholarship scholarship) {
        Map<String, Object> response = scholarshipService.createScholarship(scholarship);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchScholarships(@RequestParam String name) {
        // Sample response for search functionality
        Map<String, Object> response = Map.of(
                "success", true,
                "message", "Search functionality will be implemented",
                "searchTerm", name);
        return ResponseEntity.ok(response);
    }
}

