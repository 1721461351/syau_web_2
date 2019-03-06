package com.example.syau_web.controller;

import com.example.syau_web.DTO.PublishDTO;
import com.example.syau_web.constant.CookieConstant;
import com.example.syau_web.domain.PortData;
import com.example.syau_web.domain.ProductInfo;
import com.example.syau_web.domain.Publish;
import com.example.syau_web.domain.User;
import com.example.syau_web.domain.map.PublishMap;
import com.example.syau_web.enums.ResultEnum;
import com.example.syau_web.form.UserForm;
import com.example.syau_web.repository.UserRepository;
import com.example.syau_web.service.Impl.PortServiceImpl;
import com.example.syau_web.service.Impl.ProductInfoServiceImpl;
import com.example.syau_web.service.Impl.PublishServiceImpl;
import com.example.syau_web.service.Impl.UserServiceImpl;
import com.example.syau_web.util.CookieUtil;
import com.example.syau_web.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import javax.xml.ws.RespectBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * create by wangyu
 * 2018/11/17 0017 8:46
 */
@Controller
public class StudentController {
    @Autowired
    private PublishServiceImpl publishService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ProductInfoServiceImpl productInfoService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PortServiceImpl portService;
    @Autowired
    private PublishMap publishMap;

    @RequestMapping("login1.action")
    public ModelAndView studentController(@PathParam("username") Integer username,
                                          @PathParam("password") String password,
                                          Map<String, Object> map,
                                          HttpServletRequest request,
                                          HttpServletResponse response) {

        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        /**---------------这里是跳转界面的情况-------------------*/
        if (username == null && password == null) {
            map.put("productList", productInfoList);
            Integer n = (Integer) request.getSession().getAttribute("number");
            if (n==null){
                map.put("url","mypage/login1.jsp");
                map.put("msg",ResultEnum.NOT_LOGIN.getMessage());
                return new ModelAndView("common/error",map);
            }
            User user = userRepository.findById(n).get();

            //todo  要把用户信息加入需要改动service

//            List<PublishDTO> publishList = publishService.publishList(user.getNumber());
            List<Publish> publishList = publishMap.selectAllPublish();

            /**-----------------------------------------*/
            PublishDTO publishDTO = new PublishDTO();
            List<PublishDTO> publishDTOS = new ArrayList<>();
           /* for (Publish publish:publishList){
                publishDTOS.add();
            }*/
            map.put("user", user);
            map.put("publishList", publishList);
            map.put("productList", productInfoList);
            return new ModelAndView("mypage/student.jsp", map);
//            return new ModelAndView("mypage/student.jsp",map);
        }
        User user = userService.login(username, password);
        if (user != null) {

            /*//设置cookie的token，token为用户的学号
            String token = String.valueOf(user.getNumber());
            //设置cookie的过期时间
            Integer expire = CookieConstant.EXPIRE;
            //设置cookie
            CookieUtil.setCookie(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);
*/
            if (user.getRole() == 0) {//学生权限
                //设置cookie的token，token为用户的学号
                String token = String.valueOf(user.getNumber());
                //设置cookie的过期时间
                Integer expire = CookieConstant.EXPIRE;
                //设置学生的cookie
                CookieUtil.setCookie(response, CookieConstant.STUDENT_TOKEN, token, CookieConstant.EXPIRE);

//                List<PublishDTO> publishList = publishService.publishList(user.getNumber());
                List<Publish> publishList = publishMap.selectAllPublish();
                map.put("user", user);
                map.put("publishList", publishList);
                map.put("productList", productInfoList);
                return new ModelAndView("mypage/student.jsp", map);
            }
            if (user.getRole() == 2) {//管理员权限
                //设置cookie的token，token为用户的学号
                String token = String.valueOf(user.getNumber());
                //设置cookie的过期时间
                Integer expire = CookieConstant.EXPIRE;
                //设置cookie
                CookieUtil.setCookie(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);

                request.getSession().setAttribute("user", user);
                map.put("url", "/ftl/admin/list.action");
                map.put("msg", ResultEnum.LOGIN_SUCCESS.getMessage()+ user.getUsername());
                return new ModelAndView("common/success", map);
            }
            if (user.getRole() == 1) {//老师权限
//                List<PublishDTO> publishList = publishService.publishList(user.getNumber());
                List<Publish> publishList = publishMap.selectAllPublish();
                //串口中最新的数据
                PortData maxData = portService.findMaxId();
                //串口中所有的数据
                List<PortData> portData = portService.selectAll();
                request.getSession().setAttribute("user", user);
                map.put("publishList", publishList);
                map.put("productList", productInfoList);
                map.put("maxData", maxData);
                map.put("portData", portData);
                return new ModelAndView("mypage/teacher.jsp", map);
            }
        }
        //查询到结果为空的时候
        map.put("msg", ResultEnum.NUMBER_PASSWORD_ERROR.getMessage());
        return new ModelAndView("mypage/login1.jsp", map);
    }

    /*@RequestMapping("studentIndex")
    public ModelAndView studentList(Map<String, Object> map){
        ModelAndView view = new ModelAndView();
        Map<String, Object> model = view.getModel();
        Object usernumber = model.get("usernumber");
        return null;
    }*/

    @RequestMapping("saveStudent")
    public ModelAndView saveStudent(UserForm userForm,
                                    BindingResult bindingResult,
                                    Map<String, Object> map,
                                    HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            map.put("url", "login1.action");
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            return new ModelAndView("common/error", map);
        } else {

            User user = userRepository.findById(userForm.getNumber()).get();
            request.getSession().setAttribute("number", user.getNumber());
            user.setUsername(userForm.getUsername());
            user.setEmail(userForm.getEmail());
            user.setTelephone(userForm.getTelephone());
            user.setSex(userForm.getSex());
            //保存
            userRepository.save(user);
            //跳转
            map.put("url", "login1.action");
            map.put("msg", ResultEnum.SAVE_USER_SUCCESS.getMessage());

            return new ModelAndView("common/success", map);
        }

    }


    /**
     * 管理员登出功能实现
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping("logout")
    public ModelAndView logout(HttpServletRequest request,
                       HttpServletResponse response,
                               Map<String,Object> map){
        //1.获取cookie
        Cookie cookie = CookieUtil.getCookie(request, CookieConstant.TOKEN);
        // 2.清除cookie
        if (cookie!=null) {
            CookieUtil.setCookie(response, CookieConstant.TOKEN, null, 0);
        }
        map.put("msg",ResultEnum.LOGIN_OUT.getMessage());
        map.put("url","login");
        return new ModelAndView("common/success",map);
    }

    /**
     * 学生登出界面实现
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping("studentlogout")
    public ModelAndView studentlogout(HttpServletRequest request,
                               HttpServletResponse response,
                               Map<String,Object> map){
        //1.获取cookie
        Cookie cookie = CookieUtil.getCookie(request, CookieConstant.STUDENT_TOKEN);
        // 2.清除cookie
        if (cookie!=null) {
            CookieUtil.setCookie(response, CookieConstant.STUDENT_TOKEN, null, 0);
        }
        map.put("msg",ResultEnum.LOGIN_OUT.getMessage());
        map.put("url","login");
        return new ModelAndView("common/success",map);
    }
}
