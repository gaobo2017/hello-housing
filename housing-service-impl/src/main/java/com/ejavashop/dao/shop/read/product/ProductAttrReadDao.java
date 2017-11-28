package com.ejavashop.dao.shop.read.product;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.ProductAttr;

@Repository
public interface ProductAttrReadDao {

    ProductAttr get(Integer id);

    List<ProductAttr> getByProductId(Integer productId);

}
