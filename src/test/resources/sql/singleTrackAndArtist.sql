WITH artist_insert_id AS (
    insert into artists (public_id, artist_name)
        values ('artistuniqueid', 'Mosquit') returning id),

     album_insert_id AS (
         insert into albums (public_id, album_name, album_type, release_date, release_date_precision)
             values ('albumid', 'Adobe of the soul', 'ALBUM', '2022', 'YEAR') returning id),

     track_insert_id AS (
         insert into tracks (public_id, name, duration_ms, album_id)
             select 'trackuniqueid', 'Chill', 1200, album_insert_id.id FROM album_insert_id returning id)


INSERT INTO tracks_artists (track_id, artist_id)
SELECT track_insert_id.id, artist_insert_id.id
FROM track_insert_id, artist_insert_id;