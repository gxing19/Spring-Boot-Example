package com.springboot.cache.service;

import com.springboot.cache.entity.Category;

public interface CategoryService {
    Category queryById(Long categoryId);

    Category queryByCategoryId(Category category);

    Category queryByName(Category category);

    Category queryByCategoryName(Category category);

    Category saveCategory(Category category);

    void deleteById(Long categoryId);
}
