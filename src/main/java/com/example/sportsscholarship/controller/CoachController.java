package com.example.sportsscholarship.controller;

import java.util.List;
import java.util.Map;

import com.example.sportsscholarship.entity.CoachVerification;
import com.example.sportsscholarship.entity.TrainingMaterial;
import com.example.sportsscholarship.service.CoachService;

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
@RequestMapping("/api/coach")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CoachController {

    private final CoachService coachService;

    @PostMapping("/verify/{applicationId}")
    public ResponseEntity<Map<String, Object>> verifyApplication(
            @PathVariable Long applicationId,
            @RequestParam Long coachId,
            @RequestParam boolean verified,
            @RequestParam(required = false) String comments) {
        Map<String, Object> response = coachService.verifyApplication(applicationId, coachId, verified, comments);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/verifications/{coachId}")
    public ResponseEntity<Map<String, Object>> getCoachVerifications(@PathVariable Long coachId) {
        List<CoachVerification> verifications = coachService.getCoachVerifications(coachId);
        Map<String, Object> response = Map.of(
                "success", true,
                "verifications", verifications,
                "count", verifications.size());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/training-materials")
    public ResponseEntity<Map<String, Object>> uploadTrainingMaterial(@RequestBody TrainingMaterial material) {
        Map<String, Object> response = coachService.uploadTrainingMaterial(material);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/training-materials/{coachId}")
    public ResponseEntity<Map<String, Object>> getCoachTrainingMaterials(@PathVariable Long coachId) {
        List<TrainingMaterial> materials = coachService.getCoachTrainingMaterials(coachId);
        Map<String, Object> response = Map.of(
                "success", true,
                "materials", materials,
                "count", materials.size());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/training-materials/{materialId}")
    public ResponseEntity<Map<String, Object>> deleteTrainingMaterial(@PathVariable Long materialId) {
        // Sample response for deleting training material
        Map<String, Object> response = Map.of(
                "success", true,
                "message", "Training material deleted successfully",
                "materialId", materialId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/dashboard/{coachId}")
    public ResponseEntity<Map<String, Object>> getCoachDashboard(@PathVariable Long coachId) {
        // Sample response for coach dashboard
        Map<String, Object> response = Map.of(
                "success", true,
                "coachId", coachId,
                "pendingVerifications", 5,
                "totalVerifications", 25,
                "uploadedMaterials", 8);
        return ResponseEntity.ok(response);
    }
}

