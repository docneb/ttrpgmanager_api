-- ============================================================
-- V12: Karakterlere Avatar Ekleme
-- - player_characters tablosuna avatar_url kolonu eklendi
-- ============================================================

ALTER TABLE player_characters 
ADD COLUMN avatar_url VARCHAR(255) DEFAULT '';
