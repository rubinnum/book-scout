package com.bookscout.backend.dto;

import com.bookscout.backend.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BookDTO {
    private String title;
    private String author;
    private String description;
    private String publishedDate;
    private Integer pageCount;
    private String thumbnail;
    private Category category;
}
