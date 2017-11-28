package com.ejavashop.dao.shopm.read.mindex;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.shopm.MIndexFloorData;

@Repository
public interface MIndexFloorDataReadDao {

    MIndexFloorData get(java.lang.Integer id);

    Integer getMIndexFloorDatasCount(@Param("queryMap") Map<String, String> queryMap);

    List<MIndexFloorData> getMIndexFloorDatas(@Param("queryMap") Map<String, String> queryMap,
                                              @Param("start") Integer start,
                                              @Param("size") Integer size);

    /**
     * 根据楼层ID取得首页楼层数据表
     * @param mIndexFloorId
     * @return
     */
    List<MIndexFloorData> getMIndexFloorDatasByFloorId(Integer mIndexFloorId);

}