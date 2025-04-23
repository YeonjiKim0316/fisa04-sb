package com.example.book.service;

import com.example.book.dao.Book;
import com.example.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 특정 동작을 수행하기 위해서 repository에서 값을 실어다 나릅니다.
// public이랑 private을 사용합니다. - 캡슐화 구현
@Service
public class BookService {

    @Autowired // 생략 가능
    private BookRepository bookRepository; // 스프링부트가 의존성 주입해줍니다. 그래서 우리가 직접 만들고 사용할 필요가 없습니다.

    // 생성자
    BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 모든 책 조회
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

}
