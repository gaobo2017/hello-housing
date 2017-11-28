package com.ejavashop.dao.promotion.read.full;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.full.ActFull;

@Repository
public interface ActFullReadDao {

    ActFull get(java.lang.Integer id);

    Integer getActFullsCount(@Param("queryMap") Map<String, String> queryMap);

    List<ActFull> getActFulls(@Param("queryMap") Map<String, String> queryMap,
                              @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 根据商家ID、渠道取得满减活动（当前时间上架的活动，如果有多个，只取最新的一个）<br>
     * 
     * @return
     */
    ActFull getEffectiveActFull(@Param("sellerId") Integer sellerId,
                                @Param("channel") Integer channel);
}