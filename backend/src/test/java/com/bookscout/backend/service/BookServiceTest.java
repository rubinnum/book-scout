package com.bookscout.backend.service;

import com.bookscout.backend.mapper.BookApiResponseMapper;
import com.bookscout.backend.mapper.BookMapper;
import com.bookscout.backend.model.Book;
import com.bookscout.backend.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private GoogleBooksService googleBooksService;

    @Mock
    private BookMapper bookMapper;

    private BookService bookService;

    @BeforeEach
    void setUp() {
        BookApiResponseMapper bookApiResponseMapper = new BookApiResponseMapper();
        bookService = new BookService(googleBooksService, bookRepository, bookApiResponseMapper, bookMapper);
    }

    @Test
    void booksAreSavedIntoDatabaseAndReturned() throws IOException {
        // Given
        String subject = "psychology";
        when(googleBooksService.fetchBooksBySubject(subject, 0)).thenReturn(getMockResponse());

        // When
        bookService.getBooksBySubject(subject);

        // Then
        verify(bookRepository).deleteAll();
        verify(bookRepository, times(3)).save(any(Book.class));
        verify(bookRepository).findAll();
    }

    private String getMockResponse() throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get("src/test/resources/mock_psychology_response.json"));
        return new String(encoded);
    }
}