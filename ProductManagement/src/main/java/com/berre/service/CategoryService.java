package com.berre.service;

import com.berre.entity.Category;
import com.berre.repository.CategoryRepository;

import java.util.Optional;

public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService() {
        this.categoryRepository=new CategoryRepository();
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public Optional<Category> findById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }
}
