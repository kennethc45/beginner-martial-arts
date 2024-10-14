package org.example.repository;

import org.example.model.MartialArt;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class MartialArtRepositoryTests {
    @Autowired
    private MartialArtRepository martialArtRepository;

//    @Test
//    public void MartialArtRepository_SaveAll_ReturnSavedMartialArt() {
//
//        // Arrange
//        MartialArt martialArt = MartialArt.builder()
//                .name("Muay Thai")
//                .origin("Thailand").build();
//
//        // Act
//        MartialArt savedMartialArt = martialArtRepository.save(martialArt);
//
//        // Assert
//        assertThat(savedMartialArt).isNotNull();
//        assertThat(savedMartialArt.getId()).isGreaterThan(0);
//        assertThat(savedMartialArt.getName()).isEqualTo("Muay Thai");
//        assertThat(savedMartialArt.getOrigin()).isEqualTo("Thailand");
//
//    }
}
