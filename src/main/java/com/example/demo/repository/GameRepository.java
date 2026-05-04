package com.example.demo.repository;

import com.example.demo.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByGmId(Long gmId);

    // YENİ: Halka açık (Player'ların görebileceği ve katılabileceği) oyunlar
    List<Game> findByPublicGameTrue();

    // YENİ: Belirli bir oyuncunun (Player) daha önceden katıldığı oyunlar
    @Query("SELECT g FROM Game g JOIN g.joinedPlayerIds p WHERE p = :playerId")
    List<Game> findGamesByPlayerId(@Param("playerId") Long playerId);

    // YENİ EKLENEN: Davet koduna göre oyun arama
    Optional<Game> findByInviteCode(String inviteCode);
}