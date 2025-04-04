package com.example.newsService.web.model.news;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsListResponse {
    private List<NewsResponse> newsList = new ArrayList<>();

}
