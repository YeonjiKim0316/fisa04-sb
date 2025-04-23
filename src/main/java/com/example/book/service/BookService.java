package com.example.book.service;

import com.example.book.dao.Book;
import com.example.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> getBookByTitleAndAuthor(String title, String author) {
        return bookRepository.findByTitleContainingAndAuthorContaining(title, author);
    }

    public Book updateBookById2(Long id, Book book) {
        // 1. 전체 내용을 books 테이블에서 조회
        Book exsitingBook = bookRepository.findById(id).orElse(null); // id가 없으면 null을 반환

        // 2. 클라이언트가 Body에 준 book의 일부 변경사항을 행에 반영한다
        if (book.getTitle() != null) {
            // 책이름이 기존과 다르면 책이름 변경
            exsitingBook.setTitle(book.getTitle());
        } else if (book.getAuthor() != null ) {
            exsitingBook.setAuthor(book.getAuthor());
        } else if (book.getPage() != 0 ) {
            exsitingBook.setPage(book.getPage());
        }
        // 3. 그 결과를 service를 통해 repository로 전달한다
//        기존 book의 변경사항을 더해서 다시 db에 반영
        return bookRepository.save(exsitingBook);
    }
}
