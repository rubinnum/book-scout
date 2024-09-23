package com.bookscout.backend.service;

import com.bookscout.backend.dto.CategoryProgressDTO;
import com.bookscout.backend.mapper.CategoryProgressMapper;
import com.bookscout.backend.model.Category;
import com.bookscout.backend.model.CategoryProgress;
import com.bookscout.backend.repository.CategoryProgressRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CategoryProgressService {
    private final CategoryProgressRepository categoryProgressRepository;
    private final CategoryProgressMapper categoryProgressMapper;

    public CategoryProgressService(CategoryProgressRepository categoryProgressRepository, CategoryProgressMapper categoryProgressMapper) {
        this.categoryProgressRepository = categoryProgressRepository;
        this.categoryProgressMapper = categoryProgressMapper;
    }

    public CategoryProgressDTO getCategoryByName(Category category) {
        CategoryProgress categoryProgress = categoryProgressRepository.findByCategory(category);
        return categoryProgressMapper.apply(categoryProgress);
    }

    public void initializeCategoryProgress(Category category, int booksFetched) {
        CategoryProgress categoryProgress = CategoryProgress.builder().
                category(category).
                booksFetched(booksFetched).
                booksDisplayed(0).
                build();
        categoryProgressRepository.save(categoryProgress);
    }

    @Transactional
    public void updateDisplayedBooksProgressByCategory(Category category, int booksDisplayed) {
        categoryProgressRepository.updateDisplayedBooks(category, booksDisplayed);
    }

    @Transactional
    public void updateFetchedBooksProgressByCategory(Category category, int booksFetched) {
        categoryProgressRepository.updateFetchedBooks(category, booksFetched);
    }
}
