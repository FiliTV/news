package com.example.news.client.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class HighlightResult {
    @JsonProperty("author")
    private HighlightResultItem author;
    @JsonProperty("comment_text")
    private HighlightResultItem commentText;
    @JsonProperty("story_title")
    private HighlightResultItem storyTitle;
    @JsonProperty("story_url")
    private HighlightResultItem storyUrl;
    @JsonProperty("title")
    private HighlightResultItem title;
    @JsonProperty("url")
    private HighlightResultItem url;
    @JsonProperty("story_text")
    private HighlightResultItem storyText;


    // getters y setters
}