package com.example.news.client;

import com.example.news.mapper.ArticleDtoMapper;
import com.example.news.client.model.AlgoliaResponse;
import com.example.news.dto.ArticleDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AlgoliaClient {
    private ObjectMapper objectMapper = new ObjectMapper();
    private OkHttpClient client = new OkHttpClient();;


    public List<ArticleDto> getArticles() {
        log.info("calling algolia ...");

        Request request = new Request.Builder()
                .url("https://hn.algolia.com/api/v1/search_by_date?query=java")
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();

            AlgoliaResponse responseDto = objectMapper.readValue(responseBody, AlgoliaResponse.class);

            return responseDto.getHits().stream()
                    .map(ArticleDtoMapper::fromHit)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
