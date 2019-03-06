package com.example.syau_web.service;

import com.example.syau_web.domain.Category;

import java.util.List;

/**
 * create by wangyu
 * 2018/10/21 0021 17:46
 */
public interface CategoryService {

    /**
     * 根据商品的类型查找商品
     *
     * @param categoryList
     * @return
     */
    public List<Category> findByCategoryTypeIn(List<Integer> categoryList);

    /**
     * 根据id查找一件商品
     *
     * @param id
     * @return 要查找的商品
     */
    public Category findOneByCategoryId(String id);

    /**
     * 根据商品的名字查找商品
     *
     * @param categoryName 商品的名字
     * @return 商品列表
     */
    public Category findByCategoryName(String categoryName);

    /**
     * 更新或新增
     * @param category
     * @return
     */
    Category save(Category category);

    /**
     * 查询所有的类目
     * @return
     */
    List<Category> findAll();
}
