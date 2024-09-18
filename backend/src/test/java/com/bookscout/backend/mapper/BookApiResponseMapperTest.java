package com.bookscout.backend.mapper;

import com.bookscout.backend.utilities.BookApiResponse;
import com.bookscout.backend.model.Book;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BookApiResponseMapperTest {

    private final BookApiResponseMapper bookApiResponseMapper = new BookApiResponseMapper();

    @Test
    void bookMapperDoesNotReturnNull() {
        // Given
        BookApiResponse bookApiResponse = new BookApiResponse();
        BookApiResponse.VolumeInfo volumeInfo = new BookApiResponse.VolumeInfo();
        BookApiResponse.VolumeInfo.ImageLinks imageLinks = new BookApiResponse.VolumeInfo.ImageLinks();
        imageLinks.setThumbnail("http://books.google.com/books/content?id=raYsDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api");
        volumeInfo.setTitle("Alcohol Use and Sexual Risk Behaviour");
        volumeInfo.setAuthors(null);
        volumeInfo.setDescription("\"The link between alcohol use and sexual behaviour has serious implications for the health of populations due to the advent of HIV infection. WHO coordinated a multi-country study to identify factors related to risky sexual behaviour among alcohol users in diverse cultural settings. The countries involved included: Belarus, India, Mexico, Kenya, Romania, the Russian Federation, South Africa and Zambia.\"--Back cover.");
        volumeInfo.setPageCount(148);
        volumeInfo.setImageLinks(imageLinks);
        bookApiResponse.setVolumeInfo(volumeInfo);

        // When
        Book book = bookApiResponseMapper.apply(bookApiResponse);

        // Then
        assertThat(book).isNotNull();
    }
}