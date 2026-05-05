package com.example.demo.repository;

import com.example.demo.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    // ESKİ HALİ: List<Note> findByUserIdAndNoteType(Long userId, String noteType);[cite: 33]

    // YENİ: Sadece ana havuza ait olanları (gameId'si null olan şablonları) getirir
    List<Note> findByUserIdAndNoteTypeAndGameIdIsNull(Long userId, String noteType);

    // Spesifik bir oyuna ait kopyalanmış notları getiren sorgu[cite: 33]
    List<Note> findByGameId(Long gameId);
}