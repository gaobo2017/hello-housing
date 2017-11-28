package com.ejavashop.service.pcseller;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.shopm.pcseller.PcSellerIndexBanner;

public interface IPcSellerIndexBannerService {

    /**
     * 根据id取得PC端商家首页轮播图
     * @param  pcSellerIndexBannerId
     * @return
     */
    ServiceResult<PcSellerIndexBanner> getPcSellerIndexBannerById(Integer pcSellerIndexBannerId);

    /**
     * 保存PC端商家首页轮播图
     * @param  pcSellerIndexBanner
     * @return
     */
    ServiceResult<Boolean> savePcSellerIndexBanner(PcSellerIndexBanner pcSellerIndexBanner);

    /**
     * 更新PC端商家首页轮播图
     * @param pcSellerIndexBanner
     * @return
     */
    ServiceResult<Boolean> updatePcSellerIndexBanner(PcSellerIndexBanner pcSellerIndexBanner);

    /**
     * 删除PC端商家首页轮播图
     * @param  pcSellerIndexBanner
     * @return
     */
    ServiceResult<Boolean> deletePcSellerIndexBanner(Integer pcSellerIndexBannerId);

    /**
     * 根据条件取得PC端商家首页轮播图
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<PcSellerIndexBanner>> getPcSellerIndexBanners(Map<String, String> queryMap,
                                                                     PagerInfo pager);

    /**
     * 取得PC端商家首页轮播图（当前时间在展示时间范围内的轮播图）<br>
     * <li>如果isPreview=true取使用和预使用状态的轮播图
     * <li>如果isPreview=false取使用状态的轮播图
     * 
     * @param sellerId
     * @param isPreview
     * @return
     */
    ServiceResult<List<PcSellerIndexBanner>> getPcSellerIndexBannerForView(Integer sellerId,
                                                                           Boolean isPreview);

}