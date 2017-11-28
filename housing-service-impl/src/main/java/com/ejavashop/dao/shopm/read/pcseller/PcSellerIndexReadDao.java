package com.ejavashop.dao.shopm.read.pcseller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.shopm.pcseller.PcSellerIndex;

@Repository
public interface PcSellerIndexReadDao {

    PcSellerIndex get(java.lang.Integer id);

    PcSellerIndex getBySellerId(java.lang.Integer sellerId);

    Integer getPcSellerIndexsCount(@Param("queryMap") Map<String, String> queryMap);

    List<PcSellerIndex> getPcSellerIndexs(@Param("queryMap") Map<String, String> queryMap,
                                          @Param("start") Integer start,
                                          @Param("size") Integer size);

}