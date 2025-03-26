package com.example.newsService.web.model.news;

import com.example.newsService.web.model.comment.CommentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsWithComments {

    private Long newsId;
    private String newsContent;
    private List<CommentResponse> commentList;

}
