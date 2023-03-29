package com.example.news.controller;

import com.example.news.dto.ArticleDto;
import com.example.news.port.ArticleService;
import com.example.news.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/articles")
public class ArticlesController {
    private final ArticleService articleService;
    private final AuthService authService;
    @PostMapping()
    public void insertArticles(@RequestHeader(value = "Authorization") String authorization) {

        authService.validateToken(extractTokenFromHeader(authorization));

        articleService.insert();
    }


    @GetMapping
    public List<ArticleDto> getArticles(@PageableDefault(page = 0, size = 5) Pageable pageable,
                                        @RequestParam(required = false) String author,
                                        @RequestParam(required = false) List<String> tags,
                                        @RequestParam(required = false) String title,
                                        @RequestParam(required = false) String search,
                                        @RequestHeader(value = "Authorization") String authorization
    ) {

        authService.validateToken(extractTokenFromHeader(authorization));
        return articleService.findAll(pageable, author, tags, title, search);
    }

    public static String extractTokenFromHeader(String headerValue) {
        if (headerValue == null || !headerValue.startsWith("Bearer ")) {
            return null;
        }
        return headerValue.substring(7);
    }

}
