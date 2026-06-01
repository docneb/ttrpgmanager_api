package com.example.demo.controller;

import com.example.demo.model.Map;
import com.example.demo.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/maps")
public class MapController {

    @Autowired
    private MapRepository mapRepository;

    // Havuzdaki tüm şablon haritaları getirme (Oyun kopyaları hariç), GM bazlı.
    @GetMapping("/all/{gmId}")
    public ResponseEntity<List<Map>> getAllMaps(@PathVariable Long gmId) {
        // findAll() yerine findByGameIdIsNullAndGmId() kullanıyoruz
        return ResponseEntity.ok(mapRepository.findByGameIdIsNullAndGmId(gmId));
    }

    // Yeni harita ekleme
    @PostMapping("/create")
    public ResponseEntity<Map> createMap(@RequestBody Map map) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapRepository.save(map));
    }

    // Belirli bir oyuna ait haritaları getirme
    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<Map>> getMapsByGame(@PathVariable Long gameId) {
        return ResponseEntity.ok(mapRepository.findByGameId(gameId));
    }

    // İstersen silme (DELETE) işlemini de ekleyebiliriz ama şimdilik temel atalım
}