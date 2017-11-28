package com.ejavashop.dao.shop.read.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.ProductGoods;

@Repository
public interface ProductGoodsReadDao {

    List<ProductGoods> getByProductId(@Param("productId") Integer productId);

    ProductGoods get(java.lang.Integer id);

    Integer queryCount(Map<String, Object> map);

    List<ProductGoods> queryList(Map<String, Object> map);

    /**
     * 根据条件获取货品，只返回一条或零条货品
     * @param map
     * @return
     */
    ProductGoods getByCondition(Map<String, String> map);

}
