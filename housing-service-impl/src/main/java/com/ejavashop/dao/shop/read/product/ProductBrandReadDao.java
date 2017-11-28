package com.ejavashop.dao.shop.read.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.ProductBrand;

/**
 * 品牌的接口
 *                       
 * @Filename: ProductBrandReadDao.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Repository
public interface ProductBrandReadDao {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    ProductBrand getById(@Param("id") Integer id);

    /**
     * 根据ID的集合统计品牌
     * @param ids
     * @return
     */
    List<ProductBrand> getByIds(@Param("ids") String ids);

}
