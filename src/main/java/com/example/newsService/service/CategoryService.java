package com.example.newsService.service;

import com.example.newsService.model.Category;
import com.example.newsService.model.Comment;
import com.example.newsService.web.model.RequestFilter;

import java.util.List;

public interface CategoryService {

    List<Category> findAll(RequestFilter filter);

    Category findById(Long id);

    Category save(Category category);

    Category update(Category category);

    Category getByCategoryName(String categoryName);

    void deleteById(Long id);
}
