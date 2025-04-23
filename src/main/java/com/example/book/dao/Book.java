package com.example.book.dao;

import jakarta.persistence.*;
import lombok.Data;

// book 이라는 테이블에
// 각 어노테이션은 바로 밑의 줄에 작성된 코드블록만 반영됨
@Data
@Entity // JPA에게 아래 클래스를 등록하여 테이블을 생성, 각 필드들을 하나의 book 객체로 묶는 역할
@Table(name="books") // 테이블명을 DB와 다르게 매핑해야 할 때
public class Book {

    @Id   // id 컬럼이 기본키 컬럼
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long price; // 책 판매금액

    @Column(nullable = false)
    private String title; // 제목
    private String author;
    private String genre;
    private int page;
}
