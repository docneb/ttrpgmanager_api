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
        LAWFUL_NEUTRAL,
        LAWFUL_EVIL,
        NEUTRAL_GOOD,
        TRUE_NEUTRAL,
        NEUTRAL_EVIL,
        CHAOTIC_GOOD,
        CHAOTIC_NEUTRAL,
        CHAOTIC_EVIL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String race;      // Örn: Elf, Human, Dwarf
    private String charClass; // Örn: Wizard, Fighter, Rogue
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

    // D&D 5e Background (mekanik seçim: Acolyte, Soldier, vb.)
    @Column(name = "background", nullable = false, columnDefinition = "TEXT")
    private String background = "";

    @Enumerated(EnumType.STRING)
    @Column(name = "alignment", nullable = false)
    private Alignment alignment = Alignment.TRUE_NEUTRAL;

    // D&D 5e Ability Scores (standart değer: 10 = ortalama insan)
    @Column(nullable = false)
    private int strength = 10;

    @Column(nullable = false)
    private int dexterity = 10;

    @Column(nullable = false)
    private int constitution = 10;

    @Column(nullable = false)
    private int intelligence = 10;

    @Column(nullable = false)
    private int wisdom = 10;

    @Column(nullable = false)
    private int charisma = 10;

    // Combat Stats
    @Column(name = "hit_points", nullable = false)
    private int hitPoints = 10;

    @Column(name = "armor_class", nullable = false)
    private int armorClass = 10;

    @Column(nullable = false)
    private int speed = 30;

    // Serbest hikaye metni (backstory)
    @Column(nullable = false, columnDefinition = "TEXT")
    private String backstory = "";

    // İsteğe bağlı karakter resmi/avatar yolu
    @Column(name = "avatar_url")
    private String avatarUrl = "";

    private LocalDateTime createdAt = LocalDateTime.now();
}
