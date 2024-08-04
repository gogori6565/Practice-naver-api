package com.example.naver_book_service.vo;

import lombok.*;
import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ResultVO {
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<BookVO> items;
}
