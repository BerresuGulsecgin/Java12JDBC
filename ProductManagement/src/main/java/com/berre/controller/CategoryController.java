package com.berre.controller;

import com.berre.entity.Category;
import com.berre.service.CategoryService;
import com.berre.util.BAUtils;

import java.util.Optional;

public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController() {
        this.categoryService=new CategoryService();
    }

    public void save() {
        String categoryName= BAUtils.readString("Category name");
        Category category=Category.builder()
                .name(categoryName)
                .build();
        categoryService.save(category);
    }

    public Optional<Category> findById(Long categoryId) {
        return categoryService.findById(categoryId);

    }
}
