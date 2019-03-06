package com.example.syau_web.service.Impl;

import com.example.syau_web.domain.Category;
import com.example.syau_web.repository.CategoryRepository;
import com.example.syau_web.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * create by wangyu
 * 2018/10/21 0021 17:48
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> findByCategoryTypeIn(List<Integer> categoryList) {
        return categoryRepository.findByCategoryTypeIn(categoryList);
    }

    @Override
    public Category findOneByCategoryId(String id) {
        Category category = categoryRepository.findById(id).get();
        return category;
    }

    @Override
    public Category findByCategoryName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    @Override
    @Transactional
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
