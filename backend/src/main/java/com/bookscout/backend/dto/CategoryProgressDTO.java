package com.bookscout.backend.dto;

import com.bookscout.backend.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CategoryProgressDTO {
    private Category category;
    private Integer booksDisplayed;
    private Integer booksFetched;
}
