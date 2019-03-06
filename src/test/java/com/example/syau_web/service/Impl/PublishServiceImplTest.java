package com.example.syau_web.service.Impl;

import com.example.syau_web.domain.Publish;
import com.example.syau_web.domain.map.PublishMap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * create by wangyu
 * 2018/11/17 0017 18:06
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PublishServiceImplTest {

    @Autowired
    private PublishServiceImpl publishService ;
    @Test
    public void addPublish() {
    }

    @Test
    public void publishList() {
    }

    @Test
    public void deletePublish() {
    }

    @Test
    public void publishStudentList() {
        List<Publish> publishList = publishService.publishStudentList();
        Assert.assertNotNull(publishList);
    }
}