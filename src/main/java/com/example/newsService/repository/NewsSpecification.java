package com.example.newsService.repository;

import com.example.newsService.model.Category;
import com.example.newsService.model.News;
import com.example.newsService.web.model.news.NewsFilter;
import org.springframework.data.jpa.domain.Specification;

public interface NewsSpecification {

    static Specification<News> withFilter(NewsFilter newsFilter) {
        return Specification.where(byCategory(newsFilter.getCategoryName()))
                .and(byAuthor(newsFilter.getUserNickname()));
    }

    static Specification<News> byAuthor(String userNickname) {
        return (root, query, criteriaBuilder) -> {
            if(userNickname == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("user").get("nickname"), userNickname);
        };
    }

    static Specification<News> byCategory(String categoryName) {
        return (root, query, criteriaBuilder) -> {
            if(categoryName == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("category").get("categoryName"), categoryName);
        };
    }
}
