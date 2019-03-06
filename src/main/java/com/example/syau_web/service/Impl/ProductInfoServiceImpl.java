package com.example.syau_web.service.Impl;

import com.example.syau_web.domain.ProductInfo;
import com.example.syau_web.enums.MyExceptionEnum;
import com.example.syau_web.enums.ProductStatusEnum;
import com.example.syau_web.exception.MyException;
import com.example.syau_web.repository.ProductInfoRepository;
import com.example.syau_web.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * create by wangyu
 * 2018/10/22 0022 9:06
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo addProduct(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    public boolean deleteById(String productById) {
        productInfoRepository.deleteById(productById);
        return true;
    }

    @Override
    public ProductInfo updateProduct(ProductInfo productInfo) {


        return productInfoRepository.save(productInfo);
    }

    @Override
    public ProductInfo finOneById(String productId) {
        return productInfoRepository.findById(productId).get();
    }

    @Override
    public List<ProductInfo> ProductList() {
        return productInfoRepository.findAll();
    }

    @Override
    public List<ProductInfo> findAllLike(String productId, String productName,Integer userNumber) {
        return productInfoRepository.finAllLike(productId,productName,userNumber);
    }

    @Override
    @Transactional
    public void deleteAll(List<String> ProductIds) {
        for (String productId:ProductIds){
            productInfoRepository.deleteById(productId);
        }
    }

    @Override
    @Transactional
    public List<ProductInfo> findUpAll() {

        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public ProductInfo productUp(String productId) {
        //得到商品信息
        ProductInfo productInfo = productInfoRepository.findById(productId).get();
        //判断商品查询有木有
        if (productId == null) {
            throw new MyException(MyExceptionEnum.PRODUCT_NOT_NULL);
        }
        //判断状态
        if (productInfo.getProductStatus()==ProductStatusEnum.UP.getCode()){//如果是在架状态
            throw new MyException(MyExceptionEnum.PRODUCT_STATUS_ERROR);
        }
        //修改状态，改为上架
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        //保存数据库
        ProductInfo info = productInfoRepository.save(productInfo);
        return info;
    }

    @Override
    public ProductInfo productOff(String productId) {
        //得到商品信息
        ProductInfo productInfo = productInfoRepository.findById(productId).get();
        //判断商品查询有木有
        if (productId == null) {
            throw new MyException(MyExceptionEnum.PRODUCT_NOT_NULL);
        }
        //判断状态
        if (productInfo.getProductStatus()==ProductStatusEnum.DOWN.getCode()){//如果是下架状态
            throw new MyException(MyExceptionEnum.PRODUCT_STATUS_ERROR);
        }
        //修改状态，改为上架
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        //保存数据库
        ProductInfo info = productInfoRepository.save(productInfo);
        return info;
    }

    @Override
    @Transactional
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }
}
