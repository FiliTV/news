package com.example.news.service;

import com.example.news.client.AlgoliaClient;
import com.example.news.dto.ArticleDto;
import com.example.news.repository.Article;
import com.example.news.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;


class ArticleServiceImplTest {

    @Test
    void testInsert() {
        AlgoliaClient algoliaClient = mock(AlgoliaClient.class);
        ArticleRepository articleRepository = mock(ArticleRepository.class);
        MongoTemplate mongoTemplate = mock(MongoTemplate.class);
        ArticleServiceImpl articleService = new ArticleServiceImpl(algoliaClient, articleRepository, mongoTemplate);

        List<ArticleDto> expected = Collections.singletonList(ArticleDto.builder().title("test").build());
        BDDMockito.given(algoliaClient.getArticles()).willReturn(expected);

        articleService.insert();

        then(algoliaClient).should().getArticles();

        List<Article> articles = Collections.singletonList(Article.builder().title("test").build());
        then(articleRepository).should().saveAll(articles);

    }

    @Test
    void testGet() {
        AlgoliaClient algoliaClient = mock(AlgoliaClient.class);
        ArticleRepository articleRepository = mock(ArticleRepository.class);
        MongoTemplate mongoTemplate = mock(MongoTemplate.class);
        ArticleServiceImpl articleService = new ArticleServiceImpl(algoliaClient, articleRepository, mongoTemplate);


        List<Article> expected = Collections.singletonList(Article.builder()
                        .author("author")
                        .title("title")
                .build());
        Query query = new Query();
        query.addCriteria(Criteria.where("author").is("author"));
        query.addCriteria(Criteria.where("_tags").in(Arrays.asList("tag")));
        query.addCriteria(Criteria.where("title").is("title"));

        String month = "september";
        Month monthEnum = Month.valueOf(month.toUpperCase());
        YearMonth yearMonth = YearMonth.now().withMonth(monthEnum.getValue());
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();
        query.addCriteria(Criteria.where("created_at").gte(firstDayOfMonth).lte(lastDayOfMonth));
        query.with(Pageable.ofSize(5));

        given(mongoTemplate.find(query, Article.class)).willReturn(expected);

        List<ArticleDto> list = articleService.findAll(Pageable.ofSize(5),
                "author", Arrays.asList("tag"), "title", "september");

        assertEquals(expected.get(0).getTitle(),list.get(0).getTitle());
        assertEquals(expected.get(0).getAuthor(),list.get(0).getAuthor());
    }
}