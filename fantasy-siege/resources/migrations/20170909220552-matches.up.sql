CREATE TABLE matches
(id VARCHAR(30) PRIMARY KEY,
  slot INTEGER,
  year INTEGER,
  season INTEGER,
  region VARCHAR(10),
  week INTEGER,
  player VARCHAR(50) REFERENCES players(alias),
  kills INTEGER,
  deaths INTEGER,
  assists INTEGER;)
