package com.example.demo.controller;

import com.example.demo.model.Game;
import com.example.demo.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional; // EKLENDİ

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/games")
public class GameController {
    @Autowired
    private GameRepository gameRepository;

    @PostMapping("/create")
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        return ResponseEntity.ok(gameRepository.save(game));
    }

    @GetMapping("/my-games/{gmId}")
    public ResponseEntity<List<Game>> getGamesByGm(@PathVariable Long gmId) {
        return ResponseEntity.ok(gameRepository.findByGmId(gmId));
    }

    // GameController.java içindeki updateGame metodu
    @PutMapping("/{id}")
    public ResponseEntity<?> updateGame(@PathVariable Long id, @RequestBody Game updatedGameDetails) {
        Optional<Game> gameOptional = gameRepository.findById(id);

        if (gameOptional.isPresent()) {
            Game existingGame = gameOptional.get();
            existingGame.setTitle(updatedGameDetails.getTitle());
            existingGame.setDescription(updatedGameDetails.getDescription());
            existingGame.setMaxPlayers(updatedGameDetails.getMaxPlayers());

            // Yeni metod isimleri: setPublicGame ve isPublicGame
            existingGame.setPublicGame(updatedGameDetails.isPublicGame());

            gameRepository.save(existingGame);
            return ResponseEntity.ok(existingGame);
        }
        return ResponseEntity.notFound().build();
    }
}