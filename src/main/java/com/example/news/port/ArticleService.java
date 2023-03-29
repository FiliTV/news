package com.example.news.port;

import com.example.news.dto.ArticleDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {
    void insert();

    public List<ArticleDto> findAll(Pageable pageable, String author, List<String> tags, String title, String search);
}
