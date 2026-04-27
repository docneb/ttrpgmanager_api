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

    private LocalDateTime createdAt = LocalDateTime.now();
}