package com.example.demo.controller;

import com.example.demo.model.Game;
import com.example.demo.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/games")
public class GameController {
    @Autowired
    private GameRepository gameRepository;

    // YENİ: 6 Haneli rastgele davet kodu üreten yardımcı metot
    private String generateInviteCode() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    @PostMapping("/create")
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        // Oyun veritabanına kaydedilmeden önce rastgele kod ata
        game.setInviteCode(generateInviteCode());
        return ResponseEntity.ok(gameRepository.save(game));
    }

    @GetMapping("/my-games/{gmId}")
    public ResponseEntity<List<Game>> getGamesByGm(@PathVariable Long gmId) {
        return ResponseEntity.ok(gameRepository.findByGmId(gmId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGame(@PathVariable Long id, @RequestBody Game updatedGameDetails) {
        Optional<Game> gameOptional = gameRepository.findById(id);

        if (gameOptional.isPresent()) {
            Game existingGame = gameOptional.get();
            existingGame.setTitle(updatedGameDetails.getTitle());
            existingGame.setDescription(updatedGameDetails.getDescription());
            existingGame.setMaxPlayers(updatedGameDetails.getMaxPlayers());
            existingGame.setPublicGame(updatedGameDetails.isPublicGame());

            gameRepository.save(existingGame);
            return ResponseEntity.ok(existingGame);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/public")
    public ResponseEntity<List<Game>> getPublicGames() {
        return ResponseEntity.ok(gameRepository.findByPublicGameTrue());
    }

    @GetMapping("/joined/{playerId}")
    public ResponseEntity<List<Game>> getJoinedGames(@PathVariable Long playerId) {
        return ResponseEntity.ok(gameRepository.findGamesByPlayerId(playerId));
    }

    // ESKİ: Sadece Oyun ID'si ile katılma (Bunu public oyunlar için tutuyoruz)
    @PostMapping("/{gameId}/join/{playerId}")
    public ResponseEntity<?> joinGame(@PathVariable Long gameId, @PathVariable Long playerId) {
        return processJoinGame(gameRepository.findById(gameId), playerId);
    }

    // YENİ: Davet Kodu (Invite Code) ile katılma
    @PostMapping("/join-by-code/{inviteCode}/{playerId}")
    public ResponseEntity<?> joinGameByCode(@PathVariable String inviteCode, @PathVariable Long playerId) {
        return processJoinGame(gameRepository.findByInviteCode(inviteCode.toUpperCase()), playerId);
    }

    // Kod tekrarını önlemek için yazılmış yardımcı katılma metodu
    private ResponseEntity<?> processJoinGame(Optional<Game> gameOptional, Long playerId) {
        if (gameOptional.isPresent()) {
            Game game = gameOptional.get();

            if (game.getJoinedPlayerIds().size() >= game.getMaxPlayers()) {
                return ResponseEntity.badRequest().body("Oyun odası tam kapasite dolu!");
            }

            if (game.getJoinedPlayerIds().contains(playerId)) {
                return ResponseEntity.badRequest().body("Bu oyuna zaten daha önce katıldınız!");
            }

            game.getJoinedPlayerIds().add(playerId);
            gameRepository.save(game);
            return ResponseEntity.ok(game);
        }
        return ResponseEntity.notFound().build();
    }

    // YENİ: Oyunu bitirme endpoint'i
    @PutMapping("/{id}/finish")
    public ResponseEntity<?> finishGame(@PathVariable Long id) {
        Optional<Game> gameOptional = gameRepository.findById(id);

        if (gameOptional.isPresent()) {
            Game game = gameOptional.get();
            game.setFinished(true); // Oyunu bitti olarak işaretle
            gameRepository.save(game);
            return ResponseEntity.ok(game);
        }
        return ResponseEntity.notFound().build();
    }
}