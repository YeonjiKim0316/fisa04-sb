package com.example.book.controller;

import com.example.book.dao.Book;
import com.example.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Tag(name = "swagger 테스트 API", description = "swagger 테스트를 진행하는 API")
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
    @Operation(summary = "Book 정보 모두 조회", description = "Book의 전체 정보를 조회합니다.")
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
    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id){
        bookService.deleteBookById(id);
    }

    // 책을 저자로 조회하는 API
    @GetMapping("/select10") // select10?title=스프링부트&author=장정우
    public List<Book> getBookByTitleAndAuthor(@RequestParam String title, @RequestParam String author) {
        return bookService.getBookByTitleAndAuthor(title, author);
    }
}
