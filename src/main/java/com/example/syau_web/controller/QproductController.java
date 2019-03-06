package com.example.syau_web.controller;

import com.example.syau_web.domain.ProductInfo;
import com.example.syau_web.service.Impl.ProductInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.awt.print.Pageable;
import java.util.Map;

/**
 * create by wangyu
 * 2018/11/3 0003 20:00
 *
 * 前台商品的展示
 */
@Controller
public class QproductController {

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    /**
     * 商品列表的前台展示及分页
     * @param Page 第几页
     * @param Size 每页的条数
     * @param map  无
     * @return
     */
   /* @RequestMapping("/productList.action")
    public ModelAndView ProductList(@RequestParam(value = "page",defaultValue = "0") Integer Page,
                                @RequestParam(value = "size",defaultValue = "4") Integer Size,
                                Map<String,Object> map){
//        String webApp = request.getServletContext().getRealPath("webApp");
//        System.out.println(webApp);
        PageRequest pageable = new PageRequest(Page,Size);

        Page<ProductInfo> page = productInfoService.findAll(pageable);
        ModelAndView view = new ModelAndView();
        view.setViewName("product_list.jsp");
        view.addObject("page",page);
        view.addObject("currentPage",(Page+1));
        return view;
    }*/

    /**
     * 商品详情的展示
     * @param productId
     * @return
     */
    @RequestMapping("/productInfo.action")
    public ModelAndView ProductInfo(@RequestParam(value = "productId") String productId){
        ModelAndView view = new ModelAndView();
        ProductInfo productInfo = productInfoService.finOneById(productId);
        view.addObject("b",productInfo);
        view.setViewName("product_info.jsp");
        return view;
    }

    @RequestMapping("/productList.action")
    public ModelAndView ProductList(@RequestParam(value = "page",defaultValue = "0") Integer Page,
                                    @RequestParam(value = "size",defaultValue = "4") Integer Size,
                                    Map<String,Object> map){
//        String webApp = request.getServletContext().getRealPath("webApp");
//        System.out.println(webApp);
        PageRequest pageable = new PageRequest(Page,Size);

        Page<ProductInfo> page = productInfoService.findAll(pageable);
        ModelAndView view = new ModelAndView();
        view.setViewName("mypage/student.jsp");
        view.addObject("page",page);
        view.addObject("currentPage",(Page+1));
        return view;
    }
}
