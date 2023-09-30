WITH artist_insert_id AS (
    insert into artists (public_id, artist_name)
        values ('artistuniqueid', 'Mosquit') returning id),

     track_insert_id AS (
         insert into tracks (public_id, name, duration_ms)
             values ('trackuniqueid', 'Chill', 1200) returning id)

INSERT INTO tracks_artists (track_id, artist_id)
SELECT track_insert_id.id, artist_insert_id.id
FROM track_insert_id, artist_insert_id;