package com.example.newsService.web.model.news;

import com.example.newsService.validation.RequestFilterValid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequestFilterValid
public class NewsFilter {

    private Integer pageSize;
    private Integer pageNumber;
    private String categoryName;
    private String userNickname;
}
