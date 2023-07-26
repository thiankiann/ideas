package com.mariusz.ideas.category.service;

import com.mariusz.ideas.category.domain.model.Category;
import com.mariusz.ideas.category.domain.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Transactional(readOnly = true)
    public Page<Category> getCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }
    @Transactional(readOnly = true)
    public Category getCategory(UUID id) {
        return categoryRepository.getById(id);
    }
    @Transactional
    public Category createCategory(Category categoryRequest) {
        Category category = new Category();

        category.setName(categoryRequest.getName() );

        return categoryRepository.save(category);
    }
    @Transactional
    public Category updateCategory(UUID id, Category categoryRequest) {
        Category category = categoryRepository.getById(id);

        category.setName(categoryRequest.getName() );

        return categoryRepository.save(category);
    }
    @Transactional
    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
    }
}
