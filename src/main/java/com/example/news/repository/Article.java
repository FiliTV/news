package com.example.news.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "articles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    private String id;
    private String title;
    private String url;
    private String author;
    private String commentText;
    private List<String> tags;
    private LocalDateTime createdAt;

    // getters y setters
}
