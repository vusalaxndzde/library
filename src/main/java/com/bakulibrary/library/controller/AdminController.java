package com.bakulibrary.library.controller;

import com.bakulibrary.library.dto.BookFormDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/showAddBookForm")
    public String showAddBookForm(Model model) {
        BookFormDto bookFormDto = new BookFormDto();
        model.addAttribute("bookForm", bookFormDto);
        return "book-form";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute("bookForm") BookFormDto bookFormDto) {
        System.out.println(bookFormDto);
        return "redirect:/admin/showAddBookForm";
    }

}
