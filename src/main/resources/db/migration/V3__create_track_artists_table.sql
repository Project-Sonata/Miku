CREATE TABLE tracks_artists
(
  track_id bigint references tracks(id) NOT NULL,
  artist_id bigint references artists(id) NOT NULL
);