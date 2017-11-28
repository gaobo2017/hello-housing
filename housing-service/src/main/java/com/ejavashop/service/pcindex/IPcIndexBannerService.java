package com.ejavashop.service.pcindex;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.shopm.pcindex.PcIndexBanner;

public interface IPcIndexBannerService {

    /**
     * 根据id取得PC端首页轮播图
     * @param  pcIndexBannerId
     * @return
     */
    ServiceResult<PcIndexBanner> getPcIndexBannerById(Integer pcIndexBannerId);

    /**
     * 保存PC端首页轮播图
     * @param  pcIndexBanner
     * @return
     */
    ServiceResult<Boolean> savePcIndexBanner(PcIndexBanner pcIndexBanner);

    /**
     * 更新PC端首页轮播图
     * @param pcIndexBanner
     * @return
     */
    ServiceResult<Boolean> updatePcIndexBanner(PcIndexBanner pcIndexBanner);

    /**
     * 删除PC端首页轮播图
     * @param  pcIndexBanner
     * @return
     */
    ServiceResult<Boolean> deletePcIndexBanner(Integer pcIndexBannerId);

    /**
     * 根据条件取得PC端首页轮播图
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<PcIndexBanner>> getPcIndexBanners(Map<String, String> queryMap,
                                                         PagerInfo pager);

    /**
     * 取得PC端首页轮播图（当前时间在展示时间范围内的轮播图）<br>
     * <li>如果isPreview=true取使用和预使用状态的轮播图
     * <li>如果isPreview=false取使用状态的轮播图
     * 
     * @param isPreview
     * @return
     */
    ServiceResult<List<PcIndexBanner>> getPcIndexBannerForView(Boolean isPreview);

}