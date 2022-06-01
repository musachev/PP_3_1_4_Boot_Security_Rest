package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/")
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "index";
    }

    @ModelAttribute("newUser")
    public User getPerson() {
        return new User();
    }

    @GetMapping("/people")
    public String index(Model model) {
        model.addAttribute("people", userServiceImpl.getAllUsers());
        return "view/index";
    }

    @PostMapping("/people")
    public String creat(@ModelAttribute("newUser") User user,
                        BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("people", userServiceImpl.getAllUsers());
            return "view/index";
        }
        userServiceImpl.saveUser(user);
        return "redirect:/people";
    }

    @DeleteMapping("/people/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        userServiceImpl.removeUserById(id);
        return "redirect:/people";
    }

    @GetMapping("/people/{id}/edit")
    public String edit(@ModelAttribute("id") int id, Model model) {
        model.addAttribute("user", userServiceImpl.getUserById(id));
        return "view/edit";
    }

    @PatchMapping("/people/{id}")
    public String updatePerson(@ModelAttribute("user") User updateuser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "view/edit";
        }
        userServiceImpl.updateUser(updateuser);
        return "redirect:/people";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "redirect:/people";
    }

    @GetMapping(value = "/user")
    public String showUserInfo(@CurrentSecurityContext(expression = "authentication.principal") User principal,
                               Model model) {
        model.addAttribute("user", principal);

        return "user";
    }
}
