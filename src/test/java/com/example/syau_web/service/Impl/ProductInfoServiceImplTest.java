package com.example.syau_web.service.Impl;

import com.example.syau_web.domain.ProductInfo;
import com.example.syau_web.enums.ProductStatusEnum;
import com.example.syau_web.repository.ProductInfoRepository;
import com.example.syau_web.util.RandomUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * create by wangyu
 * 2018/10/22 0022 9:10
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    /**
     * 分页查询所有的数据
     *                  测试通过
     */
    @Test
    public void findAll() {
        PageRequest page = new PageRequest(0, 2);
        Page<ProductInfo> productInfoServiceAll = productInfoService.findAll(page);
        long count = productInfoServiceAll.getTotalElements();

        Assert.assertEquals(5,count);
    }

    /**
     * 测试插入数据
     *              测试通过
     */
    @Test
    public void save(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(RandomUtil.getUniqueKey());
        productInfo.setProductName("苹果");
        productInfo.setProductNum(100);
        productInfo.setProductPrice(6.6);
        productInfo.setProductDescription("很好吃");
        productInfo.setProductType("5");
        productInfoRepository.save(productInfo);

        ProductInfo productInfo1 = new ProductInfo();
        productInfo1.setProductId(RandomUtil.getUniqueKey());
        productInfo1.setProductName("草莓");
        productInfo1.setProductNum(100);
        productInfo1.setProductPrice(6.0);
        productInfo1.setProductDescription("不错");
        productInfo1.setProductType("5");
        ProductInfo save = productInfoRepository.save(productInfo1);

        Assert.assertNotNull(save);
    }

    /**
     * 测试根据id查询一条数据
     *                      测试通过
     *
     */
    @Test
    public void findById(){

        ProductInfo productInfo = productInfoRepository.findById("1").get();

        Assert.assertEquals("苹果", productInfo.getProductName());

    }

    /**
     * 测试根据id删除数据
     *                  测试成功
     *
     */
    @Test
    public void deleteById(){
//        删除id="1"的数据
        productInfoRepository.deleteById("1");
    }

    @Test
    public void findAllLike(){
        List<ProductInfo> productInfoList = productInfoRepository.findAllByProductIdLikeAndProductNameLike("154", "苹果");

        productInfoList.stream().forEach(productInfo -> System.out.println(productInfo));
    }

    /**
     * 查询所有的在架状态的商品
     */
    @Test
    public void findUpAll(){
        List<ProductInfo> upAll = productInfoService.findUpAll();
        for (ProductInfo productInfo:upAll){
            System.out.println(productInfo);
        }
    }

    /**
     * 商品上架测试
     */
    @Test
    public void up(){
        ProductInfo productInfo = productInfoService.productUp("1541329759192");
        Assert.assertEquals(ProductStatusEnum.UP.getCode(),productInfo.getProductStatus());
    }
}