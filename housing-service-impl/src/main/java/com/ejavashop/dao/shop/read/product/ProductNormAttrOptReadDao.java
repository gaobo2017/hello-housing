package com.ejavashop.dao.shop.read.product;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.ProductNormAttrOpt;

@Repository
public interface ProductNormAttrOptReadDao {

    ProductNormAttrOpt get(java.lang.Integer id);

    Integer queryCount(Map<String, Object> map);

    List<ProductNormAttrOpt> queryList(Map<String, Object> map);

    List<ProductNormAttrOpt> queryNormsByProductId(Integer productId);

}
