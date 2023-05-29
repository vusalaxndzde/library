package com.bakulibrary.library.controller;

import com.bakulibrary.library.dto.UpdatePasswordDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class UserController {

    @GetMapping
    public String showUserDetails(Model model) {
        model.addAttribute("updatePassword", new UpdatePasswordDTO());
        return "user-details";
    }

    @PostMapping("/updatePassword")
    public String updatePassword(@ModelAttribute("updatePassword") UpdatePasswordDTO updatePasswordDTO) {

        return "redirect:/account";
    }

}
