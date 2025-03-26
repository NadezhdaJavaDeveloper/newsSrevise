package com.example.newsService.web.controller;

import com.example.newsService.mapper.CommentMapper;
import com.example.newsService.model.Comment;
import com.example.newsService.model.News;
import com.example.newsService.service.CommentService;
import com.example.newsService.web.model.RequestFilter;
import com.example.newsService.web.model.comment.CommentListResponse;
import com.example.newsService.web.model.comment.CommentResponse;
import com.example.newsService.web.model.comment.UpsertCommentRequest;
import com.example.newsService.web.model.news.NewsListResponse;
import com.example.newsService.web.model.news.NewsResponse;
import com.example.newsService.web.model.news.NewsResponseForFindById;
import com.example.newsService.web.model.news.UpsertNewsRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;



    @GetMapping
    public ResponseEntity<CommentListResponse> findAll() {
        return ResponseEntity.ok(
                commentMapper.commentListToResponseList(
                        commentService.findAll())
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                commentMapper.commentToResponse(
                        commentService.findById(id)
                )
        );
    }

    @PostMapping
    public ResponseEntity<CommentResponse> create(@RequestBody @Valid UpsertCommentRequest request) {
        Comment comment = commentService.save(commentMapper.requestToComment(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentMapper.commentToResponse(comment));
    }

    @PutMapping("/{id}")

    public ResponseEntity<CommentResponse> update(@PathVariable Long id,
                                                  @RequestBody @Valid UpsertCommentRequest request) {
        Comment updateComment = commentService.updateComment(
                commentMapper.requestToComment(id, request), request.getUserId());

        return ResponseEntity.ok(commentMapper.commentToResponse(updateComment));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestParam Long userId) {
        commentService.deleteCommentById(id, userId);
        return ResponseEntity.noContent().build();
    }

}
