CREATE TABLE notes (
                       id BIGSERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       content TEXT,
                       tag VARCHAR(50),
                       sub_tag VARCHAR(50),
                       note_type VARCHAR(20) NOT NULL, -- 'GM' veya 'PLAYER'
                       user_id BIGINT NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       CONSTRAINT fk_note_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);