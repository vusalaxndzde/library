package com.bakulibrary.library.controller;

import com.bakulibrary.library.dto.BookFormDto;
import com.bakulibrary.library.entity.Book;
import com.bakulibrary.library.service.inter.BookService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final BookService bookService;

    public AdminController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String showBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books-table";
    }

    @GetMapping("/showAddBookForm")
    public String showAddBookForm(Model model) {
        BookFormDto bookFormDto = new BookFormDto();
        model.addAttribute("bookForm", bookFormDto);
        return "book-form";
    }

    @GetMapping("/showUpdateBookForm")
    public String showUpdateBookForm(@RequestParam("id") int id, Model model) {
        Book book = bookService.findById(id);
        BookFormDto bookFormDto = bookService.convertToBookFormDto(book);
        model.addAttribute("bookForm", bookFormDto);
        return "book-form";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute("bookForm") BookFormDto bookFormDto) {
        bookService.saveBookForm(bookFormDto);
        return "redirect:/admin/books";
    }

    @GetMapping("/deleteBook")
    public String deleteBook(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        bookService.deleteBookById(id);
        return "redirect:/admin/books";
    }

}
