CREATE TABLE tracks
(
  id SERIAL PRIMARY KEY,
  public_id varchar(250) NOT NULL UNIQUE,
  name varchar(255) NOT NULL,
  duration_ms BIGINT NOT NULL
);