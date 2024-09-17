package com.bookscout.backend.dto;

import com.bookscout.backend.model.Book;

import java.util.List;

public class BooksListDTO {
    private final List<Book> books;

    public BooksListDTO(List<Book> books) {
        this.books = books;
    }
}
