package com.example.sportsscholarship.repository;

import com.example.sportsscholarship.entity.User;
import com.example.sportsscholarship.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
    
    List<User> findByRole(UserRole role);
    
    boolean existsByEmail(String email);
}
