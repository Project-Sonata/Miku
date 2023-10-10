CREATE TABLE album_artists
(
    album_id  bigint references albums (id),
    artist_id bigint references artists (id)
);