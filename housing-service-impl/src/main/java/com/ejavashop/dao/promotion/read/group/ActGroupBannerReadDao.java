package com.ejavashop.dao.promotion.read.group;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.group.ActGroupBanner;

@Repository
public interface ActGroupBannerReadDao {

    ActGroupBanner get(java.lang.Integer id);

    Integer getActGroupBannersCount(@Param("queryMap") Map<String, String> queryMap);

    List<ActGroupBanner> getActGroupBanners(@Param("queryMap") Map<String, String> queryMap,
                                            @Param("start") Integer start,
                                            @Param("size") Integer size);

    /**
     * 查询团购首页正在使用的轮播图
     * @param pcMobile
     * @return
     */
    List<ActGroupBanner> getActGroupBannersPcMobile(@Param("pcMobile") int pcMobile);

}