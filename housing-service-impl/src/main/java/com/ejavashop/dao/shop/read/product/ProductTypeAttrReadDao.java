package com.ejavashop.dao.shop.read.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.ProductTypeAttr;

@Repository
public interface ProductTypeAttrReadDao {

    ProductTypeAttr get(@Param("id") Integer id);

    /**
     * 根据类型查询出类型下面所有的检索属性
     * @param id
     * @return
     */
    List<ProductTypeAttr> getByTypeIdAndQuery(@Param("id") Integer id);

}
