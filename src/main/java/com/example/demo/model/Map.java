package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "maps")
@Data
public class Map {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Haritanın resmi (URL, Firebase linki veya yerel dosya yolu)
    private String imageUrl;

    // İlişki: Bu harita hangi oyuna ait?
    @Column(name = "game_id")
    private Long gameId;

    // YENİ: Haritayı havuzuna yükleyen GM'nin ID'si
    @Column(name = "gm_id")
    private Long gmId;

    private LocalDateTime createdAt = LocalDateTime.now();
}