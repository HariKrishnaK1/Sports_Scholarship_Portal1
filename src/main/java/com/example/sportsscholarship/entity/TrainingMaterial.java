package com.example.sportsscholarship.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "training_materials")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingMaterial {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "coach_id", nullable = false)
    private User coach;
    
    @Column(nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(nullable = false)
    private String resourceUrl;
    
    @Column(nullable = false)
    private LocalDateTime uploadedAt = LocalDateTime.now();
}
