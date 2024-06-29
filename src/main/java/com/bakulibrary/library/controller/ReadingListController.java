package com.bakulibrary.library.controller;

import com.bakulibrary.library.entity.Book;
import com.bakulibrary.library.entity.ReadingList;
import com.bakulibrary.library.entity.User;
import com.bakulibrary.library.service.inter.BookService;
import com.bakulibrary.library.service.inter.ReadingListService;
import com.bakulibrary.library.service.inter.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/account/books")
    public String showMyBooks(Model model, Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findUserByEmail(email);

        List<ReadingList> readingLists = readingListService.findReadingListByUser(user);
        List<Book> books = readingLists.stream().map(ReadingList::getBook).collect(Collectors.toList());
        model.addAttribute("books", books);

        return "user-books";
    }

    @PostMapping("/addBookToList")
    public String addBookToMyReadingList(@RequestParam("id") int bookId, Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findUserByEmail(email);
        ReadingList readingList = readingListService.findReadingListByUserIdAndBookId(user.getId(), bookId);

        if (readingList == null) {
            Book book = bookService.findById(bookId);

            readingList = new ReadingList();
            readingList.setUser(user);
            readingList.setBook(book);
            readingListService.save(readingList);
            return "redirect:/?success";
        }
        return "redirect:/?error";
    }

    @PostMapping("/account/books/deleteBookFromList")
    public String deleteBookFromReadingList(@RequestParam("id") int bookId, Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findUserByEmail(email);
        readingListService.deleteReadingListByUserIdAndBookId(user.getId(), bookId);
        return "redirect:/account/books?success";
    }

}
