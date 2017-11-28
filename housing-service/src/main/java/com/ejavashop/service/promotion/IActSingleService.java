package com.ejavashop.service.promotion;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.promotion.single.ActSingle;

public interface IActSingleService {

    /**
     * 根据id取得单品立减活动
     * @param  actSingleId
     * @return
     */
    ServiceResult<ActSingle> getActSingleById(Integer actSingleId);

    /**
     * 保存单品立减活动
     * @param  actSingle
     * @return
     */
    ServiceResult<Boolean> saveActSingle(ActSingle actSingle);

    /**
     * 更新单品立减活动
     * @param actSingle
     * @return
     */
    ServiceResult<Boolean> updateActSingle(ActSingle actSingle);

    /**
     * 更新单品立减活动状态（只修改活动状态、审核意见、修改者信息）
     * @param actSingle
     * @return
     */
    ServiceResult<Boolean> updateActSingleStatus(ActSingle actSingle);

    /**
     * 删除单品立减活动
     * @param actSingleId
     * @param userId 删除人ID
     * @param userName 删除人名称
     * @return
     */
    ServiceResult<Boolean> deleteActSingle(Integer actSingleId, Integer userId, String userName);

    /**
     * 根据条件取得单品立减活动
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<ActSingle>> getActSingles(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 根据商家ID、渠道、商品ID取得单品立减活动（当前时间有效的活动，如果有多个，只取最新的一个）
     * 
     * @param sellerId
     * @param channel
     * @param productId
     * @return
     */
    ServiceResult<ActSingle> getEffectiveActSingle(Integer sellerId, Integer channel,
                                                   Integer productId);

}