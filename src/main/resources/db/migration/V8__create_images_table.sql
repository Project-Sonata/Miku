CREATE TABLE album_images
(
    url      VARCHAR(3000)                  NOT NULL,
    width    INTEGER,
    height   INTEGER,
    album_id INTEGER REFERENCES albums (id) NOT NULL
);