package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

// Game.java
@Entity
@Table(name = "games")
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private int maxPlayers;

    @Column(name = "is_public")
    private boolean publicGame;

    private LocalDateTime createdAt = LocalDateTime.now();
    private Long gmId;
}