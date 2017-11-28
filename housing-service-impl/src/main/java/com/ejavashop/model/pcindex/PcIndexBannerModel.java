package com.ejavashop.model.pcindex;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.dao.shopm.read.pcindex.PcIndexBannerReadDao;
import com.ejavashop.dao.shopm.write.pcindex.PcIndexBannerWriteDao;
import com.ejavashop.entity.shopm.pcindex.PcIndexBanner;

@Component(value = "pcIndexBannerModel")
public class PcIndexBannerModel {

    @Resource
    private PcIndexBannerReadDao  pcIndexBannerReadDao;
    @Resource
    private PcIndexBannerWriteDao pcIndexBannerWriteDao;

    /**
     * 根据id取得PC端首页轮播图
     * @param  pcIndexBannerId
     * @return
     */
    public PcIndexBanner getPcIndexBannerById(Integer pcIndexBannerId) {
        return pcIndexBannerReadDao.get(pcIndexBannerId);
    }

    /**
     * 保存PC端首页轮播图
     * @param  pcIndexBanner
     * @return
     */
    public boolean savePcIndexBanner(PcIndexBanner pcIndexBanner) {
        return pcIndexBannerWriteDao.insert(pcIndexBanner) > 0;
    }

    /**
     * 更新PC端首页轮播图
     * @param pcIndexBanner
     * @return
     */
    public boolean updatePcIndexBanner(PcIndexBanner pcIndexBanner) {
        return pcIndexBannerWriteDao.update(pcIndexBanner) > 0;
    }

    /**
     * 删除PC端首页轮播图
     * @param pcIndexBanner
     * @return
     */
    public boolean deletePcIndexBanner(Integer pcIndexBannerId) {
        return pcIndexBannerWriteDao.delete(pcIndexBannerId) > 0;
    }

    public Integer getPcIndexBannersCount(Map<String, String> queryMap) {
        return pcIndexBannerReadDao.getPcIndexBannersCount(queryMap);
    }

    public List<PcIndexBanner> getPcIndexBanners(Map<String, String> queryMap, Integer start,
                                                 Integer size) {
        return pcIndexBannerReadDao.getPcIndexBanners(queryMap, start, size);
    }

    /**
     * 取得PC端首页轮播图（当前时间在展示时间范围内的轮播图）<br>
     * <li>如果isPreview=true取使用和预使用状态的轮播图
     * <li>如果isPreview=false取使用状态的轮播图
     * 
     * @param isPreview
     * @return
     */
    public List<PcIndexBanner> getPcIndexBannerForView(Boolean isPreview) {
        Integer isPreviewInt = 0;
        if (isPreview != null && isPreview) {
            isPreviewInt = 1;
        }
        return pcIndexBannerReadDao.getPcIndexBannersForView(isPreviewInt);
    }
}