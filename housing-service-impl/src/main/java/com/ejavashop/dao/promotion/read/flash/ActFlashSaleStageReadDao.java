package com.ejavashop.dao.promotion.read.flash;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.flash.ActFlashSaleStage;

@Repository
public interface ActFlashSaleStageReadDao {

    ActFlashSaleStage get(java.lang.Integer id);

    List<ActFlashSaleStage> getByActFlashSaleId(java.lang.Integer actFlashSaleId);

    /**
     * 根据活动ID、时间点（小时）获取活动阶段
     * @param actFlashSaleId 活动ID
     * @param stageTime 时间点
     * @return
     */
    ActFlashSaleStage getStageByTime(@Param("actFlashSaleId") Integer actFlashSaleId,
                                     @Param("stageTime") Integer stageTime);
}