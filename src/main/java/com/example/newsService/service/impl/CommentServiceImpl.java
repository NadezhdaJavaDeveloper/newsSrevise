package com.example.newsService.service.impl;

import com.example.newsService.aop.Approved;
import com.example.newsService.exception.EntityNotFoundException;
import com.example.newsService.model.Comment;
import com.example.newsService.model.News;
import com.example.newsService.model.User;
import com.example.newsService.repository.CommentRepository;
import com.example.newsService.service.CommentService;
import com.example.newsService.service.annotationForParametrs.EntityId;
import com.example.newsService.service.annotationForParametrs.UserId;
import com.example.newsService.utils.BeanUtils;
import com.example.newsService.web.model.RequestFilter;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final UserServiceImpl userService;
    private final NewsServiceImpl newsService;
    private final CommentRepository commentRepository;

    @Override

    public List<Comment> findAll() {

        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                        "Комментарий с ID {0} не найден", id
                )));
    }

    @Override
    @Transactional
    public Comment save(Comment comment) {

        return commentRepository.save(comment);
    }

    @Override
    @Approved
    @Transactional
    public Comment updateComment(Comment comment, @UserId Long userId) {

        Comment existedComment = findById(comment.getId());

        if(!existedComment.getNews().getId().equals(comment.getNews().getId())) {
            throw new ValidationException("Комментарий не может изменить привязку к новости");
        }

        existedComment.setContent(comment.getContent());

        return commentRepository.save(existedComment);
    }

    @Override
    @Approved
    public void deleteCommentById(@EntityId Long id, @UserId Long userId) {
        Comment existedComment = findById(id);
        News news = existedComment.getNews();
        news.removeComment(existedComment);
        commentRepository.deleteById(id);
    }
}

