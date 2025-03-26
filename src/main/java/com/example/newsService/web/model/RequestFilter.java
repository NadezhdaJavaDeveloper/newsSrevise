package com.example.newsService.web.model;

import com.example.newsService.validation.RequestFilterValid;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@RequestFilterValid
public class RequestFilter {

    private Integer pageSize;
    private Integer pageNumber;
}
