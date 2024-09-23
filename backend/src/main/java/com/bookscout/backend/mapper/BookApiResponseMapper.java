package com.bookscout.backend.mapper;

import com.bookscout.backend.utilities.BookApiResponse;
import com.bookscout.backend.model.Book;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BookApiResponseMapper implements Function<BookApiResponse, Book> {
    @Override
    public Book apply(BookApiResponse bookApiResponse) {
        String author = (bookApiResponse.getVolumeInfo().getAuthors() != null)
                ? bookApiResponse.getVolumeInfo().getAuthors().get(0)
                : "Unknown";
        String thumbnail = (bookApiResponse.getVolumeInfo().getImageLinks() != null)
                ? (bookApiResponse.getVolumeInfo().getImageLinks().getThumbnail())
                : "https://i.ibb.co/RY7SFkV/image-not-available.jpg";
        return new Book(
                bookApiResponse.getVolumeInfo().getTitle(),
                author,
                bookApiResponse.getVolumeInfo().getDescription(),
                bookApiResponse.getVolumeInfo().getPublishedDate(),
                bookApiResponse.getVolumeInfo().getPageCount(),
                thumbnail
        );
    }
}
