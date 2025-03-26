package com.example.newsService.service.impl;

import com.example.newsService.aop.Approved;
import com.example.newsService.exception.EntityNotFoundException;
import com.example.newsService.model.News;
import com.example.newsService.model.User;
import com.example.newsService.repository.NewsRepository;
import com.example.newsService.repository.NewsSpecification;
import com.example.newsService.service.NewsService;
import com.example.newsService.service.annotationForParametrs.EntityId;
import com.example.newsService.service.annotationForParametrs.UserId;
import com.example.newsService.utils.BeanUtils;
import com.example.newsService.web.model.RequestFilter;
import com.example.newsService.web.model.news.NewsFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final UserServiceImpl userService;
    private final NewsRepository newsRepository;


    @Override
    public List<News> findAll(RequestFilter filter) {
        return newsRepository.findAll(PageRequest.of(
                filter.getPageNumber(), filter.getPageSize()
        )).getContent();
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                "Новость с ID {0} не найден", id
        )));
    }

    @Override
    public News save(News news) {
        return newsRepository.save(news);
    }

    @Override
    @Approved
    public News updateNews(News news, @UserId Long userId) {
        News extendedNews = findById(news.getId());
        User user = userService.findById(news.getUser().getId());
        BeanUtils.copyNonNullProperties(news, extendedNews);
        extendedNews.setUser(user);
        return newsRepository.save(extendedNews);
    }

    @Override
    @Approved
    public void deleteNewsById(@EntityId Long id, @UserId Long userId) {

        newsRepository.deleteById(id);
    }

    @Override
   // @Transactional
    public List<News> filterBy(NewsFilter newsFilter) {
        return newsRepository.findAll(NewsSpecification.withFilter(newsFilter),
                PageRequest.of(
                        newsFilter.getPageNumber(), newsFilter.getPageSize()
                )).getContent();
    }

}
