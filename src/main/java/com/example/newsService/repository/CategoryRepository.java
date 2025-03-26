package com.example.newsService.repository;

import com.example.newsService.model.Category;
import com.example.newsService.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findAll (Pageable pageable);

    Category getByCategoryName (String categoryName);
}
