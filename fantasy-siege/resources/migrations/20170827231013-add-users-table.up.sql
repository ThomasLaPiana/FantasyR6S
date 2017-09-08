CREATE TABLE users
(id VARCHAR(20) PRIMARY KEY,
 first_name VARCHAR(30),
 last_name VARCHAR(30),
 email VARCHAR(30),
 admin BOOLEAN,
 last_login TIME,
 is_active BOOLEAN,
 pass VARCHAR(300));

CREATE TABLE teams
(name VARCHAR(50) PRIMARY KEY);

CREATE TABLE players
(id VARCHAR(50) PRIMARY KEY,
 alias VARCHAR(50),
  team VARCHAR(50) REFERENCES teams(name));

CREATE TABLE matches
(id VARCHAR(30) PRIMARY KEY,
  year INTEGER,
  season INTEGER,
  week INTEGER);
