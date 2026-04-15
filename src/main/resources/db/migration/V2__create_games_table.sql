CREATE TABLE games (
                       id BIGSERIAL PRIMARY KEY,
                       title VARCHAR(100) NOT NULL,
                       description TEXT,
                       max_players INTEGER NOT NULL,
                       is_public BOOLEAN DEFAULT true,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       gm_id BIGINT,
                       CONSTRAINT fk_gm FOREIGN KEY(gm_id) REFERENCES users(id)
);