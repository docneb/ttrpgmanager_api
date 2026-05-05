package com.example.demo.controller;

import com.example.demo.model.PlayerCharacter;
import com.example.demo.repository.PlayerCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        if (character.getCharacterType() == null) {
            character.setCharacterType(PlayerCharacter.CharacterType.PLAYER);
        }
        return ResponseEntity.ok(characterRepository.save(character));
    }

    // Bir oyuncunun tüm karakterlerini getirme (Karakter Seçim ekranı için)
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PlayerCharacter>> getCharactersByUser(
            @PathVariable Long userId,
            @RequestParam(required = false) String type
    ) {
        if (type == null || type.isBlank()) {
            return ResponseEntity.ok(characterRepository.findByUserId(userId));
        }

        final var characterType = parseCharacterType(type);
        return ResponseEntity.ok(characterRepository.findByUserIdAndCharacterType(userId, characterType));
    }

    // Bir oyundaki tüm karakterleri getirme (Oyun içi ekran için)
    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<PlayerCharacter>> getCharactersByGame(
            @PathVariable Long gameId,
            @RequestParam(required = false) String type
    ) {
        if (type == null || type.isBlank()) {
            return ResponseEntity.ok(characterRepository.findByGameId(gameId));
        }

        final var characterType = parseCharacterType(type);
        return ResponseEntity.ok(characterRepository.findByGameIdAndCharacterType(gameId, characterType));
    }

    private PlayerCharacter.CharacterType parseCharacterType(String type) {
        try {
            return PlayerCharacter.CharacterType.valueOf(type.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid character type. Use PLAYER or NPC."
            );
        }
    }
}
