CREATE TABLE game_players (
                              game_id BIGINT NOT NULL,
                              player_id BIGINT NOT NULL,
                              PRIMARY KEY (game_id, player_id),
                              CONSTRAINT fk_game_players_game_id FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE
);