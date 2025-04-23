package com.example.book.controller;

import com.example.book.dao.Book;
import com.example.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // 생성자
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    // 전체 책 조회
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
    // 특정 id로 특정 책 조회

    // 책 삽입

    // 책 내용 수정

    // 책 삭제

}
