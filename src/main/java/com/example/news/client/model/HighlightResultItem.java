package com.example.news.client.model;

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
class HighlightResultItem {
    @JsonProperty("value")
    private String value;
    @JsonProperty("matchLevel")
    private String matchLevel;
    @JsonProperty("fullyHighlighted")
    private Boolean fullyHighlighted;
    @JsonProperty("matchedWords")
    private List<String> matchedWords;

    // getters y setters
}