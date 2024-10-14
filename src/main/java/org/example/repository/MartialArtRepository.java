package org.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.model.MartialArt;
public interface MartialArtRepository extends JpaRepository<MartialArt, Long> {
    // Extending JpaRepository brings custom CRUD operations
    // Define custom query methods here if needed
}
