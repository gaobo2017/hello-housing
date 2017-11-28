package com.ejavashop.dao.shopm.read.pcindex;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.shopm.pcindex.PcIndexFloorClass;

@Repository
public interface PcIndexFloorClassReadDao {

    PcIndexFloorClass get(java.lang.Integer id);

    Integer getPcIndexFloorClasssCount(@Param("queryMap") Map<String, String> queryMap);

    List<PcIndexFloorClass> getPcIndexFloorClasss(@Param("queryMap") Map<String, String> queryMap,
                                                  @Param("start") Integer start,
                                                  @Param("size") Integer size);

    //    /**
    //     * 为展示页面取数据，数据order by `order_no`
    //     * 
    //     * @param isPreview 0:不是预览，取status为1的数据，1：预览，取status小于3的数据
    //     * @return
    //     */
    //    List<PcIndexFloorClass> getPcIndexFloorClasssForView(@Param("isPreview") Integer isPreview);

    /**
     * 根据楼层ID取分类
     * @param floorId
     * @param isPreview 0:不是预览，取status为1的数据，1：预览，取status小于3的数据
     * @return
     */
    List<PcIndexFloorClass> getPcIndexFloorClasssForView(@Param("floorId") Integer floorId,
                                                         @Param("isPreview") Integer isPreview);
}