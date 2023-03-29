package com.example.news.controller;

import com.example.news.repository.Article;
import com.example.news.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/all")
@AllArgsConstructor
public class TestController {

    ArticleRepository articleRepository;

    @GetMapping
    public List<Article> articlelist(){
        return articleRepository.findAll();
    }
}
