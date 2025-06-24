package com.example.naver_book_service.controller;

import com.example.naver_book_service.service.BookService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/book/search")
    public String search() {
        return "search";
    }

    @GetMapping("/book/result")
    public String result(@RequestParam("bookname") String text, Model model) {
        log.info("Received text: {}", text);
        model.addAttribute("text", text);

        model.addAttribute("books", bookService.book(text));

        return "result";
    }
}
