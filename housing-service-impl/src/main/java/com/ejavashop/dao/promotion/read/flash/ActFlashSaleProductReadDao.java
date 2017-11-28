package com.ejavashop.dao.promotion.read.flash;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.flash.ActFlashSaleProduct;

@Repository
public interface ActFlashSaleProductReadDao {

    ActFlashSaleProduct get(java.lang.Integer id);

    /**
     * 根据阶段ID获取该阶段下的所有活动商品
     * @param actFlashSaleStageId
     * @return
     */
    List<ActFlashSaleProduct> getByStageId(java.lang.Integer actFlashSaleStageId);

    /**
     * 根据阶段ID获取审核通过的活动商品
     * @param actFlashSaleStageId
     * @return
     */
    List<ActFlashSaleProduct> getEffectiveByStageId(java.lang.Integer actFlashSaleStageId);

    List<ActFlashSaleProduct> getByStageIdAndSellerId(@Param("actFlashSaleStageId") Integer actFlashSaleStageId,
                                                      @Param("sellerId") Integer sellerId);

    /**
     * 根据阶段ID、商品ID，获取审核通过的活动商品信息
     * @param actFlashSaleStageId 阶段ID
     * @param productId 商品ID
     * @return
     */
    ActFlashSaleProduct getByStageIdAndProductId(@Param("actFlashSaleStageId") Integer actFlashSaleStageId,
                                                 @Param("productId") Integer productId);

    /**
     * 以商品id、卖家id、活动阶段和活动id查询活动商品
     * @param productId
     * @return
     */
    List<ActFlashSaleProduct> getActFlashSaleProduct(Map<String, Object> map);

    /**
     * 根据活动ID、商品ID，获取审核通过的活动商品信息
     * @param actFlashSaleId 活动ID
     * @param productId 商品ID
     * @return
     */
    List<ActFlashSaleProduct> getByActFlashSaleIdAndProductId(@Param("actFlashSaleId") Integer actFlashSaleId,
                                                              @Param("productId") Integer productId);

}