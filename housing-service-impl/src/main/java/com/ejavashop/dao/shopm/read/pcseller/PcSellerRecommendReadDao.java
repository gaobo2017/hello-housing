package com.ejavashop.dao.shopm.read.pcseller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.shopm.pcseller.PcSellerRecommend;

@Repository
public interface PcSellerRecommendReadDao {

    PcSellerRecommend get(java.lang.Integer id);

    Integer getPcSellerRecommendsCount(@Param("queryMap") Map<String, String> queryMap);

    List<PcSellerRecommend> getPcSellerRecommends(@Param("queryMap") Map<String, String> queryMap,
                                                  @Param("start") Integer start,
                                                  @Param("size") Integer size);

    /**
     * 为展示页面取数据，数据order by `order_no`
     * 
     * @param sellerId 商家ID
     * @param isPreview 0:不是预览，取status为1的数据，1：预览，取status小于3的数据
     * @return
     */
    List<PcSellerRecommend> getPcSellerRecommendsForView(@Param("sellerId") Integer sellerId,
                                                         @Param("isPreview") Integer isPreview);
}