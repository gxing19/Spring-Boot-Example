package com.springboot.example.cache;

import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.cache.annotation.CacheEvict;
        import org.springframework.cache.annotation.CachePut;
        import org.springframework.cache.annotation.Cacheable;
        import org.springframework.data.domain.Example;
        import org.springframework.data.domain.ExampleMatcher;
        import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Cacheable 先从缓存中查找，没有再找数据库
     * @param categoryId
     * @return
     */
    @Override
    @Cacheable(key = "#categoryId", value = "category")
    public Category queryById(Long categoryId) {
        return categoryRepository.findById(categoryId).get();
    }

    /**
     * 先查缓存，再查数据库
     * @param category
     * @return
     */
    @Override
    @Cacheable(key = "#category.CategoryId", value = "category")
    public Category queryByCategoryId(Category category) {
        return categoryRepository.findById(category.getCategoryId()).get();
    }

    /**
     * 保存数据并存入缓存
     * @param category
     * @return
     */
    @Override
    @Cacheable(key = "#category.CategoryId", value = "category")
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }


    /**
     * 删除数据同时删除缓存
     * @param categoryId
     */
    @Override
    @CacheEvict(value = "category")
    public void deleteById(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    /**
     * 查数据并存缓存
     * @param category
     * @return
     */
    @Override
    @CachePut(key = "#category.name", value = "category")
    public Category queryByName(Category category) {
        Example<Category> example = new Example<Category>(){

            @Override
            public Category getProbe() {
                return category;
            }

            @Override
            public ExampleMatcher getMatcher() {
                ExampleMatcher matcher = ExampleMatcher.matchingAny();
                return matcher;
            }
        };

        return categoryRepository.findOne(example).get();
    }

    /**
     * 先从缓存中查，没有再从库里查
     * @param category
     * @return
     */
    @Override
    @Cacheable(key = "#category.name", value = "category")
    public Category queryByCategoryName(Category category) {
        Example<Category> example = new Example<Category>(){

            @Override
            public Category getProbe() {
                return category;
            }

            @Override
            public ExampleMatcher getMatcher() {
                ExampleMatcher matcher = ExampleMatcher.matchingAny();
                return matcher;
            }
        };
        return categoryRepository.findOne(example).get();
    }
}
