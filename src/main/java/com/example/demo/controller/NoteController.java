package com.example.demo.controller;

import com.example.demo.model.Note;
import com.example.demo.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    // 1. Notları Getir
    @GetMapping("/{userId}/{noteType}")
    public ResponseEntity<List<Note>> getNotes(@PathVariable Long userId, @PathVariable String noteType) {
        return ResponseEntity.ok(noteRepository.findByUserIdAndNoteType(userId, noteType));
    }

    // 2. Yeni Not Ekle
    @PostMapping("/create")
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        return ResponseEntity.ok(noteRepository.save(note));
    }

    // 3. Not Güncelle
    @PutMapping("/update/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note updatedNote) {
        return noteRepository.findById(id).map(note -> {
            note.setTitle(updatedNote.getTitle());
            note.setContent(updatedNote.getContent());
            note.setTag(updatedNote.getTag());
            note.setSubTag(updatedNote.getSubTag());
            return ResponseEntity.ok(noteRepository.save(note));
        }).orElse(ResponseEntity.notFound().build());
    }

    // 4. Not Sil
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable Long id) {
        noteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // YENİ: Sadece belirli bir oyuna ait notları getir
    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<Note>> getNotesByGameId(@PathVariable Long gameId) {
        return ResponseEntity.ok(noteRepository.findByGameId(gameId));
    }
}