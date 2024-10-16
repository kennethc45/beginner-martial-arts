package org.example.service;

import org.example.repository.MartialArtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.model.MartialArt;
import org.example.dto.MartialArtDTO;

@Service
public class MartialArtService {
    @Autowired
    private MartialArtRepository martialArtRepository;

    public MartialArt addMartialArt(MartialArtDTO martialArtDTO) {
        MartialArt martialArtEntry = MartialArt.builder()
                .name(martialArtDTO.getName())
                .origin(martialArtDTO.getOrigin())
                .build();

        return martialArtRepository.save(martialArtEntry);
    }
}
