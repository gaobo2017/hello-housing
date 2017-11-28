package com.ejavashop.dao.shopm.read.pcindex;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.shopm.pcindex.PcIndexFloorPatch;

@Repository
public interface PcIndexFloorPatchReadDao {

    PcIndexFloorPatch get(java.lang.Integer id);

    Integer getPcIndexFloorPatchsCount(@Param("queryMap") Map<String, String> queryMap);

    List<PcIndexFloorPatch> getPcIndexFloorPatchs(@Param("queryMap") Map<String, String> queryMap,
                                                  @Param("start") Integer start,
                                                  @Param("size") Integer size);

    /**
     * 根据楼层ID取碎屑数据
     * @param floorId
     * @param isPreview 0:不是预览，取status为1的数据，1：预览，取status小于3的数据
     * @return
     */
    List<PcIndexFloorPatch> getPcIndexFloorPatchsForView(@Param("floorId") Integer floorId,
                                                         @Param("isPreview") Integer isPreview);
}