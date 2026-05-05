package com.example.demo.controller;

import com.example.demo.model.Note;
import com.example.demo.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    // GÜNCELLENDİ: Artık kopyaları (gameId dolu olanları) havuzda göstermeyecek[cite: 31]
    @GetMapping("/{userId}/{noteType}")
    public ResponseEntity<List<Note>> getNotes(@PathVariable Long userId, @PathVariable String noteType) {
        return ResponseEntity.ok(noteRepository.findByUserIdAndNoteTypeAndGameIdIsNull(userId, noteType));
    }

    // 2. Yeni Not Ekle[cite: 31]
    @PostMapping("/create")
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        return ResponseEntity.ok(noteRepository.save(note));
    }

    // 3. Not Güncelle[cite: 31]
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

    // 4. Not Sil[cite: 31]
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable Long id) {
        noteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Sadece belirli bir oyuna ait notları getir[cite: 31]
    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<Note>> getNotesByGameId(@PathVariable Long gameId) {
        return ResponseEntity.ok(noteRepository.findByGameId(gameId));
    }

    // YENİ: Havuzdaki notu kopyalayarak oyuna ekleme işlemi
    @PostMapping("/clone/{noteId}/to-game/{gameId}")
    public ResponseEntity<Note> cloneNoteToGame(@PathVariable Long noteId, @PathVariable Long gameId) {
        Optional<Note> originalNoteOpt = noteRepository.findById(noteId);

        if (originalNoteOpt.isPresent()) {
            Note originalNote = originalNoteOpt.get();

            // Yeni bir kopya (klon) nesnesi oluşturuyoruz
            Note clonedNote = new Note();
            clonedNote.setTitle(originalNote.getTitle());
            clonedNote.setContent(originalNote.getContent());
            clonedNote.setTag(originalNote.getTag());
            clonedNote.setSubTag(originalNote.getSubTag());
            clonedNote.setNoteType(originalNote.getNoteType());
            clonedNote.setUserId(originalNote.getUserId());

            // Klonun tek farkı, hedef oyunun ID'sine sahip olmasıdır
            clonedNote.setGameId(gameId);

            // Veritabanına yeni bir satır olarak kaydediyoruz
            Note savedNote = noteRepository.save(clonedNote);
            return ResponseEntity.ok(savedNote);
        }

        return ResponseEntity.notFound().build();
    }
}