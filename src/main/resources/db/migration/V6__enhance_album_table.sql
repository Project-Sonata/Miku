ALTER TABLE albums
    ADD COLUMN IF NOT EXISTS total_tracks_count INTEGER NOT NULL DEFAULT 0