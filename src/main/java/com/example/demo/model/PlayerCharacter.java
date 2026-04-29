package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "player_characters")
@Data
public class PlayerCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String race;      // Örn: Elf, Human
    private String charClass; // Örn: Wizard, Fighter
    private int level = 1;

    // İlişki 1: Bu karakter hangi oyuncuya (User) ait?
    @Column(name = "user_id")
    private Long userId;

    // İlişki 2: Bu karakter hangi oyunda? (Null ise henüz bir oyuna katılmamış demektir)
    @Column(name = "game_id")
    private Long gameId;

    private LocalDateTime createdAt = LocalDateTime.now();
}