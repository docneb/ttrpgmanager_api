package com.example.demo.repository;

import com.example.demo.model.PlayerCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlayerCharacterRepository extends JpaRepository<PlayerCharacter, Long> {
    // 1. Bir oyuncunun kendi yarattığı TÜM karakterleri getirmek için (Karakter Havuzu)
    List<PlayerCharacter> findByUserId(Long userId);

    // 2. Belirli bir oyuna (gameId) katılmış olan tüm karakterleri getirmek için
    List<PlayerCharacter> findByGameId(Long gameId);
}