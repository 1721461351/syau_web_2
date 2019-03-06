package com.example.syau_web.controller;

import com.example.syau_web.DTO.PublishDTO;
import com.example.syau_web.domain.Publish;
import com.example.syau_web.domain.User;
import com.example.syau_web.enums.MyExceptionEnum;
import com.example.syau_web.enums.ResultEnum;
import com.example.syau_web.exception.MyException;
import com.example.syau_web.service.Impl.PublishServiceImpl;
import com.example.syau_web.service.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * create by wangyu
 * 2018/10/26 0026 13:38
 * <p>
 * 此类是和发布动态有关的类
 */
@Controller
public class PublishController {

    @Autowired
    private PublishServiceImpl publishService;

    @Autowired
    private WebSocket webSocket;
    private final static Logger logger = LoggerFactory.getLogger(PublishController.class);

    /**
     * 添加一条动态
     *
     * @param request
     * @param response
     */
    @RequestMapping("/admin/addpublish.action")
    public ModelAndView addPublish(HttpServletRequest request,
                                   HttpServletResponse response,
                                   Map<String,Object> map) {
        Publish publish = new Publish();
        PublishDTO publishDTO = new PublishDTO();

//        获取表单数据
        String description = request.getParameter("publishContent");
//        String description = request.getParameter("publishDescription");

        /**-------------从session中获取用户信息，一定要登录，才能有用户的信息-----------*/
        User user = (User) request.getSession().getAttribute("user");

//        运用自定义的publishDTO
        publishDTO.setUser(user);

        publishDTO.setPublishContent(description);
        publishDTO.setUserNumber(user.getNumber());
        int i = publishService.addPublish(publishDTO);

        //添加消息,公告的内容
        webSocket.sendMessage(publishDTO.getPublishContent());

        if (i != 0) {//如果添加了公告
            try {
                //            分发转向
//                request.getRequestDispatcher("/admin/publishList.action").forward(request, response);
                map.put("url","/admin/publishList.action");
//                request.setAttribute("url","/admin/publishList.action");
                map.put("msg", ResultEnum.PUBLIC_SUCCESS.getMessage());
//                request.getRequestDispatcher("/publish/list").forward(request, response);
                return new ModelAndView("common/success",map);
            } catch (MyException e){
                logger.info(e.getMessage());
            }
        }
        return  null;
    }

    /**
     * 显示公告列表
     */
    @RequestMapping("/admin/publishList.action")
    public ModelAndView publishList(ModelAndView modelAndView) {
//        List<Publish> publishList = publishService.publishList();
        /**显示的是学生反馈的信息*/
        List<Publish> publishList = publishService.publishStudentList();
//       把集合存入

        modelAndView.addObject("publishList", publishList);
//        modelAndView.setViewName("/admin/products/publishList.jsp");
        modelAndView.setViewName("publish/list");

        webSocket.sendMessage("公告列表");

        return modelAndView;
    }


    @RequestMapping("ftl/admin/publish")
    public ModelAndView findPulish(){
        return new ModelAndView("publish/index");
    }

}
