package com.bookscout.backend.dto;

import com.bookscout.backend.model.Book;
import lombok.Getter;

import java.util.List;

@Getter
public class BooksListDTO {
    private final List<Book> books;

    public BooksListDTO(List<Book> books) {
        this.books = books;
    }
}
