package com.ejavashop.dao.promotion.read.flash;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.flash.ActFlashSale;
import com.ejavashop.entity.promotion.flash.ActFlashSaleStage;

@Repository
public interface ActFlashSaleReadDao {

    ActFlashSale get(java.lang.Integer id);

    Integer getActFlashSalesCount(@Param("queryMap") Map<String, String> queryMap);

    List<ActFlashSale> getActFlashSales(@Param("queryMap") Map<String, String> queryMap,
                                        @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 根据活动日期、渠道取得限时抢购活动（上架的活动，如果有多个，只取最新的一个）
     * @param actDate 活动日期
     * @param channel 渠道
     * @return
     */
    ActFlashSale getEffectiveActFlashSale(@Param("actDate") String actDate,
                                          @Param("channel") Integer channel);

    int countByActDateAndStatus(@Param("actDate") Date actDate, @Param("status") int status);

    //    /**
    //     * 取得指定日期的促销活动
    //     * @param actDate
    //     * @return
    //     */
    //    ActFlashSale getByActDate(@Param("actDate") Date actDate, @Param("status") int status);

    /**
     * 以活动id获取活动阶段
     * @param actFlashSaleId
     * @return
     */
    List<ActFlashSaleStage> getActFlashSaleStage(Integer actFlashSaleId);
}