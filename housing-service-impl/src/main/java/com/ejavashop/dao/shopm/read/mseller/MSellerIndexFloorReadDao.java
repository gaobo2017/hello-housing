package com.ejavashop.dao.shopm.read.mseller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.shopm.MSellerIndexFloor;

@Repository
public interface MSellerIndexFloorReadDao {

    MSellerIndexFloor get(java.lang.Integer id);

    Integer getMSellerIndexFloorsCount(@Param("queryMap") Map<String, String> queryMap);

    List<MSellerIndexFloor> getMSellerIndexFloors(@Param("queryMap") Map<String, String> queryMap,
                                                  @Param("start") Integer start,
                                                  @Param("size") Integer size);

    /**
     * 为展示页面取数据，如果status不为空则加条件，为空则取所有数据，数据order by order_no
     * @param status
     * @return
     */
    List<MSellerIndexFloor> getMSellerIndexFloorsForView(@Param("sellerId") Integer sellerId,
                                                         @Param("status") String status);
}