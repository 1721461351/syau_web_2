package com.example.syau_web.repository;

import com.example.syau_web.domain.ProductInfo;
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
 * 2018/10/24 0024 18:48
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Test
    public void findAllByProductIdLikeAndProductNameLike() {
        List<ProductInfo> productInfoList = productInfoRepository.findAllByProductIdLikeAndProductNameLike("154", "梨");
        productInfoList.stream().forEach(productInfo -> Assert.assertEquals("水果",productInfo.getProductType()));


    }

    /**
     * 原生的模糊查询
     *
     * 这个自然的jpa模糊查询有点坑，
     * 按照传统的传递参数的方式，
     * 不对，查出的结果为0条，
     * 需要用concat给连接起来
     *              测试通过
     */
    @Test
    public void finAllLike() {
        List<ProductInfo> productInfoList = productInfoRepository.finAllLike("154", "",2016188023);


        productInfoList.stream().forEach(productInfo -> Assert.assertEquals("水果",productInfo.getProductType()));
    }

    @Test
    public void findByProductStatus(){
        List<ProductInfo> list = productInfoRepository.findByProductStatus(0);

        Assert.assertNotNull(list);
    }

    @Test
    public void save(){
    }
}