package com.bookscout.backend.mapper;

import com.bookscout.backend.dto.BookDTO;
import com.bookscout.backend.model.Book;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BookMapper implements Function<BookDTO, Book> {
    @Override
    public Book apply(BookDTO bookDTO) {
        String author = (bookDTO.getVolumeInfo().getAuthors() != null)
                ? bookDTO.getVolumeInfo().getAuthors().get(0)
                : "Unknown";
        String thumbnail = (bookDTO.getVolumeInfo().getImageLinks() != null)
                ? (bookDTO.getVolumeInfo().getImageLinks().getThumbnail())
                : "https://bookscout-backend.s3.eu-north-1.amazonaws.com/image_not_available.jpg";
        return new Book(
                bookDTO.getVolumeInfo().getTitle(),
                author,
                bookDTO.getVolumeInfo().getDescription(),
                bookDTO.getVolumeInfo().getPublishedDate(),
                bookDTO.getVolumeInfo().getPageCount(),
                thumbnail
        );
    }
}
