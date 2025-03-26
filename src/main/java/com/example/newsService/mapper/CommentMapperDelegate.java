package com.example.newsService.mapper;

import com.example.newsService.model.Comment;
import com.example.newsService.model.News;
import com.example.newsService.service.NewsService;
import com.example.newsService.service.UserService;
import com.example.newsService.web.model.comment.CommentListResponse;
import com.example.newsService.web.model.comment.CommentResponse;
import com.example.newsService.web.model.comment.UpsertCommentRequest;
import com.example.newsService.web.model.news.NewsResponse;
import com.example.newsService.web.model.news.NewsResponseForFindById;
import com.example.newsService.web.model.news.NewsWithComments;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class CommentMapperDelegate implements CommentMapper {
    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private NewsService newsService;
    

    @Override
    public Comment requestToComment(UpsertCommentRequest request) {
        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setNews(newsService.findById(request.getNewsId()));
        comment.setUser(userService.findById(request.getUserId()));

        return comment;
    }

    @Override
    public Comment requestToComment(Long commentId, UpsertCommentRequest request) {
        Comment comment = requestToComment(request);
        comment.setId(commentId);
        return comment;
    }

    @Override
    public CommentListResponse commentListToResponseList(List<Comment> comments) {
        Map<News, List<Comment>> news2comment = comments.stream().collect(Collectors.groupingBy(Comment::getNews));

        List<NewsWithComments> newsWithCommentsList = news2comment
                .entrySet()
                .stream()
                .map(entry -> {
                    News news = entry.getKey();
                    List<CommentResponse> commentResponses = entry.getValue().stream()
                            .map(this::commentToResponse)
                            .collect(Collectors.toList());
                    return new NewsWithComments(news.getId(), news.getContent(), commentResponses);
                }).collect(Collectors.toList());
        return new CommentListResponse(newsWithCommentsList);
    }



//    @Override
//    public List<NewsResponseForFindById> commentListToResponseList(List<Comment> comments) {
//
//        List<NewsResponseForFindById> list = new ArrayList<>();
//
//        List<Long> newsId = new ArrayList<>();
//
//        for(Comment comment : comments) {
//            News news = comment.getNews();
//
//            if(newsId.contains(news.getId())) {
//                continue;
//            }
//            list.add(newsMapper.newsToResponseForFindById(news));
//
//        }
//
//        return list;
//    }

    //    @Override
//    public CommentListResponse commentListToResponseList(List<Comment> comments) {
//
//        Map<NewsResponse, List<CommentResponse>> news2comment = comments.stream()
//                .collect(Collectors.groupingBy(
//                        comment -> newsMapper.newsToResponse(comment.getNews()),
//                        Collectors.mapping(this::commentToResponse, Collectors.toList())
//
//                ));
//
//        CommentListResponse commentListResponse = new CommentListResponse();
//        commentListResponse.setNews2comment(news2comment);
//
//        return commentListResponse;
    }

