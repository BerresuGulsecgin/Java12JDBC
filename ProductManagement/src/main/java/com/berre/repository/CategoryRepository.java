package com.berre.repository;

import com.berre.entity.Category;
import com.berre.util.MyRepositoryFactory;

public class CategoryRepository extends MyRepositoryFactory<Category, Long> {
    public CategoryRepository() {
        super(Category.class);
    }
}
