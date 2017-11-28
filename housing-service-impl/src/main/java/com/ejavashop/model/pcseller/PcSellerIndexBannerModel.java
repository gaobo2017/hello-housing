package com.ejavashop.model.pcseller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.dao.shopm.read.pcseller.PcSellerIndexBannerReadDao;
import com.ejavashop.dao.shopm.write.pcseller.PcSellerIndexBannerWriteDao;
import com.ejavashop.entity.shopm.pcseller.PcSellerIndexBanner;

@Component(value = "pcSellerIndexBannerModel")
public class PcSellerIndexBannerModel {

    @Resource
    private PcSellerIndexBannerReadDao  pcSellerIndexBannerReadDao;
    @Resource
    private PcSellerIndexBannerWriteDao pcSellerIndexBannerWriteDao;

    /**
     * 根据id取得PC端商家首页轮播图
     * @param  pcSellerIndexBannerId
     * @return
     */
    public PcSellerIndexBanner getPcSellerIndexBannerById(Integer pcSellerIndexBannerId) {
        return pcSellerIndexBannerReadDao.get(pcSellerIndexBannerId);
    }

    /**
     * 保存PC端商家首页轮播图
     * @param  pcSellerIndexBanner
     * @return
     */
    public boolean savePcSellerIndexBanner(PcSellerIndexBanner pcSellerIndexBanner) {
        return pcSellerIndexBannerWriteDao.insert(pcSellerIndexBanner) > 0;
    }

    /**
     * 更新PC端商家首页轮播图
     * @param pcSellerIndexBanner
     * @return
     */
    public boolean updatePcSellerIndexBanner(PcSellerIndexBanner pcSellerIndexBanner) {
        return pcSellerIndexBannerWriteDao.update(pcSellerIndexBanner) > 0;
    }

    /**
     * 删除PC端商家首页轮播图
     * @param pcSellerIndexBanner
     * @return
     */
    public boolean deletePcSellerIndexBanner(Integer pcSellerIndexBannerId) {
        return pcSellerIndexBannerWriteDao.delete(pcSellerIndexBannerId) > 0;
    }

    public Integer getPcSellerIndexBannersCount(Map<String, String> queryMap) {
        return pcSellerIndexBannerReadDao.getPcSellerIndexBannersCount(queryMap);
    }

    public List<PcSellerIndexBanner> getPcSellerIndexBanners(Map<String, String> queryMap,
                                                             Integer start, Integer size) {
        return pcSellerIndexBannerReadDao.getPcSellerIndexBanners(queryMap, start, size);
    }

    /**
     * 取得PC端商家首页轮播图（当前时间在展示时间范围内的轮播图）<br>
     * <li>如果isPreview=true取使用和预使用状态的轮播图
     * <li>如果isPreview=false取使用状态的轮播图
     * 
     * @param sellerId
     * @param isPreview
     * @return
     */
    public List<PcSellerIndexBanner> getPcSellerIndexBannerForView(Integer sellerId,
                                                                   Boolean isPreview) {
        Integer isPreviewInt = 0;
        if (isPreview != null && isPreview) {
            isPreviewInt = 1;
        }
        return pcSellerIndexBannerReadDao.getPcSellerIndexBannersForView(sellerId, isPreviewInt);
    }
}