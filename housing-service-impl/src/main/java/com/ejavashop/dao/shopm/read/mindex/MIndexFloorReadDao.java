package com.ejavashop.dao.shopm.read.mindex;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.shopm.MIndexFloor;

@Repository
public interface MIndexFloorReadDao {

    MIndexFloor get(java.lang.Integer id);

    Integer getMIndexFloorsCount(@Param("queryMap") Map<String, String> queryMap);

    List<MIndexFloor> getMIndexFloors(@Param("queryMap") Map<String, String> queryMap,
                                      @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 为展示页面取数据，如果status不为空则加条件，为空则取所有数据，数据order by order_no
     * @param status
     * @return
     */
    List<MIndexFloor> getMIndexFloorsForView(@Param("status") String status);
}