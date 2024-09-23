package com.bookscout.backend.repository;

import com.bookscout.backend.model.Book;
import com.bookscout.backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT COUNT(b) FROM Book b WHERE b.category = :category")
    int getTheNumberOfFetchedBooksByCategory(Category category);
}
