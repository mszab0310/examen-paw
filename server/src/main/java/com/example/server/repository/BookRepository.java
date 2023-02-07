package com.example.server.repository;

import com.example.server.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAll();

    @Query("SELECT b FROM Book b WHERE " +
            "UPPER(b.description) LIKE CONCAT('%',UPPER(:query), '%')" +
            "Or UPPER(b.title) LIKE CONCAT('%', UPPER(:query), '%') Or upper(b.author) LIKE CONCAT('%', UPPER(:query), '%') ")
    List<Book> searchBooks(String query);
}
