package com.bookscout.backend.mapper;

import com.bookscout.backend.dto.BookDTO;
import com.bookscout.backend.model.Book;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BookMapper implements Function<BookDTO, Book> {
    @Override
    public Book apply(BookDTO bookDTO) {
        String author = "Unknown";
        if (bookDTO.getVolumeInfo().getAuthors() != null) {
            author = bookDTO.getVolumeInfo().getAuthors().get(0);
        }
        return new Book(
                bookDTO.getVolumeInfo().getTitle(),
                author,
                bookDTO.getVolumeInfo().getDescription(),
                bookDTO.getVolumeInfo().getPublishedDate(),
                bookDTO.getVolumeInfo().getPageCount(),
                bookDTO.getVolumeInfo().getImageLinks().getThumbnail()
        );
    }
}
