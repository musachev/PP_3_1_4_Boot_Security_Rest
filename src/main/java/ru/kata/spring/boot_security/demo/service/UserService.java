package ru.kata.spring.boot_security.demo.service;

import expert.usachev.web.pp_3_1_1_SpringBoot.dao.UserDAO;
import expert.usachev.web.pp_3_1_1_SpringBoot.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    public void updateUser(User updateUser) {
        userDAO.updateUser(updateUser);
    }

    public void removeUserById(int id) {
        userDAO.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

}
