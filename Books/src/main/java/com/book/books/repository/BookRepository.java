package com.book.books.repository;

import com.book.books.model.BookData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookData,Integer> {
    public BookData findByBookName(String name);
}
