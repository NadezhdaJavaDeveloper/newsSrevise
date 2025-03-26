package com.example.newsService.mapper;

import com.example.newsService.model.Category;
import com.example.newsService.model.Comment;
import com.example.newsService.model.News;
import com.example.newsService.model.User;
import com.example.newsService.service.CategoryService;
import com.example.newsService.service.UserService;
import com.example.newsService.web.model.comment.CommentResponse;
import com.example.newsService.web.model.news.NewsResponse;
import com.example.newsService.web.model.news.NewsResponseForFindById;
import com.example.newsService.web.model.news.UpsertNewsRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class NewsMapperDelegate implements NewsMapper {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;


    @Override
    public News requestToNews(UpsertNewsRequest request) {
        News news = new News();
        Category category = categoryService.getByCategoryName(request.getCategoryName());
        news.setCategory(category);
        news.setContent(request.getContent());
        User user = userService.findById(request.getUserId());
        news.setUser(user);
        return news;
    }

    @Override
    public News requestToNews(Long newsId, UpsertNewsRequest request) {
        News news = requestToNews(request);
        news.setId(newsId);
        return news;
    }


    @Override
    public NewsResponse newsToResponse(News news) {
        NewsResponse newsResponse = new NewsResponse();

        newsResponse.setId(news.getId());
        newsResponse.setCategoryName(news.getCategory().getCategoryName());
        newsResponse.setContent(news.getContent());
        newsResponse.setNumberOfComments(news.getCommentList().size());
        newsResponse.setAuthorOfNews(news.getUser().getNickname());

        return newsResponse;
    }

    @Override
    public NewsResponseForFindById newsToResponseForFindById(News news) {

        NewsResponseForFindById newsResponse = new NewsResponseForFindById();

        newsResponse.setId(news.getId());
        newsResponse.setCategoryName(news.getCategory().getCategoryName());
        newsResponse.setContent(news.getContent());
        newsResponse.setAuthorOfNews(news.getUser().getNickname());

        List<CommentResponse>  commentResponseList = new ArrayList<>();
        for(Comment comment : news.getCommentList()) {
            commentResponseList.add(new CommentResponse(comment.getId(), comment.getContent()));
        }
        newsResponse.setCommentList(commentResponseList);

        return newsResponse;
    }

}
