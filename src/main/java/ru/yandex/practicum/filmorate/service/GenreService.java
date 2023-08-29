package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dao.GenreDAO;
import ru.yandex.practicum.filmorate.model.Genre;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreDAO genreDAO;

    public Genre get(Long id) {
        isExist(id);
        return genreDAO.get(id);
    }

    public List<Genre> getAll() {
        return genreDAO.getAll();
    }

    private void isExist(Long... ids) {
        for (Long id : ids) {
            get(id);
        }
    }
}
