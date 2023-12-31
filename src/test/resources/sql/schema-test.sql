drop table IF EXISTS FILMS_LIKE CASCADE;
drop table IF EXISTS FRIENDS CASCADE;
drop table IF EXISTS GENRES_FILM CASCADE;
drop table IF EXISTS USERS CASCADE;
drop table IF EXISTS GENRES CASCADE;
drop table IF EXISTS FILMS CASCADE;
drop table IF EXISTS MPA_RATING CASCADE;
---F-I-L-M-S-------------------------------------------
create TABLE IF NOT EXISTS FILMS (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  name VARCHAR(200) NOT NULL,
  description VARCHAR(200),
  duration INTEGER,
  releaseDate DATE,
  mpa_rating_id INTEGER NOT NULL
);
---M-P-A---R-A-T-I-N-G---------------------------------
create TABLE IF NOT EXISTS MPA_RATING (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  name VARCHAR(10) NOT NULL UNIQUE,
  description varchar(100) NOT NULL
);

alter table FILMS add CONSTRAINT IF NOT EXISTS
 FK_FILMS_RATING FOREIGN KEY(MPA_RATING_ID) REFERENCES MPA_RATING (ID)
  ON delete RESTRICT;
---G-E-N-R-E-S-----------------------------------------
create TABLE IF NOT EXISTS GENRES (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  name VARCHAR(32) NOT NULL UNIQUE
);
---F-I-L-M-S---G-E-N-R-E-S-----------------------------
create TABLE IF NOT EXISTS FILM_GENRES (
  film_id INTEGER NOT NULL,
  genre_id INTEGER NOT NULL,
  foreign key (film_id) references films(id) on delete cascade,
  foreign key (genre_id) references genres(id) on delete cascade,
  primary key (film_id, genre_id)
);
--alter table film_genres add CONSTRAINT IF NOT EXISTS
-- FK_GENRES FOREIGN KEY(genre_id) REFERENCES GENRES (id);--  ON DELETE RESTRICT;
--alter table film_genres add CONSTRAINT IF NOT EXISTS
-- FK_FILMS FOREIGN KEY(film_id) REFERENCES FILMS (id);
---U-S-E-R-S-------------------------------------------
create TABLE IF NOT EXISTS USERS (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  login VARCHAR NOT NULL UNIQUE,
  name VARCHAR,
  email VARCHAR NOT NULL,
  birthday DATE
);
---F-R-I-E-N-D-S---------------------------------------
create TABLE IF NOT EXISTS FRIENDS (
  user_id_request INTEGER,
  user_id_friend INTEGER,
  foreign key (user_id_request) references users(id) on delete cascade,
  foreign key (user_id_friend) references users(id) on delete cascade,
  primary key (user_id_request, user_id_friend)
);

--alter table friends add CONSTRAINT IF NOT EXISTS
-- fk_friends_user FOREIGN KEY(user_id_request) REFERENCES users (id);
--
--alter table friends add CONSTRAINT IF NOT EXISTS
-- fk_friends_friend FOREIGN KEY(user_id_friend) REFERENCES users (id);

---F-I-L-M-S---L-I-K-E-S-------------------------------

create TABLE IF NOT EXISTS films_like (
  film_id INTEGER,
  user_id INTEGER,
  foreign key (film_id) references films(id) on delete cascade,
  foreign key (user_id) references users(id) on delete cascade,
  primary key (film_id, user_id)
);

--alter table films_like add CONSTRAINT IF NOT EXISTS
-- fk_like_user FOREIGN KEY(user_id) REFERENCES users (id)
--  ON delete CASCADE;
--
--alter table films_like add CONSTRAINT IF NOT EXISTS
-- fk_like_film FOREIGN KEY(film_id) REFERENCES films (id);

---E-N-D---S-C-R-I-P-T---------------------------------
