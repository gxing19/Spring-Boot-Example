package com.springboot.example.cache;

public interface CategoryService {
    Category queryById(Long categoryId);

    Category queryByCategoryId(Category category);

    Category queryByName(Category category);

    Category queryByCategoryName(Category category);

    Category saveCategory(Category category);

    void deleteById(Long categoryId);
}
