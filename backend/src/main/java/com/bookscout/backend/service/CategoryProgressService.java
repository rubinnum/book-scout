package com.bookscout.backend.service;

import com.bookscout.backend.dto.CategoryProgressDTO;
import com.bookscout.backend.mapper.CategoryProgressMapper;
import com.bookscout.backend.model.Category;
import com.bookscout.backend.model.CategoryProgress;
import com.bookscout.backend.repository.CategoryProgressRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryProgressService {
    private final CategoryProgressRepository categoryProgressRepository;
    private final CategoryProgressMapper categoryProgressMapper;

    public CategoryProgressService(CategoryProgressRepository categoryProgressRepository, CategoryProgressMapper categoryProgressMapper) {
        this.categoryProgressRepository = categoryProgressRepository;
        this.categoryProgressMapper = categoryProgressMapper;
    }

    public CategoryProgressDTO initializeCategoryProgress(Category category) {
        CategoryProgress categoryProgress = CategoryProgress.builder().
                category(category).
                booksFetched(0).
                booksDisplayed(0).
                build();
        categoryProgressRepository.save(categoryProgress);
        return categoryProgressMapper.apply(categoryProgress);
    }
}
