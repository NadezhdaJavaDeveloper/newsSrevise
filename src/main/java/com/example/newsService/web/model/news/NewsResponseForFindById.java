package com.example.newsService.web.model.news;

import com.example.newsService.model.Comment;
import com.example.newsService.web.model.comment.CommentResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponseForFindById {

    private Long id;
    private String authorOfNews;
    private String categoryName;
    private String content;
    private List<CommentResponse> commentList = new ArrayList<>();


}
