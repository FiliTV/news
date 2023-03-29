package com.example.news.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleDto {
    private String title;
    private String url;
    private String author;
    private String commentText;
    private List<String> tags;
    private LocalDateTime createdAt;
}
