package com.example.newsService.mapper;

import com.example.newsService.model.Comment;
import com.example.newsService.model.News;
import com.example.newsService.model.User;
import com.example.newsService.web.model.comment.CommentListResponse;
import com.example.newsService.web.model.comment.CommentResponse;
import com.example.newsService.web.model.comment.UpsertCommentRequest;
import com.example.newsService.web.model.news.NewsResponse;
import com.example.newsService.web.model.news.NewsResponseForFindById;
import com.example.newsService.web.model.news.NewsWithComments;
import com.example.newsService.web.model.user.UpsertUserRequest;
import com.example.newsService.web.model.user.UserListResponse;
import com.example.newsService.web.model.user.UserResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@DecoratedWith(CommentMapperDelegate.class)
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {NewsMapper.class})
public interface CommentMapper {
    Comment requestToComment(UpsertCommentRequest request);
    @Mapping(source = "commentId", target = "id")
    Comment requestToComment(Long commentId, UpsertCommentRequest request);


    CommentResponse commentToResponse(Comment comment);

    default CommentListResponse commentListToResponseList(List<Comment> comments) {
        return new CommentListResponse();
    }

   //CommentListResponse commentListToResponseList(List<Comment> comments);
}
