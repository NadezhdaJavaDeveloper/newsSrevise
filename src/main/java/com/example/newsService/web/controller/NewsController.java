package com.example.newsService.web.controller;


import com.example.newsService.mapper.NewsMapper;
import com.example.newsService.model.News;
import com.example.newsService.service.NewsService;
import com.example.newsService.web.model.*;
import com.example.newsService.web.model.news.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;
    private final NewsMapper newsMapper;

    //запрос http://localhost:8080/api/news/filter?pageSize=10&pageNumber=0&categoryName=Наука
    @GetMapping("/filter")
    public ResponseEntity<NewsListResponse> filterBy(@Valid NewsFilter filter) {
        return ResponseEntity.ok(
                newsMapper.newsListToNewsListResponse(
                        newsService.filterBy(
                                filter)
                )
        );
    }

    @GetMapping
    public ResponseEntity<NewsListResponse> findAll(@Valid RequestFilter filter) {
        return ResponseEntity.ok(newsMapper
                .newsListToNewsListResponse(newsService.findAll(filter)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponseForFindById> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                newsMapper.newsToResponseForFindById(
                        newsService.findById(id)
                )
        );
    }

    @PostMapping
    public ResponseEntity<NewsResponse> create(@RequestBody @Valid UpsertNewsRequest request) {
        News news = newsService.save(newsMapper.requestToNews(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newsMapper.newsToResponse(news));
    }

    @PutMapping("/{id}")

    public ResponseEntity<NewsResponse> update(@PathVariable Long id,
                                               @RequestBody @Valid UpsertNewsRequest request) {
        News updateNews = newsService.updateNews(newsMapper.requestToNews(id, request), request.getUserId());

        return ResponseEntity.ok(newsMapper.newsToResponse(updateNews));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestParam Long userId) {
        newsService.deleteNewsById(id, userId);
        return ResponseEntity.noContent().build();
    }
}
