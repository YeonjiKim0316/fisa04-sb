package com.example.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// URI와 메서드를 매핑하기 위한 프리젠테이션 계층
//@Controller // MVC 아키텍처에서 사용
@RestController // REST API 형식으로 결과를 리턴
@RequestMapping("/")
public class HelloController {

//    @GetMapping
//    public String sayHello() {
//        return "Hello SpringBoot!";
//    }

//    http://localhost:8080/신짱구
    @GetMapping("/{id}")
    public String sayHello2(@PathVariable String id) {
        return "Hello"+ id;
    }

//    http://localhost:8080/?name=%EC%8B%A0%EC%A7%B1%EA%B5%AC
    @GetMapping
    public String sayHello(@RequestParam(value = "name", defaultValue = "Guest") String name) {
        return "Hello, " + name + "!";
    }
}
