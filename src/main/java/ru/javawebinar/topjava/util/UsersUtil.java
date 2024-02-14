package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {
    public static final List<User> users = Arrays.asList(
            new User("Tommy", "tommy@email.com", "123456", Role.USER),
            new User("John", "john@email.com", "123456", Role.ADMIN, Role.USER),
            new User("John", "alekseev.john@email.com", "123456", Role.USER)
    );
}
