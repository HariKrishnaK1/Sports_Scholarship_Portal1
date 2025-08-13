package com.example.sportsscholarship.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "coach_verifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoachVerification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "coach_id", nullable = false)
    private User coach;
    
    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;
    
    @Column(nullable = false)
    private boolean verified = false;
    
    @Column(columnDefinition = "TEXT")
    private String comments;
    
    @Column(nullable = false)
    private LocalDateTime verifiedAt = LocalDateTime.now();
}
