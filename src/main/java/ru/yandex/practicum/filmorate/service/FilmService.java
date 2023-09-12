package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dao.FilmDAO;
import ru.yandex.practicum.filmorate.dao.FilmGenresDAO;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmService {
    private static Long filmId;
    private final FilmDAO filmDAO;
    private final FilmGenresDAO filmGenresDAO;

    public Film create(Film film) {
        log.debug("[+][S] Film: \n film:{}", film);
        film = filmDAO.create(valid(film));
        filmId = film.getId();
        List<Genre> genres = film.getGenres();
        if (genres != null) {
            filmGenresDAO.add(filmId, genres);
        }
        return get(filmId);
    }

    public Film update(Film film) {
        isExist(film.getId());
        filmId = valid(film).getId();
        List<Genre> genres = film.getGenres();
        log.debug("[u][S] Film: \n film:{}\n genres:{}", film, genres);
        filmDAO.update(film);
        if (genres != null) {
            log.debug("[i] service: update genres:{}", genres);
            filmGenresDAO.update(filmId, genres);
        }
        return get(filmId);
    }

    public Film get(Long id) {
        filmId = id;
        Film film = filmDAO.get(filmId);
        log.info("[>][S] Film\nFilm = {}", film);

        Map<Long, List<Genre>> genres = filmGenresDAO.getFilmGenres(Set.of(filmId));
        if (genres.isEmpty()) {
            film.setGenres(new ArrayList<>());
        } else {
            film.setGenres(genres.get(filmId));
        }
        return film;
    }

    public List<Film> getAll() {
        List<Film> films = filmDAO.getAll();
        log.debug("[>][S] All Films (size = {})", films.size());

        if (films.isEmpty()) {
            return new ArrayList<>();
        }
        return getAllDataFilm(films);
    }

    /**
     * Возвращает список популярных фильмов у пользователей
     *
     * @param count количество фильмов
     * @return список
     */
    public List<Film> getPopular(Long count) {
        log.debug("Returning the TOP-{} popular movies from users", count);
        List<Film> films = filmDAO.getPopular(count);
        if (films.isEmpty()) {
            return new ArrayList<>();
        }
        return getAllDataFilm(films);
    }

    private List<Film> getAllDataFilm(List<Film> films) {
        Set<Long> filmIds = films.stream()
                .map(Film::getId)
                .collect(toSet());
        Map<Long, List<Genre>> genres = filmGenresDAO.getFilmGenres(filmIds);
        films.forEach(film -> {
            if (genres.isEmpty()) {
                film.setGenres(new ArrayList<>());
            } else {
                film.setGenres(genres.get(film.getId()));
            }
        });
        return films;
    }

    private void isExist(Long... ids) {
        for (Long id : ids) {
            get(id);
        }
    }

    private Film valid(Film film) {
//        if (film.getName() == null || film.getName().isBlank()) {
//            throw new ValidException("The name" +
//                    " should not be blank");
//        }
//        if (film.getDescription().isBlank() || film.getDescription().length() > 200) {
//            throw new ValidException("The description" +
//                    " should valid");
//        }
//        if (film.getDuration() == null || film.getDuration() <= 0) {
//            throw new ValidException("The duration" +
//                    " should not be null and positive number");
//        }
//        if (film.getReleaseDate() == null) {
//            throw new ValidException("The release date" +
//                    " should not be blank");
//        }
//        if (film.getReleaseDate().isAfter(LocalDate.now())) {
//            throw new ValidException("The release date" +
//                    " should not be in the future");
//        }
//        if (film.getReleaseDate().isBefore(LocalDate.parse(RELEASE_DATE_LIMIT))) {
//            throw new ValidException("The release date" +
//                    " should not be before " + RELEASE_DATE_LIMIT);
//        }
//        if (film.getMpa() == null) {
//            throw new ValidException("The MPA Rating" +
//                    " should not be null");
//        }
        return film;
    }
}
