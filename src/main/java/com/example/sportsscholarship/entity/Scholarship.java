package com.example.sportsscholarship.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "scholarships")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scholarship {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(columnDefinition = "TEXT")
    private String eligibilityCriteria;
    
    @Column(nullable = false)
    private LocalDateTime deadline;
    
    @Column(columnDefinition = "TEXT")
    private String documentsRequired;
    
    @OneToMany(mappedBy = "scholarship", cascade = CascadeType.ALL)
    private List<Application> applications;
}
