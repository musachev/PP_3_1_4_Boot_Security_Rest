package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    void removeUserById(int id);

    void updateUser(User updateUser);

    User getUserById(int id);

    List<User> getAllUsers();

    User getUserByUsername(String username);
}
