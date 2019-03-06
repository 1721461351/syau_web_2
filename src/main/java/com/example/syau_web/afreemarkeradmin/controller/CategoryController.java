package com.example.syau_web.afreemarkeradmin.controller;

import com.example.syau_web.domain.Category;
import com.example.syau_web.service.Impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * create by wangyu
 * 2018/11/10 0010 14:54
 */
@Controller
@RequestMapping("ftl/admin/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    /**
     * 类目列表
     * @param map
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<Category> categoryList = categoryService.findAll();

        map.put("category",categoryList);
        return new ModelAndView("category/list",map);
    }
}
