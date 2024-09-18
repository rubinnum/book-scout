package com.bookscout.backend.service;

import com.bookscout.backend.repository.CategoryProgressRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryProgressService {
    private final CategoryProgressRepository categoryProgressRepository;

    public CategoryProgressService(CategoryProgressRepository categoryProgressRepository) {
        this.categoryProgressRepository = categoryProgressRepository;
    }

//    public
}
