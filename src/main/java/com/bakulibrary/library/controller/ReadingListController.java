package com.bakulibrary.library.controller;

import com.bakulibrary.library.entity.User;
import com.bakulibrary.library.service.inter.ReadingListService;
import com.bakulibrary.library.service.inter.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReadingListController {

    private final UserService userService;
    private final ReadingListService readingListService;

    public ReadingListController(UserService userService, ReadingListService readingListService) {
        this.userService = userService;
        this.readingListService = readingListService;
    }

    @PostMapping("/addMyBook")
    public String addToMyBookList(@RequestParam("id") int id, Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findUserByEmail(email);
        return "redirect:/";
    }

}
