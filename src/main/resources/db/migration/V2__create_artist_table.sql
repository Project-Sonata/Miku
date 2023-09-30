CREATE TABLE artists
(
  id SERIAL PRIMARY KEY,
  public_id varchar(250) NOT NULL UNIQUE,
  artist_name varchar(255) NOT NULL
);