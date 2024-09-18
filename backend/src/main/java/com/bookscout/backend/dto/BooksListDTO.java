package com.bookscout.backend.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class BooksListDTO {
    private final List<BookDTO> books;

    public BooksListDTO(List<BookDTO> books) {
        this.books = books;
    }
}
