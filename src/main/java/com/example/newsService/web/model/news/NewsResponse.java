package com.example.newsService.web.model.news;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponse {

    private Long id;
    private String authorOfNews;
    private String categoryName;
    private String content;
    private int numberOfComments;

}
