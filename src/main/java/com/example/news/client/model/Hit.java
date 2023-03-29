package com.example.news.client.model;

import com.example.news.client.model.HighlightResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public
class Hit {
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("title")
    private String title;
    @JsonProperty("url")
    private String url;
    @JsonProperty("author")
    private String author;
    @JsonProperty("points")
    private Integer points;
    @JsonProperty("story_text")
    private String storyText;
    @JsonProperty("comment_text")
    private String commentText;
    @JsonProperty("num_comments")
    private Integer numComments;
    @JsonProperty("story_id")
    private Integer storyId;
    @JsonProperty("story_title")
    private String storyTitle;
    @JsonProperty("story_url")
    private String storyUrl;
    @JsonProperty("parent_id")
    private Integer parentId;
    @JsonProperty("created_at_i")
    private Long createdAtI;
    @JsonProperty("_tags")
    private List<String> tags;
    @JsonProperty("objectID")
    private String objectID;
    @JsonProperty("_highlightResult")
    private HighlightResult highlightResult;

    // getters y setters
}
