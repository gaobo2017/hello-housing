package com.ejavashop.dao.shop.write.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.ProductTypeAttr;
import com.ejavashop.vo.product.ProductTypeAttrVO;

@Repository
public interface ProductTypeAttrWriteDao {

    Integer insert(ProductTypeAttr productTypeAttr);

    Integer update(ProductTypeAttr productTypeAttr);

    Integer del(Integer id);

    Integer delByTypeId(Integer id);

    ProductTypeAttr get(Integer id);

    List<ProductTypeAttr> getByTypeId(Integer id);

    Integer count(@Param("param1") Map<String, String> queryMap);

    List<ProductTypeAttrVO> page(@Param("param1") Map<String, String> queryMap,
                                 @Param("start") Integer start, @Param("size") Integer size);
}
