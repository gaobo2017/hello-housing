package com.ejavashop.dao.promotion.read.single;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.single.ActSingle;

@Repository
public interface ActSingleReadDao {

    ActSingle get(java.lang.Integer id);

    Integer getActSinglesCount(@Param("queryMap") Map<String, String> queryMap);

    List<ActSingle> getActSingles(@Param("queryMap") Map<String, String> queryMap,
                                  @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 根据商家ID和渠道取得单品立减活动（当前时间上架的活动，如果有多个，只取最新的一个）<br>
     * productIdStr是商品ID前后加,后的字符串，如：,1,
     * 
     * @param sellerId
     * @param channel
     * @param productIdStr
     * @return
     */
    ActSingle getEffectiveActSingle(@Param("sellerId") Integer sellerId,
                                    @Param("channel") Integer channel,
                                    @Param("productIdStr") String productIdStr);
}