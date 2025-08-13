package com.example.sportsscholarship.repository;

import com.example.sportsscholarship.entity.TrainingMaterial;
import com.example.sportsscholarship.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingMaterialRepository extends JpaRepository<TrainingMaterial, Long> {
    
    List<TrainingMaterial> findByCoach(User coach);
    
    List<TrainingMaterial> findByTitleContainingIgnoreCase(String title);
}
