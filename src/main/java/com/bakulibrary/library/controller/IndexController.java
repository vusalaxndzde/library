package com.bakulibrary.library.controller;

import com.bakulibrary.library.repository.BookRepository;
import com.bakulibrary.library.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final BookService bookService;

    public IndexController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String showIndex(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "index";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
