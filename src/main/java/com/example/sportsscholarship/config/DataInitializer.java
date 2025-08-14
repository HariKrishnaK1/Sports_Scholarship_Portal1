package com.example.sportsscholarship.config;

import java.time.LocalDateTime;

import com.example.sportsscholarship.entity.Application;
import com.example.sportsscholarship.entity.ApplicationStatus;
import com.example.sportsscholarship.entity.CoachVerification;
import com.example.sportsscholarship.entity.Scholarship;
import com.example.sportsscholarship.entity.TrainingMaterial;
import com.example.sportsscholarship.entity.User;
import com.example.sportsscholarship.entity.UserRole;
import com.example.sportsscholarship.repository.ApplicationRepository;
import com.example.sportsscholarship.repository.CoachVerificationRepository;
import com.example.sportsscholarship.repository.ScholarshipRepository;
import com.example.sportsscholarship.repository.TrainingMaterialRepository;
import com.example.sportsscholarship.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(2) // Run after DatabaseCleanup
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ScholarshipRepository scholarshipRepository;
    private final ApplicationRepository applicationRepository;
    private final CoachVerificationRepository coachVerificationRepository;
    private final TrainingMaterialRepository trainingMaterialRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting data initialization...");

        // Initialize users
        initializeUsers();

        // Initialize scholarships
        initializeScholarships();

        // Initialize applications
        initializeApplications();

        // Initialize coach verifications
        initializeCoachVerifications();

        // Initialize training materials
        initializeTrainingMaterials();

        log.info("Data initialization completed successfully!");
    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {
            log.info("Creating sample users...");

            User admin = new User();
            admin.setName("Admin User");
            admin.setEmail("admin@sportsscholarship.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(UserRole.ADMIN);
            userRepository.save(admin);

            User coach1 = new User();
            coach1.setName("Coach John Smith");
            coach1.setEmail("coach.john@sportsscholarship.com");
            coach1.setPassword(passwordEncoder.encode("coach123"));
            coach1.setRole(UserRole.COACH);
            userRepository.save(coach1);

            User coach2 = new User();
            coach2.setName("Coach Sarah Johnson");
            coach2.setEmail("coach.sarah@sportsscholarship.com");
            coach2.setPassword(passwordEncoder.encode("coach123"));
            coach2.setRole(UserRole.COACH);
            userRepository.save(coach2);

            User applicant1 = new User();
            applicant1.setName("Alex Rodriguez");
            applicant1.setEmail("alex.rodriguez@email.com");
            applicant1.setPassword(passwordEncoder.encode("applicant123"));
            applicant1.setRole(UserRole.APPLICANT);
            userRepository.save(applicant1);

            User applicant2 = new User();
            applicant2.setName("Maria Garcia");
            applicant2.setEmail("maria.garcia@email.com");
            applicant2.setPassword(passwordEncoder.encode("applicant123"));
            applicant2.setRole(UserRole.APPLICANT);
            userRepository.save(applicant2);

            User applicant3 = new User();
            applicant3.setName("David Chen");
            applicant3.setEmail("david.chen@email.com");
            applicant3.setPassword(passwordEncoder.encode("applicant123"));
            applicant3.setRole(UserRole.APPLICANT);
            userRepository.save(applicant3);
        }
    }

    private void initializeScholarships() {
        if (scholarshipRepository.count() == 0) {
            log.info("Creating sample scholarships...");

            Scholarship scholarship1 = new Scholarship();
            scholarship1.setName("Football Excellence Scholarship");
            scholarship1.setDescription(
                    "Full scholarship for outstanding football players with exceptional talent and academic performance.");
            scholarship1.setEligibilityCriteria(
                    "Must be under 18 years old, maintain GPA of 3.5+, demonstrate exceptional football skills");
            scholarship1.setDeadline(LocalDateTime.now().plusMonths(3));
            scholarship1.setDocumentsRequired(
                    "Academic transcripts, sports certificates, recommendation letters, video highlights");
            scholarshipRepository.save(scholarship1);

            Scholarship scholarship2 = new Scholarship();
            scholarship2.setName("Basketball Talent Scholarship");
            scholarship2.setDescription("Partial scholarship for basketball talents with strong academic background.");
            scholarship2.setEligibilityCriteria(
                    "Must be under 20 years old, maintain GPA of 3.0+, demonstrate basketball skills");
            scholarship2.setDeadline(LocalDateTime.now().plusMonths(2));
            scholarship2.setDocumentsRequired("Academic records, sports achievements, coach recommendations");
            scholarshipRepository.save(scholarship2);

            Scholarship scholarship3 = new Scholarship();
            scholarship3.setName("Swimming Excellence Award");
            scholarship3.setDescription("Full scholarship for competitive swimmers with national level achievements.");
            scholarship3.setEligibilityCriteria(
                    "Must be under 22 years old, maintain GPA of 3.2+, national level swimming achievements");
            scholarship3.setDeadline(LocalDateTime.now().plusMonths(4));
            scholarship3.setDocumentsRequired("Swimming certificates, academic transcripts, competition records");
            scholarshipRepository.save(scholarship3);

            Scholarship scholarship4 = new Scholarship();
            scholarship4.setName("Tennis Prodigy Scholarship");
            scholarship4.setDescription("Scholarship for young tennis players with exceptional potential.");
            scholarship4.setEligibilityCriteria(
                    "Must be under 16 years old, maintain GPA of 3.3+, demonstrate tennis skills");
            scholarship4.setDeadline(LocalDateTime.now().plusMonths(1));
            scholarship4.setDocumentsRequired("Tennis achievements, academic records, coach recommendations");
            scholarshipRepository.save(scholarship4);
        }
    }

    private void initializeApplications() {
        if (applicationRepository.count() == 0) {
            log.info("Creating sample applications...");

            User applicant1 = userRepository.findByEmail("alex.rodriguez@email.com").orElse(null);
            User applicant2 = userRepository.findByEmail("maria.garcia@email.com").orElse(null);
            User applicant3 = userRepository.findByEmail("david.chen@email.com").orElse(null);

            // Get scholarships by name using findAll and filter
            Scholarship scholarship1 = scholarshipRepository.findAll().stream()
                    .filter(s -> s.getName().equals("Football Excellence Scholarship"))
                    .findFirst().orElse(null);
            Scholarship scholarship2 = scholarshipRepository.findAll().stream()
                    .filter(s -> s.getName().equals("Basketball Talent Scholarship"))
                    .findFirst().orElse(null);
            Scholarship scholarship3 = scholarshipRepository.findAll().stream()
                    .filter(s -> s.getName().equals("Swimming Excellence Award"))
                    .findFirst().orElse(null);

            if (applicant1 != null && scholarship1 != null) {
                Application app1 = new Application();
                app1.setApplicant(applicant1);
                app1.setScholarship(scholarship1);
                app1.setDocumentsUrls("academic_transcript.pdf,football_certificate.pdf,recommendation_letter.pdf");
                app1.setStatus(ApplicationStatus.PENDING);
                app1.setAppliedAt(LocalDateTime.now().minusDays(5));
                applicationRepository.save(app1);
            }

            if (applicant2 != null && scholarship2 != null) {
                Application app2 = new Application();
                app2.setApplicant(applicant2);
                app2.setScholarship(scholarship2);
                app2.setDocumentsUrls("academic_records.pdf,basketball_achievements.pdf");
                app2.setStatus(ApplicationStatus.APPROVED);
                app2.setAppliedAt(LocalDateTime.now().minusDays(10));
                applicationRepository.save(app2);
            }

            if (applicant3 != null && scholarship3 != null) {
                Application app3 = new Application();
                app3.setApplicant(applicant3);
                app3.setScholarship(scholarship3);
                app3.setDocumentsUrls("swimming_certificates.pdf,academic_transcript.pdf");
                app3.setStatus(ApplicationStatus.REJECTED);
                app3.setRejectionReason("Incomplete documentation and insufficient swimming achievements");
                app3.setAppliedAt(LocalDateTime.now().minusDays(15));
                applicationRepository.save(app3);
            }
        }
    }

    private void initializeCoachVerifications() {
        if (coachVerificationRepository.count() == 0) {
            log.info("Creating sample coach verifications...");

            User coach1 = userRepository.findByEmail("coach.john@sportsscholarship.com").orElse(null);
            User coach2 = userRepository.findByEmail("coach.sarah@sportsscholarship.com").orElse(null);

            Application app1 = applicationRepository.findAll().stream().findFirst().orElse(null);
            Application app2 = applicationRepository.findAll().stream().skip(1).findFirst().orElse(null);

            if (coach1 != null && app1 != null) {
                CoachVerification verification1 = new CoachVerification();
                verification1.setCoach(coach1);
                verification1.setApplication(app1);
                verification1.setVerified(true);
                verification1.setComments("Excellent football skills demonstrated. Strong recommendation.");
                verification1.setVerifiedAt(LocalDateTime.now().minusDays(3));
                coachVerificationRepository.save(verification1);
            }

            if (coach2 != null && app2 != null) {
                CoachVerification verification2 = new CoachVerification();
                verification2.setCoach(coach2);
                verification2.setApplication(app2);
                verification2.setVerified(true);
                verification2.setComments("Outstanding basketball talent. Highly recommended for scholarship.");
                verification2.setVerifiedAt(LocalDateTime.now().minusDays(8));
                coachVerificationRepository.save(verification2);
            }
        }
    }

    private void initializeTrainingMaterials() {
        if (trainingMaterialRepository.count() == 0) {
            log.info("Creating sample training materials...");

            User coach1 = userRepository.findByEmail("coach.john@sportsscholarship.com").orElse(null);
            User coach2 = userRepository.findByEmail("coach.sarah@sportsscholarship.com").orElse(null);

            if (coach1 != null) {
                TrainingMaterial material1 = new TrainingMaterial();
                material1.setCoach(coach1);
                material1.setTitle("Football Training Program - Advanced Level");
                material1.setDescription("Comprehensive training program for advanced football players");
                material1.setResourceUrl("training_materials/football_advanced_program.pdf");
                material1.setUploadedAt(LocalDateTime.now().minusDays(5));
                trainingMaterialRepository.save(material1);

                TrainingMaterial material2 = new TrainingMaterial();
                material2.setCoach(coach1);
                material2.setTitle("Football Skills Assessment Guide");
                material2.setDescription("Guide for assessing football skills and techniques");
                material2.setResourceUrl("training_materials/football_assessment_guide.pdf");
                material2.setUploadedAt(LocalDateTime.now().minusDays(3));
                trainingMaterialRepository.save(material2);
            }

            if (coach2 != null) {
                TrainingMaterial material3 = new TrainingMaterial();
                material3.setCoach(coach2);
                material3.setTitle("Basketball Training Fundamentals");
                material3.setDescription("Basic to intermediate basketball training program");
                material3.setResourceUrl("training_materials/basketball_fundamentals.pdf");
                material3.setUploadedAt(LocalDateTime.now().minusDays(7));
                trainingMaterialRepository.save(material3);

                TrainingMaterial material4 = new TrainingMaterial();
                material4.setCoach(coach2);
                material4.setTitle("Basketball Shooting Techniques");
                material4.setDescription("Advanced shooting techniques and drills");
                material4.setResourceUrl("training_materials/basketball_shooting_guide.pdf");
                material4.setUploadedAt(LocalDateTime.now().minusDays(2));
                trainingMaterialRepository.save(material4);
            }
        }
    }
}
