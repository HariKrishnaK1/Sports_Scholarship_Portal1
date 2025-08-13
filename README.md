# Sports Scholarship Portal

A Spring Boot application for managing sports scholarships, applications, and training materials.

## Features

- **User Management**: Register and login for applicants, coaches, and admins
- **Scholarship Management**: Create and manage scholarship opportunities
- **Application Processing**: Apply for scholarships and track application status
- **Coach Verification**: Coaches can verify applications and upload training materials
- **Admin Dashboard**: Comprehensive admin panel for managing the entire system

## Technology Stack

- **Backend**: Spring Boot 3.4.8
- **Database**: MySQL
- **ORM**: Spring Data JPA with Hibernate
- **Build Tool**: Maven
- **Java Version**: 17
- **Additional Libraries**: Lombok, Spring Boot DevTools

## Project Structure

```
com.example.sportsscholarship/
├── entity/                    # JPA entities
│   ├── User.java
│   ├── Scholarship.java
│   ├── Application.java
│   ├── CoachVerification.java
│   ├── TrainingMaterial.java
│   ├── UserRole.java
│   └── ApplicationStatus.java
├── controller/                # REST controllers
│   ├── AuthController.java
│   ├── ScholarshipController.java
│   ├── ApplicationController.java
│   ├── CoachController.java
│   └── AdminController.java
├── service/                   # Business logic
│   ├── UserService.java
│   ├── ScholarshipService.java
│   ├── ApplicationService.java
│   ├── CoachService.java
│   └── AdminService.java
├── repository/                # Data access layer
│   ├── UserRepository.java
│   ├── ScholarshipRepository.java
│   ├── ApplicationRepository.java
│   ├── CoachVerificationRepository.java
│   └── TrainingMaterialRepository.java
├── dto/                       # Data transfer objects
│   ├── UserDto.java
│   ├── LoginRequest.java
│   ├── RegisterRequest.java
│   └── ApplicationRequest.java
└── config/                    # Configuration classes
    └── WebConfig.java
```

## Database Configuration

Update the `application.properties` file with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sportsscholarshipdb
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
```

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - User login
- `GET /api/auth/user/{id}` - Get user details

### Scholarships
- `GET /api/scholarships` - Get all scholarships
- `GET /api/scholarships/{id}` - Get scholarship by ID
- `POST /api/scholarships` - Create new scholarship
- `GET /api/scholarships/search` - Search scholarships

### Applications
- `POST /api/applications` - Apply for scholarship
- `GET /api/applications/user/{userId}` - Get user applications
- `GET /api/applications/{id}` - Get application by ID
- `GET /api/applications/status/{userId}` - Track application status
- `DELETE /api/applications/{id}` - Withdraw application

### Coach Operations
- `POST /api/coach/verify/{applicationId}` - Verify application
- `GET /api/coach/verifications/{coachId}` - Get coach verifications
- `POST /api/coach/training-materials` - Upload training material
- `GET /api/coach/training-materials/{coachId}` - Get coach materials
- `DELETE /api/coach/training-materials/{materialId}` - Delete material
- `GET /api/coach/dashboard/{coachId}` - Coach dashboard

### Admin Operations
- `GET /api/admin/applications` - Get all applications
- `POST /api/admin/applications/{id}/approve` - Approve application
- `POST /api/admin/applications/{id}/reject` - Reject application
- `GET /api/admin/users` - Get all users
- `GET /api/admin/dashboard` - Admin dashboard stats
- `GET /api/admin/applications/status/{status}` - Filter by status
- `DELETE /api/admin/users/{id}` - Delete user

## Sample JSON Responses

### Register Response
```json
{
  "success": true,
  "message": "User registered successfully",
  "user": {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "role": "APPLICANT"
  }
}
```

### Login Response
```json
{
  "success": true,
  "message": "Login successful",
  "user": {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "role": "APPLICANT"
  },
  "token": "sample-jwt-token-12345"
}
```

### Scholarship List Response
```json
{
  "success": true,
  "scholarships": [
    {
      "id": 1,
      "name": "Football Excellence Scholarship",
      "description": "Full scholarship for outstanding football players",
      "eligibilityCriteria": "Must be under 18, excellent academic record",
      "deadline": "2024-06-30T23:59:59",
      "documentsRequired": "Academic transcripts, sports certificates, recommendation letters"
    }
  ],
  "count": 1
}
```

## Running the Application

1. **Prerequisites**:
   - Java 17 or higher
   - MySQL 8.0 or higher
   - Maven 3.6 or higher

2. **Database Setup**:
   ```sql
   CREATE DATABASE sportsscholarshipdb;
   ```

3. **Build and Run**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access the Application**:
   - The application will start on `http://localhost:8080`
   - API endpoints are available at `http://localhost:8080/api/`

## Development Notes

- This is a sample implementation with mock responses
- Real business logic needs to be implemented in service classes
- Database operations are currently returning sample data
- Authentication and authorization need to be implemented
- File upload functionality for documents needs to be added
- Input validation and error handling need to be enhanced

## Future Enhancements

- JWT-based authentication
- File upload for documents
- Email notifications
- Advanced search and filtering
- Reporting and analytics
- Mobile app support
- Payment integration
- Real-time notifications

