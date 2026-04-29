CREATE TABLE player_characters (
                                   id BIGSERIAL PRIMARY KEY,
                                   name VARCHAR(255),
                                   race VARCHAR(255),
                                   char_class VARCHAR(255),
                                   level INT DEFAULT 1,
                                   user_id BIGINT,
                                   game_id BIGINT,
                                   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);