package com.ejavashop.dao.shop.read.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.ProductPicture;

@Repository
public interface ProductPictureReadDao {

    ProductPicture get(@Param("id") java.lang.Integer id);

    /**
     * 根据商品ID取得图片
     * @param productId
     * @return
     */
    List<ProductPicture> getByProductId(@Param("productId") Integer productId);

    /**
     * 根据商品ID取得图片
     * @param productId
     * @return
     */
    ProductPicture getproductLead(@Param("productId") Integer productId);

    Integer count(@Param("param1") Map<String, String> queryMap);

    List<ProductPicture> page(@Param("param1") Map<String, String> queryMap,
                              @Param("start") Integer start, @Param("size") Integer size);

}
