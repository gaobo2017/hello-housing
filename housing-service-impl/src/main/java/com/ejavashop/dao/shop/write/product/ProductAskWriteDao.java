package com.ejavashop.dao.shop.write.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.ProductAsk;

@Repository
public interface ProductAskWriteDao {

    ProductAsk get(Integer id);

    Integer insert(ProductAsk productAsk);

    Integer update(ProductAsk productAsk);

    Integer getProductAsksCount(@Param("queryMap") Map<String, String> query);

    List<ProductAsk> getProductAsks(@Param("queryMap") Map<String, String> queryMap,
                                    @Param("start") Integer start, @Param("size") Integer size);

}
