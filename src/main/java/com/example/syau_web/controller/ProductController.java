package com.example.syau_web.controller;

import com.example.syau_web.DTO.ProductInfoDTO;
import com.example.syau_web.DTO.UserDTO;
import com.example.syau_web.domain.Category;
import com.example.syau_web.domain.ProductInfo;
import com.example.syau_web.domain.User;
import com.example.syau_web.enums.MyExceptionEnum;
import com.example.syau_web.exception.MyException;
import com.example.syau_web.service.Impl.CategoryServiceImpl;
import com.example.syau_web.service.Impl.ProductInfoServiceImpl;
import com.example.syau_web.util.MultipartUtil;
import com.example.syau_web.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * create by wangyu
 * 2018/10/22 0022 9:49
 * <p>
 * 商品信息
 */
@Controller
public class ProductController {
    private final static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductInfoServiceImpl productInfoService;
    /**
     * todo 商品类别待添加
     */
    @Autowired
    private CategoryServiceImpl categoryService;

    /**
     * ---------------------------展示商品信息，能够按条件模糊查询------------------------------------
     */
    @RequestMapping("admin/list.action")
    public ModelAndView productList(ModelAndView modelAndView, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        UserDTO userDTO = new UserDTO();
//        属性拷贝
        BeanUtils.copyProperties(user,userDTO);

        String pId = request.getParameter("pId");
        String pName = request.getParameter("pName");
//        User user = (User) session.getAttribute("user");
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
        PageRequest pageRequest = new PageRequest(0, 10);
//        Page<ProductInfo> productInfoPage = productInfoService.findAll(pageRequest);
        /**---------------------根据学号查询商品列表--------------------------*/
        List<ProductInfo> productInfoPage = productInfoService.findAllLike(pId, pName,user.getNumber());

        userDTO.setProductInfoList(productInfoPage);
//        List<ProductInfo> content = productInfoPage.getContent();
//        modelAndView.addObject("content",content);
        ModelAndView view = modelAndView.addObject("productInfo", userDTO);
        view.setViewName("products/list.jsp");
        return view;
    }

    /**
     * 添加商品的信息，
     * 这里涉及到的主要问题是
     * 文件该如何上传
     * 需要加上BindingResult todo BindingResult是什么，怎么用
     *
     * @param productInfoDTO 前端的name，对应里面的属性
     * @param bindingResult
     * @return
     */
    @RequestMapping("/admin/addproduct.action")
    public String addProduct(ProductInfoDTO productInfoDTO, BindingResult bindingResult,HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
//        得到文件
        MultipartFile file = productInfoDTO.getProductImg();
        /**-----------------这里用到了自定义的文件上传工具类-------------------*/
        /**-----------------这里的map都是自己定义的---------------------------*/
        Map<String, Object> map = MultipartUtil.upload(file);
        String filename = (String) map.get("文件名");
        String suffixName = (String) map.get("文件后缀名");
        String uniqueKey = (String) map.get("新名字");

        /**------------------把下面的代码封装到了工具类里面---------------------*/
//        判断文件是否为空
//        if (file.isEmpty()){
//            logger.info("文件为空");
//            return "上传失败，文件不能为空";
//        }

//        获取文件名
       /* String filename = file.getOriginalFilename();

//        设置随机数(当成新的文件的名字)
        String uniqueKey = RandomUtil.getUniqueKey();

//        获取文件的后缀名
        String suffixName  = filename.substring(filename.lastIndexOf("."));

//        设置文件的存储路径
        String filePath = "D://SYAUWEB//";
        String path = filePath + uniqueKey  +suffixName;

        File dest = new File(path);

//        判断文件夹是否存在
        if (!dest.exists()){//如果不存在
            dest.getParentFile().mkdirs();//创建文件夹
        }

        try {
            *//**
         * 写入文件
         *//*
            file.transferTo(dest);
        } catch (IOException e) {
//            抛出自定义异常
            throw new MyException(MyExceptionEnum.IOEXCEPTION);
        }*/
        ProductInfo productInfo = new ProductInfo();
//        数据转换
        BeanUtils.copyProperties(productInfoDTO, productInfo);
//        产生随机id
        productInfo.setProductId(RandomUtil.getUniqueKey());
//        文件名存入
        productInfo.setProductImg(uniqueKey + suffixName);
//        设置学号
        User user = (User) request.getSession().getAttribute("user");
        productInfo.setUserNumber(user.getNumber());

//        添加
        productInfoService.addProduct(productInfo);

//        页面跳转
//        view.setViewName("admin/list.action");

//        重定向到展示商品的列表
        return "redirect:/admin/list.action";
    }


    /**
     * 注意这里的参数传递问题 和界面写参数的问题
     * todo  get传递参数和post传递参数有什么不同吗
     *
     * @param productId 商品id
     * @return 删除后的列表展示
     */
    @RequestMapping(value = "/admin/delete.action/{productId}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable String productId) {

        logger.info(productId);
        boolean b = productInfoService.deleteById(productId);
        if (b == true) {
            return "redirect:/admin/list.action";
        } else {
            return "删除失败";
        }

    }

    /***
     *   完成数据回显功能
     * @param productId 商品id
     * @return view
     */
    @RequestMapping(value = "/admin/edit.action/{productId}", method = RequestMethod.GET)
    public ModelAndView editProduct(@PathVariable String productId) {

        ModelAndView view = new ModelAndView();
//        获得的商品信息转化到productname
        ProductInfo productInfo = productInfoService.finOneById(productId);
//        把该商品存入到session中
        view.addObject("product", productInfo);
//        设置跳转的路径
        view.setViewName("/admin/products/edit.jsp");
//        返回view到界面
        return view;
    }

    /**
     * 修改商品操作
     *
     * @param productId      商品的id
     * @param productInfoDTO jsp对应的name
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/admin/save.action/{productId}", method = RequestMethod.POST)
    public String saveProduct(@PathVariable String productId, ProductInfoDTO productInfoDTO, BindingResult bindingResult) {
//        ModelAndView view = new ModelAndView();
//        得到文件
        MultipartFile file = productInfoDTO.getProductImg();

//        这里得到的名字是jsp界面里name属性里的名字
        String name = file.getName();
        String originalFilename = file.getOriginalFilename();
        logger.info("更改的文件名字为：{}", name);

//        这里利用的是自定义的文件上传工具类
        Map<String, Object> map = MultipartUtil.upload(file);
        String filename = (String) map.get("文件名");
        String suffixName = (String) map.get("文件后缀名");
        String uniqueKey = (String) map.get("新名字");

        ProductInfo productInfo = new ProductInfo();
//                数据转换，利用了属性拷贝的功能
        BeanUtils.copyProperties(productInfoDTO, productInfo);
//        产生随机id
        productInfo.setProductId(productInfoDTO.getProductId());
//        文件名存入
        productInfo.setProductImg(uniqueKey + suffixName);


//        更新
        productInfoService.addProduct(productInfo);

//        设置跳转
//        view.setViewName("/admin/list.action");

//        这里是运用了从定向功能
        return "redirect:/admin/list.action";
    }

    /**
     * --------------------------------------查询已经被废除----------------------------------------
     */
    @RequestMapping(value = "admin/search.action", method = RequestMethod.POST)
    public void searchProduct(HttpServletRequest request, HttpServletResponse response) {

        /**
         * 刚才没有获取到值是因为pName中的值大写了，但是并没有看出来
         */
        String pName = request.getParameter("pName");
        String pId = request.getParameter("pId");
        logger.info(pId.trim());
        logger.info(pName.trim());

//        模糊查询
//        List<ProductInfo> productInfoList = productInfoService.findAllLike(pId, pName,);
//        request.setAttribute("productInfo",productInfoList);
//        跳转界面
        try {
            request.getRequestDispatcher("/admin/products/list.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return null;
    }

    /**
     * 批量删除功能
     * @param strings  传过来的productId数组
     * @return
     */
    @RequestMapping("/admin/deleteAll.action")
    public ModelAndView deleteAll(@RequestParam("ids") String[] strings){

        logger.info(strings.toString());
        ModelAndView view = new ModelAndView();
        productInfoService.deleteAll(Arrays.asList(strings));

        /**-----------------ModelAndView重定向-----------------------*/
        view.setViewName("redirect:/admin/list.action");
        return view;
    }
}
