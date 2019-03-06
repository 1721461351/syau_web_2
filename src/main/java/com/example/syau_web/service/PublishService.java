package com.example.syau_web.service;

import com.example.syau_web.DTO.PublishDTO;
import com.example.syau_web.domain.Publish;
import com.example.syau_web.domain.User;

import java.util.List;

/**
 * create by wangyu
 * 2018/10/25 0025 21:01
 */
public interface PublishService {

    /**
     * 添加公告
     *
     * @param publishDTO 要添加的公告对象
     * @return
     */
    int addPublish(PublishDTO publishDTO);

    /**
     * 显示所有公告
     *
     * @return
     */
    List<PublishDTO> publishList(Integer number);

    /**
     * 根据公告的id删除公告
     *
     * @return 删除的公告
     */
    int deletePublish(PublishDTO publishDTO);

    /**
     * 查询所有学生发布的信息
     * @return
     */
    List<Publish> publishStudentList();
}
