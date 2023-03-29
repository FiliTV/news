package com.example.news.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {
}
