package com.example.syau_web.repository;

import com.example.syau_web.domain.Category;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * create by wangyu
 * 2018/10/21 0021 16:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findByCategoryType() {
        List<Category> categoryList = categoryRepository.findByCategoryTypeIn(Arrays.asList(5, 10));

        categoryList.stream().forEach(category -> System.out.println(category.getCategoryName()));
//        Assert.assertEquals("",categoryRepository);
        Assert.assertNotNull(categoryList);
    }
}