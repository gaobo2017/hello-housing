package com.ejavashop.dao.shopm.read.pcindex;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.shopm.pcindex.PcIndexFloorData;

@Repository
public interface PcIndexFloorDataReadDao {

    PcIndexFloorData get(java.lang.Integer id);

    Integer getPcIndexFloorDatasCount(@Param("queryMap") Map<String, String> queryMap);

    List<PcIndexFloorData> getPcIndexFloorDatas(@Param("queryMap") Map<String, String> queryMap,
                                                @Param("start") Integer start,
                                                @Param("size") Integer size);

    /**
     * 根据楼层分类ID取分类
     * @param floorClassId
     * @return
     */
    List<PcIndexFloorData> getPcIndexFloorDatasForView(@Param("floorClassId") Integer floorClassId);
}