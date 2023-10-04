create table albums
(
  id SERIAL PRIMARY KEY,
  public_id VARCHAR(255) NOT NULL UNIQUE,
  album_name VARCHAR(255) NOT NULL,
  album_type VARCHAR(50) NOT NULL
);