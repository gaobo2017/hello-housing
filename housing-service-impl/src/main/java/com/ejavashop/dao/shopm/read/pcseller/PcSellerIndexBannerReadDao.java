package com.ejavashop.dao.shopm.read.pcseller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.shopm.pcseller.PcSellerIndexBanner;

@Repository
public interface PcSellerIndexBannerReadDao {

    PcSellerIndexBanner get(java.lang.Integer id);

    Integer getPcSellerIndexBannersCount(@Param("queryMap") Map<String, String> queryMap);

    List<PcSellerIndexBanner> getPcSellerIndexBanners(@Param("queryMap") Map<String, String> queryMap,
                                                      @Param("start") Integer start,
                                                      @Param("size") Integer size);

    /**
     * 为展示页面取数据，数据order by `order_no`，默认取当前时间在展示时间之内的数据
     * 
     * @param sellerId 商家ID
     * @param isPreview 0:不是预览，取status为1的数据，1：预览，取status小于3的数据
     * @return
     */
    List<PcSellerIndexBanner> getPcSellerIndexBannersForView(@Param("sellerId") Integer sellerId,
                                                             @Param("isPreview") Integer isPreview);
}