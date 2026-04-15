package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

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
    private boolean isPublic;
    private LocalDateTime createdAt = LocalDateTime.now();

    // Şimdilik basit tutmak için gmId'yi direkt tutalım
    private Long gmId;
}