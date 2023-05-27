package com.bakulibrary.library.service;

import com.bakulibrary.library.dto.BookFormDto;
import com.bakulibrary.library.entity.Author;
import com.bakulibrary.library.entity.Book;
import com.bakulibrary.library.entity.Genre;
import com.bakulibrary.library.repository.AuthorRepository;
import com.bakulibrary.library.repository.BookRepository;
import com.bakulibrary.library.repository.GenreRepository;
import com.bakulibrary.library.service.inter.BookService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findNewBooks() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        Date date = calendar.getTime();

        return bookRepository.findBookByAddedDateAfter(date);
    }

    @Override
    public Book findById(int id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElseThrow();
    }

    @Override
    public List<Book> findBookByNameContainsIgnoreCase(String name) {
        return bookRepository.findBookByNameContainsIgnoreCase(name);
    }

    @Override
    public List<Book> findBookByNameContainsIgnoreCaseOrderByAddedDateDesc(String name) {
        return bookRepository.findBookByNameContainsIgnoreCaseOrderByAddedDateDesc(name);
    }

    @Override
    public List<Book> findBookByNameContainsIgnoreCaseOrderByName(String name) {
        return bookRepository.findBookByNameContainsIgnoreCaseOrderByName(name);
    }

    @Override
    public List<Book> searchAndSort(String name, String sortType) {
        List<Book> books;

        if (name != null && name.equals("")) {
            name = null;
        }

        if (name != null && sortType != null) {
            if (sortType.equals("date")) {
                books = bookRepository.findBookByNameContainsIgnoreCaseOrderByAddedDateDesc(name);
            } else if (sortType.equals("name")) {
                books = bookRepository.findBookByNameContainsIgnoreCaseOrderByName(name);
            } else {
                books = bookRepository.findBookByNameContainsIgnoreCase(name);
            }
        } else {
            books = bookRepository.findBookByNameContainsIgnoreCase(name);
        }

        return books;
    }

    @Override
    public void saveBookForm(BookFormDto bookFormDto) {
        Book book = new Book();
        book.setId(bookFormDto.getId());
        book.setName(bookFormDto.getName());
        book.setAbout(bookFormDto.getAbout());
        book.setTotalPages(bookFormDto.getTotalPages());
        book.setImageUrl("/image/" + bookFormDto.getImageUrl());

        Date addedDate = new Date(System.currentTimeMillis());
        book.setAddedDate(addedDate);

        String authorName = bookFormDto.getAuthorName();
        book.setAuthor(checkAuthorExist(authorName));

        List<String> genreNames = Arrays.stream(bookFormDto.getGenres().split(" ")).toList();
        book.setGenres(checkGenresExist(genreNames));

        bookRepository.save(book);
    }

    @Override
    public void deleteBookById(int id) {
        bookRepository.deleteBookById(id);
    }

    @Override
    public BookFormDto convertToBookFormDto(Book book) {
        BookFormDto bookFormDto = new BookFormDto();
        bookFormDto.setName(book.getName());
        bookFormDto.setId(book.getId());
        bookFormDto.setImageUrl(book.getImageUrl().substring(7));
        bookFormDto.setAbout(book.getAbout());
        bookFormDto.setTotalPages(book.getTotalPages());
        bookFormDto.setAuthorName(book.getAuthor().getAuthorName());

        StringBuilder genreNames = new StringBuilder();
        List<Genre> genres = book.getGenres();
        for (Genre genre : genres) {
            genreNames.append(genre.getGenreName()).append(" ");
        }
        bookFormDto.setGenres(genreNames.substring(0, genreNames.length() - 1));

        return bookFormDto;
    }

    public Author checkAuthorExist(String authorName) {
        Author author = authorRepository.findAuthorByAuthorName(authorName);
        if (author == null) {
            author = new Author();
            author.setAuthorName(authorName);
            authorRepository.save(author);
        }
        return author;
    }

    public List<Genre> checkGenresExist(List<String> genreNames) {
        List<Genre> genres = new ArrayList<>();
        for (String genreName : genreNames) {
            Genre genre = genreRepository.findGenreByGenreName(genreName);
            if (genre == null) {
                genre = new Genre();
                genre.setGenreName(genreName);
                genreRepository.save(genre);
            }
            genres.add(genre);
        }
        return genres;
    }

}
