package com.example.syau_web.repository;

import com.example.syau_web.domain.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

/**
 * create by wangyu
 * 2018/10/22 0022 8:34
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {


    List<ProductInfo> findByProductStatus(Integer productStatus);
//    public Page<ProductInfo> findAllByProductIdLikeAndProductNameLikeAndProductTypeAndProductPriceIn

    /**
     * 根据productId和productName进行模糊查询。
     * @param productId 商品id
     * @param productName 商品的名字
     * @return  模糊查询列表
     */
    public List<ProductInfo> findAllByProductIdLikeAndProductNameLike(String productId,String productName);


    /**
     * 原生的注解查询方式 ，要用concat连接起来
     * @param productId
     * @param productName
     * @return
     */

    /**--------------------------注意：模糊查询这里原生sql语句的写法，使用其他的写法都会报错--------------**/
    @Query(nativeQuery = true,value = "SELECT * from product_info where product_id like CONCAT('%',:productId,'%') and product_name like CONCAT('%',:productName,'%') and user_number=:userNumber")
    public List<ProductInfo> finAllLike(@Param("productId") String productId, @Param("productName") String productName,@Param("userNumber") Integer userNumber);




}
