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
public class AlgoliaResponse {
    @JsonProperty("hits")
    private List<Hit> hits;
    @JsonProperty("nbHits")
    private int nbHits;
    @JsonProperty("page")
    private int page;
    @JsonProperty("nbPages")
    private int nbPages;
    @JsonProperty("hitsPerPage")
    private int hitsPerPage;
    @JsonProperty("exhaustiveNbHits")
    private boolean exhaustiveNbHits;
    @JsonProperty("exhaustiveTypo")
    private boolean exhaustiveTypo;
    @JsonProperty("exhaustive")
    private Exhaustive exhaustive;
    @JsonProperty("query")
    private String query;
    @JsonProperty("params")
    private String params;
    @JsonProperty("processingTimeMS")
    private int processingTimeMS;
    @JsonProperty("processingTimingsMS")
    private ProcessingTimingsMS processingTimingsMS;
    @JsonProperty("serverTimeMS")
    private int serverTimeMS;
}

