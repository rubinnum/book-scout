package com.bookscout.backend.controller;

import com.bookscout.backend.dto.CategoryProgressDTO;
import com.bookscout.backend.exception.CategoryProgressDoesNotExistException;
import com.bookscout.backend.model.Category;
import com.bookscout.backend.service.CategoryProgressService;
import com.bookscout.backend.service.CategoryService;
import com.bookscout.backend.utilities.Helper;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/v1")
public class CategoryProgressController {
    private final CategoryProgressService categoryProgressService;
    private final CategoryService categoryService;
    private final Helper helper;

    public CategoryProgressController(CategoryProgressService categoryProgressService, CategoryService categoryService, Helper helper) {
        this.categoryProgressService = categoryProgressService;
        this.categoryService = categoryService;
        this.helper = helper;
    }

    @GetMapping("/progress/{categoryName}")
    public CategoryProgressDTO getCategoryProgress(@PathVariable String categoryName) {
        Category category = categoryService.getCategoryByName(categoryName);
        if (!helper.categoryProgressExist(category)) {
            throw new CategoryProgressDoesNotExistException("Category Progress for " + categoryName + " does not exist yet");
        }
        return categoryProgressService.getCategoryByName(category);
    }

    @PutMapping("/progress/{categoryName}")
    public void updateDisplayedBooksProgress(@PathVariable String categoryName, @RequestParam int booksDisplayed) {
        Category category = categoryService.getCategoryByName(categoryName);
        if (!helper.categoryProgressExist(category)) {
            throw new CategoryProgressDoesNotExistException("Category Progress for " + categoryName + " does not exist yet");
        }
        categoryProgressService.updateDisplayedBooksProgressByCategory(category, booksDisplayed);
    }
}
