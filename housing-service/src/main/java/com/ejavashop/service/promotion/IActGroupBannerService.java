package com.ejavashop.service.promotion;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.promotion.group.ActGroupBanner;

public interface IActGroupBannerService {

    /**
     * 根据id取得团购首页轮播图
     * @param  pcIndexBannerId
     * @return
     */
    ServiceResult<ActGroupBanner> getActGroupBannerById(Integer pcIndexBannerId);

    /**
     * 保存团购首页轮播图
     * @param  pcIndexBanner
     * @return
     */
    ServiceResult<Boolean> saveActGroupBanner(ActGroupBanner pcIndexBanner);

    /**
     * 更新团购首页轮播图
     * @param pcIndexBanner
     * @return
     */
    ServiceResult<Boolean> updateActGroupBanner(ActGroupBanner pcIndexBanner);

    /**
     * 删除团购首页轮播图
     * @param  pcIndexBanner
     * @return
     */
    ServiceResult<Boolean> deleteActGroupBanner(Integer pcIndexBannerId);

    /**
     * 根据条件取得团购首页轮播图
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<ActGroupBanner>> getActGroupBanners(Map<String, String> queryMap,
                                                           PagerInfo pager);

    /**
     * 查询团购首页轮播图
     * @param pcMobilePc
     * @param channel2
     * @return
     */
    ServiceResult<List<ActGroupBanner>> getActGroupBannersPcMobile(int pcMobile);

}