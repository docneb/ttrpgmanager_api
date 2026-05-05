-- ============================================================
-- V11: D&D 5e Karakter Sistemi İyileştirmesi
-- - Alignment terminolojisi düzeltmesi (NATURAL → NEUTRAL)
-- - Yeni alanlar: ability scores, HP, AC, speed, backstory
-- ============================================================

-- 1. Mevcut alignment constraint'ini kaldır
ALTER TABLE player_characters
DROP CONSTRAINT IF EXISTS chk_player_characters_alignment;

-- 2. Mevcut NATURAL değerlerini NEUTRAL olarak güncelle
UPDATE player_characters SET alignment = 'LAWFUL_NEUTRAL'  WHERE alignment = 'LAWFUL_NATURAL';
UPDATE player_characters SET alignment = 'NEUTRAL_GOOD'    WHERE alignment = 'NATURAL_GOOD';
UPDATE player_characters SET alignment = 'TRUE_NEUTRAL'    WHERE alignment = 'TRUE_NATURAL';
UPDATE player_characters SET alignment = 'NEUTRAL_EVIL'    WHERE alignment = 'NATURAL_EVIL';
UPDATE player_characters SET alignment = 'CHAOTIC_NEUTRAL' WHERE alignment = 'CHAOTIC_NATURAL';

-- 3. Yeni constraint'i doğru terminolojiyle ekle
ALTER TABLE player_characters
ADD CONSTRAINT chk_player_characters_alignment
CHECK (
    alignment IN (
        'LAWFUL_GOOD',
        'LAWFUL_NEUTRAL',
        'LAWFUL_EVIL',
        'NEUTRAL_GOOD',
        'TRUE_NEUTRAL',
        'NEUTRAL_EVIL',
        'CHAOTIC_GOOD',
        'CHAOTIC_NEUTRAL',
        'CHAOTIC_EVIL'
    )
);

-- 4. Default değeri de güncelle
ALTER TABLE player_characters
ALTER COLUMN alignment SET DEFAULT 'TRUE_NEUTRAL';

-- 5. Ability Scores (D&D 5e standart: 10 = ortalama insan)
ALTER TABLE player_characters ADD COLUMN strength     INT NOT NULL DEFAULT 10;
ALTER TABLE player_characters ADD COLUMN dexterity    INT NOT NULL DEFAULT 10;
ALTER TABLE player_characters ADD COLUMN constitution INT NOT NULL DEFAULT 10;
ALTER TABLE player_characters ADD COLUMN intelligence INT NOT NULL DEFAULT 10;
ALTER TABLE player_characters ADD COLUMN wisdom       INT NOT NULL DEFAULT 10;
ALTER TABLE player_characters ADD COLUMN charisma     INT NOT NULL DEFAULT 10;

-- 6. Combat alanları
ALTER TABLE player_characters ADD COLUMN hit_points  INT NOT NULL DEFAULT 10;
ALTER TABLE player_characters ADD COLUMN armor_class INT NOT NULL DEFAULT 10;
ALTER TABLE player_characters ADD COLUMN speed       INT NOT NULL DEFAULT 30;

-- 7. Backstory (serbest hikaye metni) - background mekanik seçim olarak kalıyor
ALTER TABLE player_characters ADD COLUMN backstory TEXT NOT NULL DEFAULT '';
