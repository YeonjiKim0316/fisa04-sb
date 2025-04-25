package com.example.book.controller;

import com.example.book.dao.Book;
import com.example.book.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 📘 책 목록
    @GetMapping
    public String getAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "bookmain"; // bookmain.html + book-list fragment
    }

    // ➕ 책 추가 폼
    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book()); // 폼 바인딩용 빈 객체
        return "form-add"; // bookmain.html + form-add fragment
    }

    // 💾 책 저장
    @PostMapping
    public String saveBook(@ModelAttribute Book book) {
        bookService.saveBook(book);
        return "redirect:/books"; // 저장 후 목록으로 리다이렉트
    }

    // 🔍 책 검색 폼
    @GetMapping("/search")
    public String searchForm() {
        return "form-search"; //bookmain.html + form-search fragment
    }

    // 📊 조건 검색
    @GetMapping("/select4")
    public String searchBooks(@RequestParam String title,
                              @RequestParam String author,
                              Model model) {
//        List<Book> results = bookService.getBookByTitleAndAuthor(title, author);
        List<Book> results = bookService.getBookByTitleContainingOrAuthorContaining(title, author);


        model.addAttribute("books", results);
        return "bookmain"; // 검색 결과는 목록 fragment로 출력
    }

    // 🗑 삭제
    @PostMapping("/{id}/delete")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBookForm(@PathVariable Long id, Model model) {
        Optional<Book> bookOptional = bookService.getBookById(id);
        if (bookOptional.isPresent()) {
            model.addAttribute("book", bookOptional.get());
            return "form-edit";
        } else {
            return "redirect:/books"; // 존재하지 않으면 목록으로
        }
    }

    // ✏️ 수정
    @PostMapping("/{id}/update")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book) {
        book.setId(id);
        bookService.saveBook(book);
        return "redirect:/books";
    }

    // 수정 화면에 bootstrap 을 입혀보시고

}
