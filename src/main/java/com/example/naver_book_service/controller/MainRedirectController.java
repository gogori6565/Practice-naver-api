package com.example.naver_book_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainRedirectController {

    @GetMapping("/")
    public String redirectToBookSearch() {
        return "redirect:/book/search";
    }

}
