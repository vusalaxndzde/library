package com.bakulibrary.library.repository;

import com.bakulibrary.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findBookByNameContainsIgnoreCase(String name);

    List<Book> findBookByNameContainsIgnoreCaseOrderByAddedDate(String name);

    List<Book> findBookByNameContainsIgnoreCaseOrderByName(String name);

    List<Book> findBookByAddedDateAfter(Date date);

    @Modifying
    @Transactional
    @Query("delete from Book b where b.id = :id")
    void deleteBookById(@Param("id") int id);

}
