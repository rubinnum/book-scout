package com.bookscout.backend.repository;

import com.bookscout.backend.model.Book;
import com.bookscout.backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    int countBooksByCategory(Category category);

    @Query(value = "SELECT * FROM books b WHERE b.category_id = :categoryId LIMIT 10 OFFSET :startIndex", nativeQuery = true)
    List<Book> findTenNotDisplayedBooksByCategory(Long categoryId, int startIndex);

}
