package com.ejavashop.dao.promotion.read.group;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.group.ActGroupType;

@Repository
public interface ActGroupTypeReadDao {

    ActGroupType get(java.lang.Integer id);

    int count(@Param("param1") Map<String, String> queryMap);

    List<ActGroupType> getActGroupTypes(@Param("param1") Map<String, String> queryMap,
                                        @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 查询所有可用的团购分类
     * @return
     */
    List<ActGroupType> getAll();

}