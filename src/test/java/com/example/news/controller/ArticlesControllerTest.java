package com.example.news.controller;

import com.example.news.dto.ArticleDto;
import com.example.news.port.ArticleService;
import com.example.news.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;


import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;

class ArticlesControllerTest {

    @Test
    void testInsertArticles() {
        ArticleService articleService = mock(ArticleService.class);
        AuthService authService = mock(AuthService.class);
        ArticlesController articleController = new ArticlesController(articleService,authService);


        articleController.insertArticles("");

        then(articleService).should().insert();
    }

    @Test
    void testGetArticles() {
        ArticleService articleService = mock(ArticleService.class);
        AuthService authService = mock(AuthService.class);
        ArticlesController articleController = new ArticlesController(articleService,authService);


        List<ArticleDto> expected = Collections.singletonList(ArticleDto.builder()
                .author("test")
                .title("test")
                .build());
        given(articleService.findAll(Pageable.ofSize(5),
                "", Collections.emptyList(), "", "")).willReturn(expected);


        List<ArticleDto> list = articleController.getArticles(Pageable.ofSize(5),
                "", Collections.emptyList(), "", "","");



        assertEquals(list.get(0).getTitle(),expected.get(0).getTitle());
        assertEquals(list.get(0).getAuthor(),expected.get(0).getAuthor());

    }
}