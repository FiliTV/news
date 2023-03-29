package com.example.news.service;

import com.example.news.mapper.ArticleDtoMapper;
import com.example.news.port.ArticleService;
import com.example.news.client.AlgoliaClient;
import com.example.news.dto.ArticleDto;
import com.example.news.repository.Article;
import com.example.news.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final AlgoliaClient algoliaClient;
    private final ArticleRepository articleRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public void insert() {
        List<ArticleDto> hits = algoliaClient.getArticles();
        List<Article> articles = hits.stream()
                .map(ArticleDtoMapper::fromArticleDto)
                .collect(Collectors.toList());
        articleRepository.saveAll(articles);
    }

    @Override
    public List<ArticleDto> findAll(Pageable pageable, String author, List<String> tags, String title, String month) {
        Query query = new Query();
        if (author != null) {
            query.addCriteria(Criteria.where("author").is(author));
        }
        if (tags != null && !tags.isEmpty()) {
            query.addCriteria(Criteria.where("_tags").in(tags));
        }
        if (title != null) {
            query.addCriteria(Criteria.where("title").is(title));
        }
        if (month != null) {
            Month monthEnum = Month.valueOf(month.toUpperCase());
            YearMonth yearMonth = YearMonth.now().withMonth(monthEnum.getValue());
            LocalDate firstDayOfMonth = yearMonth.atDay(1);
            LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();
            query.addCriteria(Criteria.where("created_at").gte(firstDayOfMonth).lte(lastDayOfMonth));

        }
        query.with(pageable);
        return mongoTemplate.find(query,Article.class).stream()
                .map(ArticleDtoMapper::fromArticle)
                .collect(Collectors.toList());
    }
}
