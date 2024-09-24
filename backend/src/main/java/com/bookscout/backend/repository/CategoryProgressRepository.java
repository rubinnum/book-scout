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
    @Query("UPDATE CategoryProgress cp SET cp.booksDisplayed = :booksDisplayed WHERE cp.category = :category")
    void updateDisplayedBooks(@Param("category") Category category, @Param("booksDisplayed") int booksDisplayed);

    @Modifying
    @Query("UPDATE CategoryProgress cp SET cp.booksFetched = :booksFetched, cp.fetchesNumber = :fetchesNumber WHERE cp.category = :category")
    void updateFetchedBooksAndFetchesNumber(Category category, int booksFetched, int fetchesNumber);

    @Query("SELECT cp.booksDisplayed FROM CategoryProgress cp WHERE cp.category = :category")
    int findDisplayedBooks(Category category);

    @Query("SELECT cp.booksFetched FROM CategoryProgress cp WHERE cp.category = :category")
    int findFetchedBooks(Category category);

    @Query("SELECT cp.fetchesNumber FROM CategoryProgress cp WHERE cp.category = :category")
    int findFetchesNumber(Category category);

    boolean existsByCategory(Category category);

    CategoryProgress findByCategory(Category categoryName);
}

