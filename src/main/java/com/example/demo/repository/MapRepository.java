package com.example.demo.repository;

import com.example.demo.model.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MapRepository extends JpaRepository<Map, Long> {
    List<Map> findByGameId(Long gameId);

    // DEĞİŞTİRİLEN: Sadece bu kullanıcıya ait olan havuz şablonlarını getirir
    List<Map> findByGameIdIsNullAndOwnerId(Long ownerId);
}