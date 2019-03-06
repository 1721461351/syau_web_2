package com.example.syau_web.afreemarkeradmin.controller;

import com.example.syau_web.DTO.UserDTO;
import com.example.syau_web.domain.Category;
import com.example.syau_web.domain.ProductInfo;
import com.example.syau_web.domain.User;
import com.example.syau_web.enums.MyExceptionEnum;
import com.example.syau_web.enums.ResultEnum;
import com.example.syau_web.exception.MyException;
import com.example.syau_web.form.ProductForm;
import com.example.syau_web.service.Impl.CategoryServiceImpl;
import com.example.syau_web.service.Impl.ProductInfoServiceImpl;
import com.example.syau_web.service.ProductInfoService;
import com.example.syau_web.util.RandomUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * create by wangyu
 * 2018/11/7 0007 18:47
 */
@Controller
@RequestMapping("/ftl/admin/")
public class FtlProductController {
    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Autowired
    private CategoryServiceImpl categoryService;

    /**
     * ---------------------------展示商品信息，实现分页------------------------------------
     */
    @RequestMapping("list.action")
    public ModelAndView productList(ModelAndView modelAndView, HttpServletRequest request,
                                    Map<String,Object> map,
                                    @RequestParam(value = "currentpage", defaultValue = "1") Integer page,
                                    @RequestParam(value = "size", defaultValue = "4") Integer size) {

        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
//            map.put("url","mypage/login1.jsp");
            map.put("url","mypage/login1.jsp");
            map.put("msg", ResultEnum.NOT_LOGIN.getMessage());
            return new ModelAndView("common/error",map);
        }
        UserDTO userDTO = new UserDTO();
//        属性拷贝功能
        BeanUtils.copyProperties(user, userDTO);

        String pId = request.getParameter("pId");
        String pName = request.getParameter("pName");
//        System.out.println(user.getUsername());
        /**
         * 不清楚为什么点击查看商品列表，查出的pId和pName为空
         * 如果为空那么查出的结果就为0条，就没有商品信息
         * 需要把商品为空改为商品为  ""
         */
        if (pId == null && pName == null) {
            pId = "";
            pName = "";
        }

        PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<ProductInfo> productInfos = productInfoService.findAll(pageRequest);
//        Page<ProductInfo> productInfoPage = productInfoService.findAll(pageRequest);
        /**---------------------根据学号查询商品列表--------------------------*/
//        List<ProductInfo> productInfoPage = productInfoService.findAllLike(pId, pName,user.getNumber());

//        List<ProductInfo> content = productInfoPage.getContent();
//        modelAndView.addObject("content",content);
        ModelAndView view = modelAndView.addObject("productInfo", productInfos);
        view.addObject("currentPage", page);
        userDTO.getProductInfoList();
        view.setViewName("product/productList");
        return view;
    }

    /**
     * 商品的上架
     *
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("productup")
    public ModelAndView productUp(@RequestParam("productId") String productId,
                                  Map<String, Object> map) {
        try {
            ProductInfo productInfo = productInfoService.productUp(productId);
        } catch (MyException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/ftl/admin/list.action");
            /**---------------这个异常就不用抛出去了，因为跳转界面了-------------------*/
//           throw new MyException(MyExceptionEnum.PRODUCT_UP_ERROR);
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/ftl/admin/list.action");
        return new ModelAndView("common/success", map);
    }

    /**
     * 商品的下架
     *
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("productoff")
    public ModelAndView productOff(@RequestParam("productId") String productId,
                                   Map<String, Object> map) {
        try {
            productInfoService.productOff(productId);
        } catch (MyException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/ftl/admin/list.action");
            /**---------------这个异常就不用抛出去了，因为跳转界面了-------------------*/
//           throw new MyException(MyExceptionEnum.PRODUCT_UP_ERROR);
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/ftl/admin/list.action");
        return new ModelAndView("common/success", map);
    }

    /**
     * 根据商品的id删除商品
     *
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("deleteProduct")
    public ModelAndView deleteProduct(@RequestParam("productId") String productId,
                                      Map<String, Object> map) {
//        ProductInfo productInfo=null;
        try {
            productInfoService.deleteById(productId);
//             productInfo = productInfoService.finOneById(productId);
        } catch (MyException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/ftl/admin/list.action");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.DELETE_PRODUCT_SUCCESS.getMessage());
        map.put("url", "/ftl/admin/list.action");
        return new ModelAndView("common/success", map);
    }

    /**
     * 新增
     *
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("index")
    public ModelAndView indexProduct(@RequestParam(value = "productId", required = false) String productId,
                                     Map<String, Object> map) {
        ProductInfo productInfo = null;
        if (productId != null) {
            productInfo = productInfoService.finOneById(productId);
            map.put("productInfo", productInfo);
        }

        //查询类目
        List<Category> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
//        map.put("url","/ftl/admin/list.action");
        return new ModelAndView("product/index", map);
    }

    /**
     * 新增商品的保存
     *
     * @return
     */
    @PostMapping("save")
    public ModelAndView saveProduct(@Valid ProductForm productForm,
                                    BindingResult bindingResult,
                                    Map<String, Object> map) {
        ProductInfo productInfo = new ProductInfo();
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "ftl/admin/index");
            return new ModelAndView("common/error", map);
        }
        try {
            //判断是新增还是修改
            /**-----------这里利用了spring的工具类---------*/
            if (!StringUtils.isEmpty(productForm.getProductId())){//说明是修改
                //根据id查询
                productInfo = productInfoService.finOneById(productForm.getProductId());
            }else {//说明是新增
                productForm.setProductId(RandomUtil.getUniqueKey());
            }
            //属性拷贝
            BeanUtils.copyProperties(productForm, productInfo);
            //保存
            productInfoService.save(productInfo);
        } catch (MyException e) {
            map.put("msg", e.getMessage());
            map.put("url", "ftl/admin/index");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.SAVE_SUCCESS.getMessage());
        map.put("url", "/ftl/admin/list.action");
        return new ModelAndView("common/success", map);
    }
}
