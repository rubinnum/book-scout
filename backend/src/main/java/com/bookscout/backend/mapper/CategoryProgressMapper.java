package com.bookscout.backend.mapper;

import com.bookscout.backend.dto.CategoryProgressDTO;
import com.bookscout.backend.model.CategoryProgress;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CategoryProgressMapper implements Function<CategoryProgress, CategoryProgressDTO>  {
    @Override
    public CategoryProgressDTO apply(CategoryProgress categoryProgress) {
        return new CategoryProgressDTO(
                categoryProgress.getCategory(),
                categoryProgress.getBooksDisplayed(),
                categoryProgress.getBooksFetched()
        );
    }
}
