package com.bakulibrary.library.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String genreName;

    @ManyToMany(mappedBy = "genres")
    private List<Book> books;

}
