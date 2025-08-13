package com.example.sportsscholarship.repository;

import com.example.sportsscholarship.entity.CoachVerification;
import com.example.sportsscholarship.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachVerificationRepository extends JpaRepository<CoachVerification, Long> {
    
    List<CoachVerification> findByCoach(User coach);
    
    List<CoachVerification> findByApplicationId(Long applicationId);
    
    List<CoachVerification> findByVerified(boolean verified);
}
