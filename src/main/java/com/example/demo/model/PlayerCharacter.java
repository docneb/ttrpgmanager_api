package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "player_characters")
@Data
public class PlayerCharacter {
    public enum CharacterType {
        PLAYER,
        NPC
    }

    public enum Alignment {
        LAWFUL_GOOD,
        LAWFUL_NATURAL,
        LAWFUL_EVIL,
        NATURAL_GOOD,
        TRUE_NATURAL,
        NATURAL_EVIL,
        CHAOTIC_GOOD,
        CHAOTIC_NATURAL,
        CHAOTIC_EVIL
    }

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

    @Enumerated(EnumType.STRING)
    @Column(name = "character_type", nullable = false)
    private CharacterType characterType = CharacterType.PLAYER;

    @Column(name = "background", nullable = false, columnDefinition = "TEXT")
    private String background = "";

    @Enumerated(EnumType.STRING)
    @Column(name = "alignment", nullable = false)
    private Alignment alignment = Alignment.TRUE_NATURAL;

    private LocalDateTime createdAt = LocalDateTime.now();
}
