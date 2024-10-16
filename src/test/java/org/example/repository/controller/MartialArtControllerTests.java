package org.example.repository.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.controller.MartialArtController;
import org.example.dto.MartialArtDTO;
import org.example.service.MartialArtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.example.model.MartialArt;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MartialArtController.class)
@ActiveProfiles("test")
public class MartialArtControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MartialArtService martialArtService;

    @Autowired
    private ObjectMapper objectMapper;

    private MartialArtDTO martialArtDTO;
    private MartialArt savedMartialArt;

    @BeforeEach
    public void setup() {
        martialArtDTO = new MartialArtDTO();
        martialArtDTO.setName("Muay Thai");
        martialArtDTO.setOrigin("Thailand");

        savedMartialArt = MartialArt.builder()
                .id(1L)
                .name("Muay Thai")
                .origin("Thailand")
                .build();

        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser
    public void testCreateMartialArt() throws Exception {
        // Mock the service layer
        when(martialArtService.addMartialArt(any(MartialArtDTO.class))).thenReturn(savedMartialArt);

        // Perform the request and check the response
        mockMvc.perform(post("/api/martial-arts")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(martialArtDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Muay Thai"))
                .andExpect(jsonPath("$.origin").value("Thailand"));


    }
}
