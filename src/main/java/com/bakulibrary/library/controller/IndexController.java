package com.bakulibrary.library.controller;

import com.bakulibrary.library.entity.Book;
import com.bakulibrary.library.service.inter.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/book")
    public String showBook(@RequestParam("id") int id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "book";
    }

    @GetMapping("/search")
    public String showSearch(@RequestParam("q") String question) {
        System.out.println(bookService.findBookByNameContainsIgnoreCase(question));
        return "search";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
