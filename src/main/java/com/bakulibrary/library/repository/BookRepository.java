package com.bakulibrary.library.repository;

import com.bakulibrary.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {



}
