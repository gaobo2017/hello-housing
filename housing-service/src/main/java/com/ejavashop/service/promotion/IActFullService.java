package com.ejavashop.service.promotion;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.promotion.full.ActFull;

public interface IActFullService {

    /**
     * 根据id取得满减活动
     * @param  actFullId
     * @return
     */
    ServiceResult<ActFull> getActFullById(Integer actFullId);

    /**
     * 保存满减活动
     * @param  actFull
     * @return
     */
    ServiceResult<Boolean> saveActFull(ActFull actFull);

    /**
     * 更新满减活动
     * @param actFull
     * @return
     */
    ServiceResult<Boolean> updateActFull(ActFull actFull);

    /**
     * 更新满减活动状态（只修改活动状态、审核意见、修改者信息）
     * @param actFull
     * @return
     */
    ServiceResult<Boolean> updateActFullStatus(ActFull actFull);

    /**
     * 删除满减活动
     * 
     * @param actFullId
     * @param userId 删除人ID
     * @param userName 删除人名称
     * @return
     */
    ServiceResult<Boolean> deleteActFull(Integer actFullId, Integer userId, String userName);

    /**
     * 根据条件取得满减活动
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<ActFull>> getActFulls(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 根据商家ID和渠道取得满减活动（当前时间有效的活动，如果有多个，只取最新的一个）
     * 
     * @param sellerId
     * @param channel 渠道
     * @return
     */
    ServiceResult<ActFull> getEffectiveActFull(Integer sellerId, Integer channel);

}