package org.example.controller;

import org.example.service.MartialArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.model.MartialArt;
import org.example.dto.MartialArtDTO;

@RestController
@RequestMapping("/api/martial-arts")
public class MartialArtController {
    @Autowired
    private MartialArtService martialArtService;

//    @PostMapping
//    public MartialArt createMartialArt(@RequestBody MartialArtDTO martialArtDTO) {
//        return martialArtService.addMartialArt(martialArtDTO);
//    }
    @PostMapping
    public ResponseEntity<MartialArt> createMartialArt(@RequestBody MartialArtDTO martialArtDTO) {
        MartialArt savedMartialArt = martialArtService.addMartialArt(martialArtDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMartialArt);
    }
}
