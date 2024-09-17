package com.bookscout.backend.dto;

import com.bookscout.backend.model.Category;
import lombok.Getter;

import java.util.List;

@Getter
public class CategoriesDTO {
    private final List<Category> categories;

    public CategoriesDTO(List<Category> categories) {
        this.categories = categories;
    }
}
