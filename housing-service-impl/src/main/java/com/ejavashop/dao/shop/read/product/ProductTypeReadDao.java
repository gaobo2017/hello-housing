package com.ejavashop.dao.shop.read.product;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.ProductType;

@Repository
public interface ProductTypeReadDao {

    ProductType get(Integer id);

}
