package com.bookscout.backend.service;

import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final GoogleBooksService googleBooksService;

    public BookService(GoogleBooksService googleBooksService) {
        this.googleBooksService = googleBooksService;
    }

    public String getBooksBySubject(String subject) {
        return googleBooksService.searchBooksBySubject(subject);
    }
}
