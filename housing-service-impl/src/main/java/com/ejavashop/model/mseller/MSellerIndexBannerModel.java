package com.ejavashop.model.mseller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.TimeUtil;
import com.ejavashop.dao.shopm.read.mseller.MSellerIndexBannerReadDao;
import com.ejavashop.dao.shopm.write.mseller.MSellerIndexBannerWriteDao;
import com.ejavashop.entity.shopm.MSellerIndexBanner;

@Component(value = "mSellerIndexBannerModel")
public class MSellerIndexBannerModel {

    @Resource
    private MSellerIndexBannerReadDao  mSellerIndexBannerReadDao;
    @Resource
    private MSellerIndexBannerWriteDao mSellerIndexBannerWriteDao;

    /**
     * 根据id取得移动端首页轮播图
     * @param  mSellerIndexBannerId
     * @return
     */
    public MSellerIndexBanner getMSellerIndexBannerById(Integer mSellerIndexBannerId) {
        return mSellerIndexBannerReadDao.get(mSellerIndexBannerId);
    }

    /**
     * 保存移动端首页轮播图
     * @param  mSellerIndexBanner
     * @return
     */
    public boolean saveMSellerIndexBanner(MSellerIndexBanner mSellerIndexBanner) {
        return mSellerIndexBannerWriteDao.insert(mSellerIndexBanner) > 0;
    }

    /**
     * 更新移动端首页轮播图
     * @param mSellerIndexBanner
     * @return
     */
    public boolean updateMSellerIndexBanner(MSellerIndexBanner mSellerIndexBanner) {
        return mSellerIndexBannerWriteDao.update(mSellerIndexBanner) > 0;
    }

    /**
     * 删除移动端首页轮播图
     * @param mSellerIndexBanner
     * @return
     */
    public boolean deleteMSellerIndexBanner(Integer mSellerIndexBannerId) {
        return mSellerIndexBannerWriteDao.delete(mSellerIndexBannerId) > 0;
    }

    public Integer getMSellerIndexBannersCount(Map<String, String> queryMap) {
        return mSellerIndexBannerReadDao.getMSellerIndexBannersCount(queryMap);
    }

    public List<MSellerIndexBanner> getMSellerIndexBanners(Map<String, String> queryMap,
                                                           Integer start, Integer size) {
        return mSellerIndexBannerReadDao.getMSellerIndexBanners(queryMap, start, size);
    }

    /**
     * 取得移动端首页轮播图<br>
     * <li>如果isPreview=true取所有轮播图
     * <li>如果isPreview=false取使用状态且当前时间在展示时间范围内的轮播图
     * 
     * @param sellerId
     * @param isPreview
     * @return
     */
    public List<MSellerIndexBanner> getMSellerIndexBannerForView(Integer sellerId,
                                                                 Boolean isPreview) {
        Map<String, String> queryMap = new HashMap<String, String>();
        if (queryMap != null && !isPreview) {
            queryMap.put("q_status", MSellerIndexBanner.STATUS_1 + "");
        }
        queryMap.put("q_time", TimeUtil.getDateTimeString(new Date()));
        queryMap.put("q_sellerId", sellerId.intValue() + "");
        return mSellerIndexBannerReadDao.getMSellerIndexBannersForView(queryMap);
    }
}