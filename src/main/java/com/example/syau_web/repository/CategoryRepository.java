package com.example.syau_web.repository;

import com.example.syau_web.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * create by wangyu
 * 2018/10/21 0021 16:44
 *
 *todo 待扩展
 */
public interface CategoryRepository extends JpaRepository<Category,String> {

    /**
     *                  根据产品的类型list查询所有的产品列表
     * @param catoryList
     *                  产品类型的list集合
     * @return
     *                  产品的列表
     */
    public List<Category> findByCategoryTypeIn(List<Integer> catoryList);


    public Category findByCategoryName(String categoryName);

}
