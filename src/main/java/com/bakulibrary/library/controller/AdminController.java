package com.bakulibrary.library.controller;

import com.bakulibrary.library.dto.BookFormDto;
import com.bakulibrary.library.dto.UserDTO;
import com.bakulibrary.library.entity.Book;
import com.bakulibrary.library.entity.User;
import com.bakulibrary.library.service.inter.BookService;
import com.bakulibrary.library.service.inter.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final BookService bookService;
    private final UserService userService;

    public AdminController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
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
    public String deleteBook(@RequestParam("id") int id) {
        bookService.deleteBookById(id);
        return "redirect:/admin/books";
    }

    @GetMapping("/addUser")
    public String showAddUser(Model model) {
        model.addAttribute("userForm", new UserDTO());
        return "user-form";
    }

    @PostMapping("/addUser")
    public String addUser(@Valid @ModelAttribute("userForm") UserDTO userDTO, BindingResult bindingResult) {
        User tempUser = userService.findUserByEmail(userDTO.getEmail());
        if (tempUser != null) {
            return "redirect:/admin/addUser?error";
        }

        if (bindingResult.hasErrors()) {
            return "user-form";
        }

        userService.saveMember(userDTO);
        return "redirect:/admin/books";
    }

}
