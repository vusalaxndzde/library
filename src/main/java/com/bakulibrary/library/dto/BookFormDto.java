package com.bakulibrary.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookFormDto {

    private int id;

    private String name;

    private String about;

    private String imageUrl;

    private int totalPages;

    private String authorName;

    private String genres;

}
