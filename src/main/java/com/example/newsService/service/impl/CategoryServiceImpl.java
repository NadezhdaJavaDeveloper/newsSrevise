package com.example.newsService.service.impl;

import com.example.newsService.exception.EntityNotFoundException;
import com.example.newsService.model.Category;
import com.example.newsService.repository.CategoryRepository;
import com.example.newsService.service.CategoryService;
import com.example.newsService.utils.BeanUtils;
import com.example.newsService.web.model.RequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll(RequestFilter filter) {
        return categoryRepository.findAll(PageRequest.of(
                filter.getPageNumber(), filter.getPageSize()
        )).getContent();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                        "Категория с ID {0} не найден", id
                )));
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        Category existedCategory = findById(category.getId());
        BeanUtils.copyNonNullProperties(category, existedCategory);
        return categoryRepository.save(existedCategory);
    }

    @Override
    public Category getByCategoryName(String categoryName) {
        return categoryRepository.getByCategoryName(categoryName);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
