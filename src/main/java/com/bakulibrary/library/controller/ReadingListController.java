package com.bakulibrary.library.controller;

import com.bakulibrary.library.entity.Book;
import com.bakulibrary.library.entity.ReadingList;
import com.bakulibrary.library.entity.User;
import com.bakulibrary.library.service.inter.BookService;
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
    private final BookService bookService;

    public ReadingListController(UserService userService, ReadingListService readingListService, BookService bookService) {
        this.userService = userService;
        this.readingListService = readingListService;
        this.bookService = bookService;
    }

    @PostMapping("/addBookToList")
    public String addBookToMyReadingList(@RequestParam("id") int id, Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findUserByEmail(email);
        Book book = bookService.findById(id);

        ReadingList readingList = new ReadingList();
        readingList.setUser(user);
        readingList.setBook(book);
        readingListService.save(readingList);

        return "redirect:/";
    }

}
