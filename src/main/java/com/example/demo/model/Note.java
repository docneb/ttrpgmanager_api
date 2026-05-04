package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
@Data
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String tag;
    private String subTag;

    // Notun kime ait olduğu ve rolü
    private String noteType;
    private Long userId;

    // YENİ EKLENEN KISIM: Notun bağlı olduğu oyun (Kişisel notsa null kalır)
    @Column(name = "game_id")
    private Long gameId;

    // Getter ve Setter'ını da eklemeyi unutma:
    public Long getGameId() { return gameId; }
    public void setGameId(Long gameId) { this.gameId = gameId; }

    private LocalDateTime createdAt = LocalDateTime.now();
}