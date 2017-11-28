package com.ejavashop.dao.shopm.read.pcindex;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.shopm.pcindex.PcIndexFloor;

@Repository
public interface PcIndexFloorReadDao {

    PcIndexFloor get(java.lang.Integer id);

    Integer getPcIndexFloorsCount(@Param("queryMap") Map<String, String> queryMap);

    List<PcIndexFloor> getPcIndexFloors(@Param("queryMap") Map<String, String> queryMap,
                                        @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 为展示页面取数据，数据order by `order_no`
     * 
     * @param isPreview 0:不是预览，取status为1的数据，1：预览，取status小于3的数据
     * @return
     */
    List<PcIndexFloor> getPcIndexFloorsForView(@Param("isPreview") Integer isPreview);
}