package com.example.book.controller;

import com.example.book.dao.Book;
import com.example.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Tag(name = "swagger 테스트 API", description = "swagger 테스트를 진행하는 API")
//@RestController
@Controller
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
    public String getAllBooks(Model model) { // String으로 리턴타입이 바뀌는 이유는 bookmain의 html 코드와 model로 전달한 데이터가 합쳐서 화면을 만든 html 코드가 최종 리턴되므로
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "bookmain"; // model을 통해 렌더링할 html 파일의 경로를 적어줍니다.
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

    // 책 내용 수정 - 삽입을 위해 만든 saveBook을 id를 달아서 재사용
    @PutMapping("/{id}") // 전부를 가져가서 변경
    public void updateBookById(@PathVariable Long id, @RequestBody Book book){
        // 1. 전체 내용을 books 테이블에서 조회
        book.setId(id);
        // 2. 클라이언트가 Body에 준 book의 모든 변경사항을 행에 반영한다
        // 3. 그 결과를 service를 통해 repository로 전달한다
        bookService.saveBook(book);
    }
    
    @PatchMapping("/{id}") // 변경할 사항만 가져가서 변경
    public void updateBookById2(@PathVariable Long id, @RequestBody Book book) {
        // 1. 전체 내용을 books 테이블에서 조회
        // 2. 클라이언트가 Body에 준 book의 일부 변경사항을 행에 반영한다
        // 3. 그 결과를 service를 통해 repository로 전달한다
        bookService.updateBookById2(id, book);
    }

    // 책 삭제
    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id){
        bookService.deleteBookById(id);
    }

    // 책을 저자와 책이름으로 조회하는 API
    @GetMapping("/select1") // select?title=스프링부트&author=장정우
    public List<Book> getBookByTitleAndAuthor(@RequestParam String title, @RequestParam String author) {
        return bookService.getBookByTitleAndAuthor(title, author);
    }

    //    - 책이름으로 책을 검색하는 API
    @GetMapping("/select")
    public List<Book> getBookByTitle(@RequestParam String title){
        return bookService.getBookByTitle(title);
    }

    //    - 책의 최소/최대 페이지로 책을 검색하는 API
    //    - //    select3?minPage=10&maxPage=3001
    @GetMapping("/select3")
    public List<Book> getBookByPagesBetween(@RequestParam int minPage, @RequestParam int maxPage) {
        return bookService.getBookByPagesBetween(minPage, maxPage);
    }
}
