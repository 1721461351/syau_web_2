package com.example.syau_web.afreemarkeradmin.controller;

import com.example.syau_web.domain.PortData;
import com.example.syau_web.service.Impl.PortServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * create by wangyu
 * 2018/11/10 0010 17:29
 */
@Controller
@RequestMapping("ftl/admin/port")
public class FPortController {
    @Autowired
    private PortServiceImpl portService;

    /**
     * 返回的是串口的list集合
     * @return
     */
    @RequestMapping("list")
    public ModelAndView selectAllPortData(Map<String,Object> map){
        List<PortData> portDataList = portService.selectAll();
        map.put("portDataList",portDataList);
        return new ModelAndView("port/list",map);
    }
}
