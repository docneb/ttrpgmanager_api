package com.example.demo.controller;

import com.example.demo.model.PlayerCharacter;
import com.example.demo.repository.PlayerCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/characters")
public class PlayerCharacterController {

    @Autowired
    private PlayerCharacterRepository characterRepository;

    // Yeni karakter oluşturma (Oyuncu kendi havuzuna ekler, henüz bir oyuna bağlı değildir)
    @PostMapping("/create")
    public ResponseEntity<PlayerCharacter> createCharacter(@RequestBody PlayerCharacter character) {
        return ResponseEntity.ok(characterRepository.save(character));
    }

    // Bir oyuncunun tüm karakterlerini getirme (Karakter Seçim ekranı için)
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PlayerCharacter>> getCharactersByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(characterRepository.findByUserId(userId));
    }

    // Bir oyundaki tüm karakterleri getirme (Oyun içi ekran için)
    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<PlayerCharacter>> getCharactersByGame(@PathVariable Long gameId) {
        return ResponseEntity.ok(characterRepository.findByGameId(gameId));
    }
}