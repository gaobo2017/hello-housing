package com.ejavashop.dao.shop.read.product;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.ProductCate;

@Repository
public interface ProductCateReadDao {

    ProductCate get(Integer id);

    Integer queryCount(Map<String, Object> map);

    List<ProductCate> queryList(Map<String, Object> map);

    /**
     * 根据Pid获取分类列表
     * @param pid
     * @return
     */
    List<ProductCate> getByPid(Integer pid);

}
