package com.ejavashop.dao.shop.read.product;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.ProductAsk;

/**
 * 咨询管理
 *                       
 * @Filename: ProductAskReadDao.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Repository
public interface ProductAskReadDao {

    ProductAsk get(java.lang.Integer id);

    Integer queryCount(Map<String, Object> map);

    List<ProductAsk> queryList(Map<String, Object> map);

}
