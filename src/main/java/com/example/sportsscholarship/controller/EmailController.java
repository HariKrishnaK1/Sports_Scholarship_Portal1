package com.example.sportsscholarship.controller;

import com.example.sportsscholarship.service.EmailService;
import com.example.sportsscholarship.service.InMemoryOutbox;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;
    private final InMemoryOutbox outbox;

    @PostMapping("/test-email")
    public ResponseEntity<Map<String, Object>> sendTestEmail(
            @RequestParam("to") String to,
            @RequestParam(value = "subject", required = false) String subject,
            @RequestParam(value = "body", required = false) String body
    ) {
        Map<String, Object> resp = new HashMap<>();
        try {
            String subj = (subject != null && !subject.isBlank()) ? subject : "Test Email - Sports Scholarship Portal";
            String msg = (body != null && !body.isBlank()) ? body : "This is a test email from Sports Scholarship Portal.";
            emailService.sendPlainText(to, subj, msg);
            resp.put("success", true);
            resp.put("message", "Email attempted to send");
            return ResponseEntity.ok(resp);
        } catch (Exception ex) {
            resp.put("success", false);
            resp.put("message", ex.getMessage());
            return ResponseEntity.internalServerError().body(resp);
        }
    }

    @GetMapping("/mock-emails")
    public ResponseEntity<Object> listMockEmails() {
        return ResponseEntity.ok(outbox.list());
    }

    @DeleteMapping("/mock-emails")
    public ResponseEntity<Object> clearMockEmails() {
        outbox.clear();
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("message", "Mock outbox cleared");
        return ResponseEntity.ok(resp);
    }
}
