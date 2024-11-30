package ru.alinka.springboot.Springboot.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alinka.springboot.Springboot.model.User;
import ru.alinka.springboot.Springboot.service.UserService;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String redirectToUsers() {
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String listUsers(Model model, Pageable pageable) {
        model.addAttribute("users", userService.getUsers());
        return "/users";
    }

    @GetMapping("/users/count")
    public String getUsersByCount(@RequestParam(required = false) Integer count, Model model) {
        if (count != null) {
            model.addAttribute("users", userService.getUsersByCount(count));
        }
        return "/users";
    }

    @GetMapping("/users/{id}")
    public String showUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "/show";
    }

    @GetMapping("/users/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "creat";
    }

    @PostMapping("/users")
    public String createUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "creat";
        }
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "/edit";
    }

    @PostMapping("/users/update/{id}")
    public String updateUser(@Valid User user, BindingResult result, @RequestParam("id") int id) {
        if (result.hasErrors()) {
            return "/edit";
        }
        userService.updateUser(id, user);
        return "redirect:/users";
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }
}
