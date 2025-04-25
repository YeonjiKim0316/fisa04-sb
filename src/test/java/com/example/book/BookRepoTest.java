package com.example.book;

import com.example.book.dao.Book;
import com.example.book.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // db에 실제 반영을 어떻게 할지 여부를 작성
public class BookRepoTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void insertTest(){

    // given: 초기 상태
    Book book = new Book();
    book.setTitle("짱구는 못말려");
    book.setAuthor("신짱구");
    book.setPrice(30000L);
        // 한 코드줄에서 특정 변수에 삽입하거나, 조회하거나 할 값들을 묶어서 관리할 수 있게 됩니다.
//        Book book = Book.builder()
//                .title("짱구는 못말려").author("신짱구").price(30000L).build();

    Book savedBook = bookRepository.saveAndFlush(book);

    // when: 특정 동작이 일어나면
    // 저장된 책을 조회
//    Book selected = bookRepository.findById(savedBook.getId()).orElse(null);
    Optional<Book> selected = bookRepository.findById(savedBook.getId());

    // then: 결과 정의
        assertEquals("짱구는 못말려", selected.get().getTitle(), "책이름이 틀립니다");
        assertEquals(savedBook.getId(), selected.get().getId(), "조회된 책이 삽입된 책과 책번호가 틀립니다");

    }

}
