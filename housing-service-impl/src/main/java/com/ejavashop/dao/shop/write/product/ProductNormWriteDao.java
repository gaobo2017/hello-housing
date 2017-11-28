package com.ejavashop.dao.shop.write.product;

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
public interface ProductNormWriteDao {

    Integer insertNorm(ProductNorm norm);

    Integer updateNorm(ProductNorm norm);

    ProductNorm getNormById(Integer id);

    List<ProductNorm> getNormByIds(@Param("ids") String ids);

    Integer countNorm(@Param("param1") Map<String, String> queryMap);

    List<ProductNorm> getNormByPage(@Param("param1") Map<String, String> queryMap,
                                    @Param("start") Integer start, @Param("size") Integer size);

    Integer insertNormAttr(ProductNormAttr attr);

    Integer updateNormAttr(ProductNormAttr attr);

    Integer delAttr(Integer id);

    Integer delAttrByNormId(Integer id);

    ProductNormAttr getNormAttrById(Integer id);

    List<ProductNormAttr> getAttrByNormId(Integer id);

    List<ProductNormAttr> getAttrByNormIds(@Param("ids") String ids);

    Integer countNormAttr(@Param("param1") Map<String, String> queryMap);

    List<ProductNormAttr> getNormAttrByPage(@Param("param1") Map<String, String> queryMap,
                                            @Param("start") Integer start,
                                            @Param("size") Integer size);

    Integer insertNormAttrOpt(ProductNormAttrOpt opt);

    Integer updateNormAttrOpt(ProductNormAttrOpt opt);

    Integer delOpt(Integer id);

    ProductNormAttrOpt getNormAttrOptById(Integer id);

    Integer countNormAttrOpt(@Param("param1") Map<String, String> queryMap);

    List<ProductNormAttrOpt> getNormAttrOptByPage(@Param("param1") Map<String, String> queryMap,
                                                  @Param("start") Integer start,
                                                  @Param("size") Integer size);

    Integer deleteProductNormOptByProductId(Integer proid);

    /**
     * 查询所有的规格
     * @return
     */
    List<ProductNorm> listNoPage();

}
