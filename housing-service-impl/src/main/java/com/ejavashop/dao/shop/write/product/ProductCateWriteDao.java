package com.ejavashop.dao.shop.write.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.ProductCate;
import com.ejavashop.vo.product.ProductCateVO;

@Repository
public interface ProductCateWriteDao {

    Integer insert(ProductCate productCate);

    Integer update(ProductCate productCate);

    Integer del(Integer id);

    ProductCate get(Integer id);

    ProductCate getByTypeId(@Param("typeId") Integer typeId);

    Integer count(Map<String, String> queryMap);

    List<ProductCateVO> page(Map<String, Object> queryMap);

    List<ProductCate> getByPid(Integer pid);

    List<ProductCate> getBySellerId(Integer sellerId);
}
