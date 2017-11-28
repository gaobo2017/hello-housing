package com.ejavashop.dao.shop.write.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.ProductType;

@Repository
public interface ProductTypeWriteDao {

    Integer insert(ProductType productType);

    Integer update(ProductType productType);

    Integer del(Integer id);

    ProductType get(Integer id);

    Integer count(@Param("param1") Map<String, String> queryMap);

    List<ProductType> page(@Param("param1") Map<String, String> queryMap,
                           @Param("start") Integer start, @Param("size") Integer size);
    List<ProductType> getByNormId(@Param("normId") Integer normId);
    List<ProductType> getByBrandId(@Param("brandId") Integer brandId);
}
