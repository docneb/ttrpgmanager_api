CREATE TABLE maps (
                      id BIGSERIAL PRIMARY KEY,
                      name VARCHAR(255),
                      image_url VARCHAR(255),
                      game_id BIGINT,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);