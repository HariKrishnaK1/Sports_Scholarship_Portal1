package com.example.sportsscholarship.config;

import com.example.sportsscholarship.repository.ApplicationRepository;
import com.example.sportsscholarship.repository.CoachVerificationRepository;
import com.example.sportsscholarship.repository.ScholarshipRepository;
import com.example.sportsscholarship.repository.TrainingMaterialRepository;
import com.example.sportsscholarship.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(1) // Run before DataInitializer
public class DatabaseCleanup implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ScholarshipRepository scholarshipRepository;
    private final ApplicationRepository applicationRepository;
    private final CoachVerificationRepository coachVerificationRepository;
    private final TrainingMaterialRepository trainingMaterialRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting database cleanup...");

        // Delete in reverse order to avoid foreign key constraints
        coachVerificationRepository.deleteAll();
        trainingMaterialRepository.deleteAll();
        applicationRepository.deleteAll();
        scholarshipRepository.deleteAll();
        userRepository.deleteAll();

        log.info("Database cleanup completed successfully!");
    }
}
