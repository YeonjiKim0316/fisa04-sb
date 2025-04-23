package com.example.book.controller;

import com.example.book.dao.Book;
import com.example.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @GetMapping("/{id}") // @PathVarible  Optional<자료형>은 null로 발생하는 예외를 처리해주는 wrapper class입니다.
    public Optional<Book> getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    // 책 삽입
    @PostMapping        // @RequestBody : body에 실려서 오는 값을 Book 자료형으로 받겠음
    public Book saveBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    // 책 내용 수정

    // 책 삭제

}
