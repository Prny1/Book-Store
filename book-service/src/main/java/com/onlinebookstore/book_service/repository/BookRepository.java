package com.onlinebookstore.book_service.repository;

import com.onlinebookstore.book_service.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity , Long> {

    BookEntity findByTitle(String title);

    List<BookEntity> findByCategory(String category);
}
