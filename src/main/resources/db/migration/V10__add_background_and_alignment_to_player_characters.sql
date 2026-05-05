ALTER TABLE player_characters
ADD COLUMN background TEXT NOT NULL DEFAULT '';

ALTER TABLE player_characters
ADD COLUMN alignment VARCHAR(30) NOT NULL DEFAULT 'TRUE_NATURAL';

ALTER TABLE player_characters
ADD CONSTRAINT chk_player_characters_alignment
CHECK (
    alignment IN (
        'LAWFUL_GOOD',
        'LAWFUL_NATURAL',
        'LAWFUL_EVIL',
        'NATURAL_GOOD',
        'TRUE_NATURAL',
        'NATURAL_EVIL',
        'CHAOTIC_GOOD',
        'CHAOTIC_NATURAL',
        'CHAOTIC_EVIL'
    )
);
