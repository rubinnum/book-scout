package com.bookscout.backend.mapper;

import com.bookscout.backend.dto.BookDTO;
import com.bookscout.backend.model.Book;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BookMapper implements Function<Book, BookDTO> {

    @Override
    public BookDTO apply(Book book) {
        return new BookDTO(
                book.getTitle(),
                book.getAuthor(),
                book.getDescription(),
                book.getPublishedDate(),
                book.getPageCount(),
                book.getThumbnail());
    }
}
