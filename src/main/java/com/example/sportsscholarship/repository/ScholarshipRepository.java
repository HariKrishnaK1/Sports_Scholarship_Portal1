package com.example.sportsscholarship.repository;

import com.example.sportsscholarship.entity.Scholarship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScholarshipRepository extends JpaRepository<Scholarship, Long> {
    
    List<Scholarship> findByDeadlineAfter(LocalDateTime currentTime);
    
    List<Scholarship> findByNameContainingIgnoreCase(String name);
}
