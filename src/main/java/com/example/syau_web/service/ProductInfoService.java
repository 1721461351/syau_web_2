package com.example.syau_web.service;

import com.example.syau_web.domain.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * create by wangyu
 * 2018/10/22 0022 9:04
 */
public interface ProductInfoService {

    /**
     * 查询所有商品的列表
     *
     * @param pageable 分页
     * @return 分页列表
     */
    public Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 添加商品
     *
     * @param productInfo 商品信息
     * @return 新添加商品的信息
     */
    public ProductInfo addProduct(ProductInfo productInfo);

    /**
     * 通过id删除商品
     *
     * @param productById 商品的id
     * @return 删除成功返回true，否则返回false
     */
    public boolean deleteById(String productById);

    /**
     * 根据商品的id更新商品
     *
     * @param productInfo 商品的id
     * @return 修改后商品的信息
     */
    public ProductInfo updateProduct(ProductInfo productInfo);

    /**
     * 根据商品的id删除商品
     *
     * @param productId 商品的id
     * @return 查询到的该商品信息
     */
    public ProductInfo finOneById(String productId);

    /**
     * 商品列表，不带分页
     *
     * @return
     */
    public List<ProductInfo> ProductList();

    /**
     * 根据商品信息模糊查询
     *
     * @param productId
     * @param productName
     * @return 模糊查询列表
     */
    public List<ProductInfo> findAllLike(String productId, String productName,Integer userNumber);

    /**
     * 批量删除
     * @param ProductIds
     */
    public void deleteAll(List<String> ProductIds);

    /**
     * 查询所有上架的商品
     */
    public List<ProductInfo> findUpAll();

    /**
     * 商品上架
     *
     * @param productId
     * @return
     */
    ProductInfo productUp(String productId);

    /**
     * 商品下架
     * @param productId
     * @return
     */
    ProductInfo productOff(String productId);

    /**
     * 保存/修改
     * @param productInfo
     * @return
     */
    ProductInfo save(ProductInfo productInfo);
}
