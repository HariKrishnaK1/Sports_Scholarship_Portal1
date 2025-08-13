package com.example.sportsscholarship.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;
    
    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL)
    private List<Application> applications;
    
    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL)
    private List<CoachVerification> coachVerifications;
    
    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL)
    private List<TrainingMaterial> trainingMaterials;
}
