package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.yandex.practicum.filmorate.exception.UserAlreadyExistException;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Map;

@Data
@ToString
public class User {

    @EqualsAndHashCode.Exclude
    private Integer id;

    @Email(message = "Введенное значение не является адресом электронной почты.")
    private String email;

    @Pattern(regexp = "^\\S*$", message = "Логин не может содержать пробелы.")
    @Size(min = 3, max = 20, message = "Логин должен быть от 3 до 20 символов.")
    private String login;

    private String name;

    @Past(message = "Дата рождения должна быть только в прошлом.")
    private LocalDate birthday;

//    private final Set<Integer> friends;

    public User(String login, String name, String email, LocalDate birthday) {
        this.login = login;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
//        friends = new HashSet<>();
    }

//    public boolean addFriend(Integer id) {
//        return friends.add(id);
//    }

//    public boolean deleteFriend(Integer id) {
//        return friends.remove(id);
//    }

    public boolean isLoginUnical(Map<Integer, User> users) {
        for (User user : users.values()) {
            if (this.getLogin().equals(user.getLogin())) {
                throw new UserAlreadyExistException(
                        String.format("Пользователь с логином:%s уже существует.", this.login));
            }
        }
        return true;
    }

    public void nameEqualLoginIfNameIsNullOrBlank() {
        if (name == null || name.isBlank()) {
            name = login;
        }
    }
}