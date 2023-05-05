package com.bakulibrary.library.service.inter;

import com.bakulibrary.library.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    Book findById(int id);

}
