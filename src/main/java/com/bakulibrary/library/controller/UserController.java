package com.bakulibrary.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class UserController {

    @GetMapping
    public String showUserDetails() {
        return "user-details";
    }

}
