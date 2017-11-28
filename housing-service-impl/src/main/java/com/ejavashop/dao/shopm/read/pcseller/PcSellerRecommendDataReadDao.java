package com.ejavashop.dao.shopm.read.pcseller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.shopm.pcseller.PcSellerRecommendData;

@Repository
public interface PcSellerRecommendDataReadDao {

    PcSellerRecommendData get(java.lang.Integer id);

    Integer getPcSellerRecommendDatasCount(@Param("queryMap") Map<String, String> queryMap);

    List<PcSellerRecommendData> getPcSellerRecommendDatas(@Param("queryMap") Map<String, String> queryMap,
                                                          @Param("start") Integer start,
                                                          @Param("size") Integer size);

    /**
     * 为展示页面取数据，数据order by `order_no`
     * 
     * @param recommendId 推荐类型ID
     * @return
     */
    List<PcSellerRecommendData> getPcSellerRecommendDatasForView(@Param("recommendId") Integer recommendId);
}