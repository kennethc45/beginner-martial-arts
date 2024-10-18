package org.example.service;

import org.example.dto.UserRegistrationDTO;
import org.example.exception.UserAlreadyExistsException;
import org.example.model.UserRegistration;
import org.example.repository.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserRegistrationService {
    private static final Logger logger = LoggerFactory.getLogger(UserRegistrationService.class);
    @Autowired
    private UserRegistrationRepository userRegistrationRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserRegistration registerUser(UserRegistrationDTO userRegistrationDTO) {
        if(userRegistrationDTO.getUsername() == null || userRegistrationDTO.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if(userRegistrationDTO.getPassword() == null || userRegistrationDTO.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        if (userRegistrationRepository.findByUsername(userRegistrationDTO.getUsername()).isPresent()) {
//            throw new RuntimeException("Username already exists");
            logger.warn("Attempt to register an existing user: {}", userRegistrationDTO.getUsername());
            throw new UserAlreadyExistsException("Username already exists");
        }

        if ((userRegistrationDTO.getPassword()).length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters");
        }

        if (!userRegistrationDTO.getPassword().matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            throw new IllegalArgumentException("Password must contain at least one special character");
        }

        UserRegistration newUserRegistration = UserRegistration.builder()
                .username(userRegistrationDTO.getUsername())
                .password(passwordEncoder.encode(userRegistrationDTO.getPassword()))
                .role("ROLE_USER")
                .build();

        return userRegistrationRepository.save(newUserRegistration);
    }
}
