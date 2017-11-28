package com.ejavashop.dao.shop.read.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.ProductNorm;
import com.ejavashop.entity.product.ProductNormAttr;
import com.ejavashop.entity.product.ProductNormAttrOpt;

/**
 * 商品规格、商品规格属性、商品选定的规格属性数据库操作
 * @Version: 1.0
 * @Author: zhaozhx
 * @Email: zhaozhx@sina.cn
 */
@Repository
public interface ProductNormReadDao {

    ProductNorm getNormById(Integer id);

    List<ProductNorm> getNormByIds(@Param("ids") String ids);

    Integer countNorm(@Param("param1") Map<String, String> queryMap);

    List<ProductNorm> getNormByPage(@Param("param1") Map<String, String> queryMap,
                                    @Param("start") Integer start, @Param("size") Integer size);

    ProductNormAttr getNormAttrById(Integer id);

    List<ProductNormAttr> getAttrByNormId(Integer id);

    List<ProductNormAttr> getAttrByNormIds(@Param("ids") String ids);

    Integer countNormAttr(@Param("param1") Map<String, String> queryMap);

    List<ProductNormAttr> getNormAttrByPage(@Param("param1") Map<String, String> queryMap,
                                            @Param("start") Integer start,
                                            @Param("size") Integer size);

    ProductNormAttrOpt getNormAttrOptById(Integer id);

    Integer countNormAttrOpt(@Param("param1") Map<String, String> queryMap);

    List<ProductNormAttrOpt> getNormAttrOptByPage(@Param("param1") Map<String, String> queryMap,
                                                  @Param("start") Integer start,
                                                  @Param("size") Integer size);

}
