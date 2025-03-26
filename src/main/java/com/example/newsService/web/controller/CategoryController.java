package com.example.newsService.web.controller;

import com.example.newsService.mapper.CategoryMapper;
import com.example.newsService.model.Category;
import com.example.newsService.service.CategoryService;
import com.example.newsService.web.model.RequestFilter;
import com.example.newsService.web.model.category.CategoryListResponse;
import com.example.newsService.web.model.category.CategoryResponse;
import com.example.newsService.web.model.category.UpsertCategoryRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<CategoryListResponse> findAll(@Valid RequestFilter requestFilter) {
        return ResponseEntity.ok(
                categoryMapper.categoryListToResponseList(
                        categoryService.findAll(requestFilter)
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                categoryMapper.categoryToResponse(
                        categoryService.findById(id)
                )
        );
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody @Valid UpsertCategoryRequest request) {
        Category newCategory = categoryService.save(categoryMapper.requestToCategory(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryMapper.categoryToResponse(newCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id,
                                                   @RequestBody @Valid UpsertCategoryRequest request) {
        Category updateCategory = categoryService.update(categoryMapper.requestToCategory(id, request));
        return ResponseEntity.ok(
                categoryMapper.categoryToResponse(updateCategory)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

