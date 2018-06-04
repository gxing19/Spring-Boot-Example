package com.springboot.example.cache;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/cache/category")
public class CategoryController {
    private final static Logger logger = LogManager.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/queryById")
    public Category queryById(Long CategoryId){
       Category category = categoryService.queryById(CategoryId);
       logger.info(JSON.toJSONString(category));
       return category;
    }

    @RequestMapping("/queryByCategoryId")
    public Category queryByCategoryId(Category category){
        category = categoryService.queryByCategoryId(category);
        logger.info(JSON.toJSONString(category));
        return category;
    }

    @RequestMapping("/saveCategory")
    public Category saveCategory(){
        Category category = new Category().setCategoryId(18L).setLastUpdate(new Date()).setName("Hello Kitty");
        category = categoryService.saveCategory(category);
        logger.info(JSON.toJSONString(category));
        return category;
    }

    @RequestMapping("/deleteById")
    public void deleteById(Long categoryId){
        categoryService.deleteById(categoryId);
    }

    @RequestMapping("/queryByName")
    public Category queryByName(Category category){
        category = categoryService.queryByName(category);
        logger.info(JSON.toJSONString(category));
        return category;
    }

    @RequestMapping("/queryByCategoryName")
    public Category queryByCategoryName(Category category){
        category = categoryService.queryByCategoryName(category);
        logger.info(JSON.toJSONString(category));
        return category;
    }

}
