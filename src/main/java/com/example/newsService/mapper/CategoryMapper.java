package com.example.newsService.mapper;

import com.example.newsService.model.Category;
import com.example.newsService.model.User;
import com.example.newsService.web.model.category.CategoryListResponse;
import com.example.newsService.web.model.category.CategoryResponse;
import com.example.newsService.web.model.category.UpsertCategoryRequest;

import com.example.newsService.web.model.user.UserListResponse;
import com.example.newsService.web.model.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {NewsMapper.class})
public interface CategoryMapper {

    Category requestToCategory(UpsertCategoryRequest request);

    @Mapping(source = "categoryId", target = "id")
    Category requestToCategory(Long categoryId, UpsertCategoryRequest request);

    CategoryResponse categoryToResponse(Category category);

    default CategoryListResponse categoryListToResponseList(List<Category> categories) {
        CategoryListResponse response = new CategoryListResponse();

        response.setCategories(categories.stream()
                .map(this::categoryToResponse)
                .collect(Collectors.toList()));

        return response;
    }
}
