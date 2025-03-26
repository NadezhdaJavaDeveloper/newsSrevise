package com.example.newsService.mapper;

import com.example.newsService.model.News;
import com.example.newsService.web.model.news.NewsListResponse;
import com.example.newsService.web.model.news.NewsResponse;
import com.example.newsService.web.model.news.NewsResponseForFindById;
import com.example.newsService.web.model.news.UpsertNewsRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@DecoratedWith(NewsMapperDelegate.class)
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NewsMapper {

    News requestToNews(UpsertNewsRequest request);

    @Mapping(source = "newsId", target = "id")
    News requestToNews(Long newsId, UpsertNewsRequest request);

//    @Mapping(source = "user.nickname", target = "authorOfNews")
//    @Mapping(source = "category.categoryName", target = "categoryName")
//    @Mapping(target = "numberOfComments", expression = "java(news.getCommentList().size())")
    NewsResponse newsToResponse(News news);

    NewsResponseForFindById newsToResponseForFindById (News news);

    default NewsListResponse newsListToNewsListResponse(List<News> newsList) {

        NewsListResponse response = new NewsListResponse();
        response.setNewsList(newsList.stream()
                .map(this::newsToResponse)
                .collect(Collectors.toList()));

        return response;
    }
}
