package com.bakulibrary.library.repository;

import com.bakulibrary.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findBookByNameContainsIgnoreCase(String name);

}
