package com.bookscout.backend.repository;

import com.bookscout.backend.model.Category;
import com.bookscout.backend.model.CategoryProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryProgressRepository extends JpaRepository<CategoryProgress, Long> {
    @Modifying
    @Query("update CategoryProgress cp set cp.booksDisplayed = :booksDisplayed where cp.category = :category")
    void updateCategoryProgressesByCategory(@Param("category") Category category, @Param("booksDisplayed") Integer booksDisplayed);
}

