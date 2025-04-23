package com.example.book.repository;

import com.example.book.dao.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository                                        // 엔터티명, 엔터티의 ID자료형
public interface BookRepository extends JpaRepository<Book, Long> {
//    List<Book> findByAuthor(String author);

    // Author는 일부일치, Title은 일부일치 탐색
    List<Book> findByTitleContainingAndAuthorContaining(String title, String author);
    // jpa의 규칙에 맞는 메서드를 자동 생성해줍니다.
    // 필요한 경우 사용자 정의한 메서드를 만들어서 추상메서드로 등록해놓거나
    // 쿼리를 추가할 수도 있습니다.
}
