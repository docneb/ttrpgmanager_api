package com.example.demo.controller;

import com.example.demo.model.Game;
import com.example.demo.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}