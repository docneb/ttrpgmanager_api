ALTER TABLE player_characters
ADD COLUMN character_type VARCHAR(20) NOT NULL DEFAULT 'PLAYER';

ALTER TABLE player_characters
ADD CONSTRAINT chk_player_characters_character_type
CHECK (character_type IN ('PLAYER', 'NPC'));
