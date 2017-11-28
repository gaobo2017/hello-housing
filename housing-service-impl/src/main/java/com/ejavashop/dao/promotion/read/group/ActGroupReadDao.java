package com.ejavashop.dao.promotion.read.group;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.group.ActGroup;

@Repository
public interface ActGroupReadDao {

    ActGroup get(java.lang.Integer id);

    int count(@Param("param1") Map<String, String> queryMap);

    List<ActGroup> getActGroups(@Param("param1") Map<String, String> queryMap,
                                @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 统计团购前台查看
     * @param type
     * @param channel
     * @return
     */
    int countFront(@Param("type") int type, @Param("channel") int channel);

    /**
     * 团购前台查看列表页
     * @param type
     * @param channel
     * @param start
     * @param size
     * @return
     */
    List<ActGroup> getActGroupsFront(@Param("type") int type, @Param("channel") int channel,
                                     @Param("start") int start, @Param("size") int size);

    /**
     * 根据类型查询topNum条团购
     * @param type
     * @param topNum
     * @return
     */
    List<ActGroup> getActGroupsByType(@Param("type") Integer type, @Param("topNum") Integer topNum);
}