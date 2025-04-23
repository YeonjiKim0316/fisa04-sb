package com.example.book.dao;

import jakarta.persistence.*;
import lombok.Data;

// book 이라는 테이블에
@Data
@Entity // 테이블을 생성, 각 필드들을 하나의 book 객체로 묶는 역할
//@Table(name="books") // 테이블명을 DB와 다르게 매핑해야 할 때
public class Book {

    @Id   // id 컬럼이 기본키 컬럼
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long price; // 책 판매금액

    @Column(nullable = false)
    String title; // 제목
    String author;
    String genre;
    int page;
}
