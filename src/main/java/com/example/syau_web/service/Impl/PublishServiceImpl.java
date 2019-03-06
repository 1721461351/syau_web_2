package com.example.syau_web.service.Impl;

import com.example.syau_web.DTO.PublishDTO;
import com.example.syau_web.domain.Publish;
import com.example.syau_web.domain.User;
import com.example.syau_web.domain.map.PublishMap;
import com.example.syau_web.repository.UserRepository;
import com.example.syau_web.service.PublishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * create by wangyu
 * 2018/10/25 0025 21:08
 */
@Service
public class PublishServiceImpl implements PublishService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PublishMap publishMap;

    /**
     * 添加一条公告
     * @param publishDTO 要添加的公告对象
     * @return 添加的公告数目 貌似没什么用
     */
    @Override
    public int addPublish(PublishDTO publishDTO) {
        //        根据学号查询一个人的信息
        User user = userRepository.findById(publishDTO.getUserNumber()).get();
//        把个人信息封装到publishDTO中
        publishDTO.setUser(user);
//        把publishDTO中的数据转移到publish中
        Publish publish = new Publish();
//        把publishDto中的数据转移到publish中去
        BeanUtils.copyProperties(publishDTO, publish);
//        增加内容
        int i = publishMap.addPublish(publish);
        return i;
    }


    /**
     * 公告列表
     * @return 公告列表 todo 公告列表还没有实现
     */
    @Override
    public List<PublishDTO> publishList(Integer number) {

        PublishDTO publishDTO = new PublishDTO();
        List<Publish> publishList = publishMap.selectAllPublish();
        User user = userRepository.findById(number).get();
        List<PublishDTO> publishDTOList = new ArrayList<>();
        for (Publish publish:publishList){
            BeanUtils.copyProperties(publish,publishDTO);
            publishDTO.setUser(user);
            publishDTOList.add(publishDTO);
        }
        return publishDTOList;

    }

    /**
     * 删除公告
     * @param publishDTO todo 还没有删除公告的界面
     * @return 删除的公告数目
     */
    @Override
    public int deletePublish(PublishDTO publishDTO) {

        return publishMap.deletePublishById(publishDTO.getPublishId());
    }

    @Override
    public List<Publish> publishStudentList() {
        List<User> userList = userRepository.findAllByRole(0);
        /**--------------lamb表达式-------------------*/
        List<Integer> integerList = userList
                .stream()
                .map(user -> user.getNumber())
                .collect(Collectors.toList());
        List<Publish> publishList = publishMap.findAllByNumber(integerList);

        return publishList;
    }
}
