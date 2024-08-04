package com.example.naver_book_service.controller;

import com.example.naver_book_service.vo.BookVO;
import com.example.naver_book_service.vo.ResultVO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@Controller
public class BookController {

    @GetMapping("/book/search")
    public String search() {
        return "search";
    }

    @GetMapping("/book/result")
    public String result(@RequestParam("bookname") String text, Model model) {
        log.info("Received text: {}", text);
        model.addAttribute("text", text);

        //네이버 검색 API 요청
        String clientId = "clientId";
        String clientSecret = "clientSecret";

        //String apiURL
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/book.json")
                .queryParam("query", text)
                .queryParam("display", 10)
                .queryParam("start", 1)
                .queryParam("sort", "sim")
                .encode()
                .build()
                .toUri();

        //Spring 요청 제공 클래스
        RequestEntity<Void> req = RequestEntity.get(uri)
                .header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret)
                .build();

        //Spring 제공 RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(req, String.class); //응답 본문 String 타입으로 변환

        //JSON 파싱 (JSON 문자열을 객체로 만듦, 문서화)
        ObjectMapper om = new ObjectMapper();
        ResultVO resultVO = null;

        try{
            resultVO = om.readValue(resp.getBody(), ResultVO.class);
        } catch (JsonMappingException e){
            e.printStackTrace();
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }

        //result.mustache 에 값 넘겨주기
        List<BookVO> books = resultVO.getItems();
        model.addAttribute("books", books);

        return "result";
    }
}
