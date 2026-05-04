package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "invite_code", unique = true)
    private String inviteCode;

    @Column(name = "is_finished")
    @JsonProperty("isFinished")
    private boolean isFinished = false;

    // YENİ EKLENEN: Oyuna katılan oyuncuların ID'lerini tutan liste
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "game_players", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "player_id")
    private Set<Long> joinedPlayerIds = new HashSet<>();
}