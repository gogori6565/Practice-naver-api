package com.example.naver_book_service.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookVO {
    private String title;
    private String link;
    private String image;
    private String author;
    private String discount;
    private String publisher;
    private String isbn;
    private String description;
    private String pubdate;
}
