package ru.kata.spring.boot_security.demo.repositories;

import ru.kata.spring.boot_security.demo.model.User;

public interface UserRepository {

    User findByUsername(String username);
}
