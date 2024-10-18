package org.example.repository.service;

import org.example.dto.UserRegistrationDTO;
import org.example.exception.UserAlreadyExistsException;
import org.example.model.UserRegistration;
import org.example.repository.UserRegistrationRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRegistrationService {
    @Mock
    private UserRegistrationRepository userRegistrationRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private org.example.service.UserRegistrationService userRegistrationService;

    private UserRegistrationDTO userRegistrationDTO;
    private UserRegistration savedUser;

    @BeforeEach
    public void setUp() {
        userRegistrationDTO = new UserRegistrationDTO();
        userRegistrationDTO.setUsername("testUser");
        userRegistrationDTO.setPassword("password123$");

        savedUser = UserRegistration.builder()
                .username("testUser")
                .password("encodedPassword")
                .role("ROLE_USER")
                .build();
    }

    @Test
    @WithMockUser
    public void testRegisterUser_Success() {
        when(userRegistrationRepository.findByUsername(userRegistrationDTO.getUsername())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(userRegistrationDTO.getPassword())).thenReturn("encodedPassword");
        when(userRegistrationRepository.save(any(UserRegistration.class))).thenReturn(savedUser);

        UserRegistration registeredUser = userRegistrationService.registerUser(userRegistrationDTO);

        verify(userRegistrationRepository).findByUsername(userRegistrationDTO.getUsername());
        verify(userRegistrationRepository).save(any(UserRegistration.class));
        verify(passwordEncoder).encode(userRegistrationDTO.getPassword());


        assertEquals("testUser", registeredUser.getUsername());
        assertEquals("ROLE_USER", registeredUser.getRole());
        assertEquals("encodedPassword", registeredUser.getPassword());
    }


    @Test
    @WithMockUser
    public void testRegisterUser_UsernameAlreadyExists() {
        when(userRegistrationRepository.findByUsername(userRegistrationDTO.getUsername())).thenReturn(Optional.of(savedUser));
//        assertThrows(UserAlreadyExistsException.class, () -> userRegistrationService.registerUser(userRegistrationDTO));
        UserAlreadyExistsException usernameExistsException = assertThrows(UserAlreadyExistsException.class,
                () -> userRegistrationService.registerUser(userRegistrationDTO));

        assertEquals("Username already exists", usernameExistsException.getMessage());

        verify(userRegistrationRepository).findByUsername(userRegistrationDTO.getUsername());
        verify(userRegistrationRepository, never()).save(any(UserRegistration.class));
    }

    @Test
    @WithMockUser
    public void testRegisterUser_NullOrEmptyUsername() {
        userRegistrationDTO.setUsername(null);
        IllegalArgumentException usernameNullException = assertThrows(IllegalArgumentException.class,
                () -> userRegistrationService.registerUser(userRegistrationDTO));

        assertEquals("Username cannot be empty", usernameNullException.getMessage());
        verify(userRegistrationRepository, never()).save(any(UserRegistration.class));


        userRegistrationDTO.setUsername("");
        IllegalArgumentException usernameEmptyException = assertThrows(IllegalArgumentException.class,
                () -> userRegistrationService.registerUser(userRegistrationDTO));

        assertEquals("Username cannot be empty", usernameEmptyException.getMessage());
        verify(userRegistrationRepository, never()).save(any(UserRegistration.class));
    }

    @Test
    @WithMockUser
    public void testRegisterUser_NullOrEmptyPassword() {
        userRegistrationDTO.setPassword(null);
        IllegalArgumentException passwordNullException = assertThrows(IllegalArgumentException.class,
                () -> userRegistrationService.registerUser(userRegistrationDTO));

        assertEquals("Password cannot be empty", passwordNullException.getMessage());
        verify(userRegistrationRepository, never()).save(any(UserRegistration.class));

        userRegistrationDTO.setPassword("");
        IllegalArgumentException passwordEmptyException = assertThrows(IllegalArgumentException.class,
                () -> userRegistrationService.registerUser(userRegistrationDTO));

        assertEquals("Password cannot be empty", passwordEmptyException.getMessage());
        verify(userRegistrationRepository, never()).save(any(UserRegistration.class));
    }

    @Test
    @WithMockUser
    public void testRegisterUser_PasswordTooShort() {
        userRegistrationDTO.setPassword("short");
        IllegalArgumentException shortPasswordexception = assertThrows(IllegalArgumentException.class,
                () -> userRegistrationService.registerUser(userRegistrationDTO));

        assertEquals("Password must be at least 8 characters", shortPasswordexception.getMessage());

        verify(userRegistrationRepository, never()).save(any(UserRegistration.class));
    }

    @Test
    @WithMockUser
    public void testRegisterUser_NoSpecialCharPassword() {
        userRegistrationDTO.setPassword("boringPassword");
        IllegalArgumentException noSpecialCharexception = assertThrows(IllegalArgumentException.class,
                () -> userRegistrationService.registerUser(userRegistrationDTO));

        assertEquals("Password must contain at least one special character", noSpecialCharexception.getMessage());

        verify(userRegistrationRepository, never()).save(any(UserRegistration.class));
    }

}
