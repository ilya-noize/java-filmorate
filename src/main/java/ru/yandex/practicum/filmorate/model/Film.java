package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.yandex.practicum.filmorate.constraint.CorrectReleaseDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Data
public class Film {
    private final String RELEASE_DATE_LIMIT = "1895-12-28"; //LocalDate.of(1895, 12, 28);

    @EqualsAndHashCode.Exclude
    @Positive(message = "Уникальный номер фильма только положительный")
    private Integer id;

    @NotNull(message = "Название фильма не может быть null.")
    @NotBlank(message = "Название фильма не может быть пустым.")
    private String name;

    @Size(max = 200, message = "Длина описания не более 200 символов.")
    private String description;

    @CorrectReleaseDate(value = RELEASE_DATE_LIMIT, //Attribute value must be constant
            message = "Дата релиза не раньше 28 DEC 1895 и не позже сегодня")
    private LocalDate releaseDate;

    @Positive(message = "Продолжительность фильма - положительное натуральное число.")
    private int duration;

    public Film(String name, String description, LocalDate releaseDate, int duration) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }
}