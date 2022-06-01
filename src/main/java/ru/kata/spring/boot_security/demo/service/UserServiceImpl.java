package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserDAO userDAO;
    private final UserRepository userRepository;

    public UserServiceImpl(UserDAO userDAO, UserRepository userRepository) {
        this.userDAO = userDAO;
        this.userRepository = userRepository;
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

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
