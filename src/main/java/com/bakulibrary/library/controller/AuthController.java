package com.bakulibrary.library.controller;

import com.bakulibrary.library.dto.UserDTO;
import com.bakulibrary.library.entity.User;
import com.bakulibrary.library.service.inter.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        model.addAttribute("userDTO", new UserDTO());
        return "registration";
    }

    @PostMapping("/registration/save")
    public String saveUser(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult bindingResult) {
        User tempUser = userService.findUserByEmail(userDTO.getEmail());
        if (tempUser != null) {
            return "redirect:/registration?error";
        }
        System.out.println(userDTO);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        User user = userService.convertUserDTOToUser(userDTO);
        System.out.println(user);
//        userService.saveUser(user);
        return "redirect:/login?success";
    }

}
