DELETE FROM GENRES;

alter table GENRES alter COLUMN ID RESTART with 1;

INSERT INTO GENRES (ID, NAME) VALUES
(1,  'Комедия'),
(2,  'Драма'),
(3,  'Мультфильм'),
(4,  'Триллер'),
(5,  'Документальный'),
(6,  'Боевик');--,
--(7,  'Фантастика'),
--(8,  'Вестерн'),
--(9,  'Детектив'),
--(10, 'Нуар'),
--(11, 'Ужасы'),
--(12, 'Политика'),
--(13, 'Мюзикл'),
--(14, 'Мелодрама'),
--(15, 'Сказка');as a comment