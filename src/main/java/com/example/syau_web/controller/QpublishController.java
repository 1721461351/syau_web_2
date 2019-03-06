package com.example.syau_web.controller;

import com.example.syau_web.DTO.PublishDTO;
import com.example.syau_web.domain.Publish;
import com.example.syau_web.domain.User;
import com.example.syau_web.enums.ResultEnum;
import com.example.syau_web.repository.UserRepository;
import com.example.syau_web.service.Impl.PublishServiceImpl;
import com.example.syau_web.service.Impl.UserServiceImpl;
import com.example.syau_web.service.WebSocket;
import com.example.syau_web.util.DateFormatUtil;
import com.example.syau_web.util.MultipartUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

/**
 * create by wangyu
 * 2018/11/16 0016 20:16
 * <p>
 * 前台查看消息的controller
 */
@Controller
public class QpublishController {

    @Autowired
    private PublishServiceImpl publishService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebSocket webSocket;
    private final static Logger logger = LoggerFactory.getLogger(PublishController.class);

    /**
     * 显示公告列表
     */
    /*@RequestMapping("/publishList.action")
    public ModelAndView publishList(ModelAndView modelAndView) {
        List<Publish> publishList = publishService.publishList();

//       把集合存入
        modelAndView.addObject("publishList", publishList);
//        modelAndView.setViewName("/admin/products/publishList.jsp");
        modelAndView.setViewName("publish/list");
//        webSocket.sendMessage("");

        return modelAndView;
    }*/

    @RequestMapping(value = "suggest.action", method = RequestMethod.POST)
    public ModelAndView addsuggest(Map<String, Object> map,
                                   @PathParam("suggest") String suggest,
                                   @PathParam("usernumber") Integer usernumber,
                                   @RequestParam(value = "fileUp",required = false) MultipartFile file,
                                   HttpServletRequest request) {
        PublishDTO publishDTO = new PublishDTO();
        request.getSession().setAttribute("number", usernumber);
//        map.put("usernumber",usernumber);
        User user = userRepository.findById(usernumber).get();
        if (user != null && suggest != null) {
            publishDTO.setUserNumber(usernumber);
            publishDTO.setUser(user);
            publishDTO.setPublishContent(suggest);

            if (file!=null){
                Map<String, Object> filemap = MultipartUtil.upload(file);
                String filename = (String) filemap.get("文件名");
                String suffixName = (String) filemap.get("文件后缀名");
                String uniqueKey = (String) filemap.get("新名字");
                publishDTO.setFileName(filename);
            }
//            publishDTO.setCreateTime(DateFormatUtil.getDate());
            //设置用户
            publishDTO.setUser(user);
            publishService.addPublish(publishDTO);
//            map.put("url","mypage/student.jsp");
            map.put("url", "login1.action");
            map.put("msg", ResultEnum.SUGGEST_SUCCESS.getMessage());

            //websocket消息推送
            webSocket.sendMessage(suggest);
            return new ModelAndView("common/success", map);


//            return new ModelAndView("mypage/student.jsp",map);
        } else {
            map.put("url", "mypage/student.jsp");
            map.put("msg", ResultEnum.SUGGEST_ERROR.getMessage());
            return new ModelAndView("common/error", map);
        }
    }
}
