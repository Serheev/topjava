package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {
    public static final List<User> users = Arrays.asList(
            new User(null, "Dolbunoff", "dolbun@mail.ru", "password", Role.ADMIN),
            new User(null, "Commandor", "commandor@mail.ru", "password", Role.USER),
            new User(null, "Boris", "boris@mail.ru", "password", Role.USER),
            new User(null, "Eugen", "eugen@mail.ru", "password", Role.ADMIN, Role.USER),
            new User(null, "Alex", "alex@mail.ru", "password", Role.ADMIN),
            new User(null, "Boris", "alexeev@mail.ru", "password", Role.USER)
    );
}
