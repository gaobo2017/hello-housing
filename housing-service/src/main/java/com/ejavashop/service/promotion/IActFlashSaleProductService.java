package com.ejavashop.service.promotion;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.promotion.flash.ActFlashSaleProduct;

public interface IActFlashSaleProductService {

    /**
     * 根据id取得限时抢购活动商品
     * @param  actFlashSaleProductId
     * @return
     */
    ServiceResult<ActFlashSaleProduct> getActFlashSaleProductById(Integer actFlashSaleProductId);

    /**
     * 保存限时抢购活动商品，如果商品存在执行更新操作
     * @param  actFlashSaleProduct
     * @return
     */
    ServiceResult<Boolean> saveActFlashSaleProduct(ActFlashSaleProduct actFlashSaleProduct);

    /**
     * 更新限时抢购活动商品
     * @param actFlashSaleProduct
     * @return
     */
    ServiceResult<Boolean> updateActFlashSaleProduct(ActFlashSaleProduct actFlashSaleProduct);

    /**
     * 更新限时抢购活动商品状态（只修改活动状态、审核意见、修改者信息）
     * @param actFlashSaleProduct
     * @return
     */
    ServiceResult<Boolean> updateActFlashSaleProductStatus(ActFlashSaleProduct actFlashSaleProduct);

    /**
     * 删除限时抢购活动商品
     * 
     * @param actFlashSaleProductId
     * @param userId 删除人ID
     * @param userName 删除人名称
     * @return
     */
    ServiceResult<Boolean> deleteActFlashSaleProduct(Integer actFlashSaleProductId, Integer userId,
                                                     String userName);

    /**
     * 根据阶段ID、商家ID、商品ID删除限时抢购活动商品
     * 
     * @param actFlashSaleStageId
     * @param sellerId
     * @param productId
     * @param userId 删除人ID
     * @param userName 删除人名称
     * @return
     */
    ServiceResult<Boolean> deleteActFlashSaleProductForSeller(Integer actFlashSaleStageId,
                                                              Integer sellerId, Integer productId,
                                                              Integer userId, String userName);

    /**
     * 根据阶段ID获取活动商品
     * @param actFlashSaleStageId
     * @return
     */
    ServiceResult<List<ActFlashSaleProduct>> getActFlashSaleProductsByStageId(Integer actFlashSaleStageId);

    /**
     * 根据阶段ID和商家ID获取活动商品
     * @param actFlashSaleStageId
     * @param sellerId
     * @return
     */
    ServiceResult<List<ActFlashSaleProduct>> getActFlashSaleProductsByStageIdAndSellerId(Integer actFlashSaleStageId,
                                                                                         Integer sellerId);

    /**
     * 以商品id、卖家id、活动阶段和活动id查询活动商品
     * @param param
     * @return
     */
    ServiceResult<List<ActFlashSaleProduct>> getActFlashSaleProduct(Map<String, Object> param);

}