package com.example.newsService.service;

import com.example.newsService.model.Comment;
import com.example.newsService.model.News;
import com.example.newsService.web.model.RequestFilter;

import java.util.List;

public interface CommentService {

    List<Comment> findAll();

    Comment findById(Long id);

    Comment save(Comment comment);

    Comment  updateComment(Comment comment, Long userId);

    void deleteCommentById(Long id, Long userId);
}
