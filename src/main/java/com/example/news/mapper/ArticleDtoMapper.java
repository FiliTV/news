package com.example.news.mapper;

import com.example.news.client.model.Hit;
import com.example.news.dto.ArticleDto;
import com.example.news.repository.Article;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class ArticleDtoMapper {
    private ArticleDtoMapper() {}

    public static ArticleDto fromArticle(Article article) {
        return new ArticleDto(
                article.getTitle(),
                article.getUrl(),
                article.getAuthor(),
                article.getCommentText(),
                article.getTags(),
                article.getCreatedAt()
        );
    }
    public static ArticleDto fromHit(Hit hit) {
        String title = hit.getStoryTitle();
        String url = hit.getStoryUrl();
        String author = hit.getAuthor();
        String commentText = hit.getCommentText();
        LocalDateTime createdAt = LocalDateTime.parse(hit.getCreatedAt(), DateTimeFormatter.ISO_DATE_TIME);

        return new ArticleDto(title, url, author, commentText, hit.getTags(),createdAt);
    }

    public static Article fromArticleDto(ArticleDto articleDto) {
        log.info(articleDto.toString());
        Article article = new Article();
        article.setTitle(articleDto.getTitle());
        article.setUrl(articleDto.getUrl());
        article.setAuthor(articleDto.getAuthor());
        article.setCommentText(articleDto.getCommentText());
        article.setCreatedAt(articleDto.getCreatedAt());
        return article;
    }
}
