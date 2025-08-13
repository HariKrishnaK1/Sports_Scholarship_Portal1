package com.example.sportsscholarship.repository;

import com.example.sportsscholarship.entity.Application;
import com.example.sportsscholarship.entity.ApplicationStatus;
import com.example.sportsscholarship.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    
    List<Application> findByApplicant(User applicant);
    
    List<Application> findByStatus(ApplicationStatus status);
    
    List<Application> findByApplicantAndStatus(User applicant, ApplicationStatus status);
    
    List<Application> findByScholarshipId(Long scholarshipId);
}
