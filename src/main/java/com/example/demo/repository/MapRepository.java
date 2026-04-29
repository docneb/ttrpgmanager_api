package com.example.demo.repository;

import com.example.demo.model.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MapRepository extends JpaRepository<Map, Long> {
    // Belirli bir oyuna ait haritaları getirmek için
    List<Map> findByGameId(Long gameId);

    // YENİ EKLENEN: Sadece game_id'si null olanları (Havuz Şablonlarını) getirir
    List<Map> findByGameIdIsNull();
}