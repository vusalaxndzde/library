package com.bakulibrary.library.controller;

import com.bakulibrary.library.entity.User;
import com.bakulibrary.library.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/registration")
    public String showRegistration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration/save")
    public String saveUser(@ModelAttribute("user") User user) {
        User tempUser = userService.findUserByEmail(user.getEmail());
        if (tempUser != null) {
            return "redirect:/registration?error";
        }
        userService.saveUser(user);
        return "redirect:/registration?success";
    }

}
