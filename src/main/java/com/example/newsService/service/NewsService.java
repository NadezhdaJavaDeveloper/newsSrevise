package com.example.newsService.service;

import com.example.newsService.model.News;
import com.example.newsService.model.User;
import com.example.newsService.web.model.RequestFilter;
import com.example.newsService.web.model.news.NewsFilter;

import java.util.List;

public interface NewsService {

    List<News> findAll(RequestFilter filter);

    News findById(Long id);

    News save(News news);

    News updateNews (News news, Long userId);

    void deleteNewsById(Long id, Long userId);

    List<News> filterBy(NewsFilter newsFilter);
}
