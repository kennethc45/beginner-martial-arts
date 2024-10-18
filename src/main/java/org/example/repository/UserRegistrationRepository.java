package org.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.model.UserRegistration;

import java.util.Optional;


public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Long> {
    Optional<UserRegistration> findByUsername(String username);
}
