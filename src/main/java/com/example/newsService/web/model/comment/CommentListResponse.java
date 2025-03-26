package com.example.newsService.web.model.comment;

import com.example.newsService.model.Comment;
import com.example.newsService.model.News;
import com.example.newsService.web.model.category.CategoryResponse;
import com.example.newsService.web.model.news.NewsResponse;
import com.example.newsService.web.model.news.NewsWithComments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentListResponse {

    private List<NewsWithComments> newsWithCommentsList;
}
