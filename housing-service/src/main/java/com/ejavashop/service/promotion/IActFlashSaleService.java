package com.ejavashop.service.promotion;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.promotion.flash.ActFlashSale;
import com.ejavashop.entity.promotion.flash.ActFlashSaleStage;

public interface IActFlashSaleService {

    /**
     * 根据id取得限时抢购活动
     * @param  actFlashSaleId
     * @return
     */
    ServiceResult<ActFlashSale> getActFlashSaleById(Integer actFlashSaleId);

    /**
     * 根据id、商家ID取得限时抢购活动，封装活动阶段以及阶段的活动商品
     * @param  actFlashSaleId
     * @param  sellerId 商家ID（为空时取所有的活动商品，不为空时取对应商家的活动商品）
     * @return
     */
    ServiceResult<ActFlashSale> getActFlashSaleByIdAndSellerId(Integer actFlashSaleId,
                                                               Integer sellerId);

    /**
     * 保存限时抢购活动
     * @param  actFlashSale
     * @return
     */
    ServiceResult<Boolean> saveActFlashSale(ActFlashSale actFlashSale);

    /**
     * 更新限时抢购活动
     * @param actFlashSale
     * @return
     */
    ServiceResult<Boolean> updateActFlashSale(ActFlashSale actFlashSale);

    /**
     * 更新限时抢购活动状态（只修改活动状态、审核意见、修改者信息）
     * @param actFlashSale
     * @return
     */
    ServiceResult<Boolean> updateActFlashSaleStatus(ActFlashSale actFlashSale);

    /**
     * 删除限时抢购活动
     * 
     * @param actFlashSaleId
     * @param userId 删除人ID
     * @param userName 删除人名称
     * @return
     */
    ServiceResult<Boolean> deleteActFlashSale(Integer actFlashSaleId, Integer userId,
                                              String userName);

    /**
     * 根据条件取得限时抢购活动
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<ActFlashSale>> getActFlashSales(Map<String, String> queryMap,
                                                       PagerInfo pager);

    /**
     * 根据活动日期、渠道取得限时抢购活动（上架的活动，如果有多个，只取最新的一个）
     * 
     * @param actDate 活动日期，日期的时分秒会设定成0，如2016-05-14 00:00:00
     * @param channel 渠道
     * @return
     */
    ServiceResult<ActFlashSale> getEffectiveActFlashSale(Date actDate, Integer channel);

    /**
     * 根据商品ID、渠道取得当前时间点的该商品参加的限时抢购活动、阶段、活动商品信息（上架的活动，如果有多个，只取最新的一个）
     * 
     * @param productId 商品ID
     * @param channel 渠道
     * @return
     */
    ServiceResult<ActFlashSale> getCurrEffectiveActFlashSale(Integer productId, Integer channel);

    //    /**
    //     * 取得指定日期的促销活动
    //     * @param strToDate
    //     * @return
    //     */
    //    ServiceResult<ActFlashSale> getByActDate(Date actDate);

    /**
     * 活动时间阶段列表
     * @param actFlashSaleId
     * @return
     */
    ServiceResult<List<ActFlashSaleStage>> getActFlashSaleStage(Integer actFlashSaleId);

    /**
     * 根据商品ID、渠道取得当天该商品参加的限时抢购活动、阶段、活动商品信息（上架的活动，如果有多个，只取最新的一个）
     * @param productId
     * @param channel
     * @return
     */
    ServiceResult<ActFlashSale> getTodayEffectiveActFlashSale(Integer productId, Integer channel);
}