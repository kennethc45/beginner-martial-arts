package org.example.controller;

import org.example.dto.UserRegistrationDTO;
import org.example.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user_registration")
public class RegistrationController {
    @Autowired
    private UserRegistrationService userRegistrationService;

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        userRegistrationService.registerUser(userRegistrationDTO);
        return ResponseEntity.ok("User Registered Successfully");
    }
}
