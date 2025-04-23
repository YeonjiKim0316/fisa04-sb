package com.example.book.repository;

import com.example.book.dao.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository                                        // 엔터티명, 엔터티의 ID자료형
public interface BookRepository extends JpaRepository<Book, Long> {
}
