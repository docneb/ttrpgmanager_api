package com.example.demo.repository;

import com.example.demo.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    // Spring Data JPA bu isimden SQL sorgusunu otomatik üretir:
    // SELECT * FROM notes WHERE user_id = ? AND note_type = ?
    List<Note> findByUserIdAndNoteType(Long userId, String noteType);
    // YENİ: Spesifik bir oyuna ait notları getiren sorgu
    List<Note> findByGameId(Long gameId);
}