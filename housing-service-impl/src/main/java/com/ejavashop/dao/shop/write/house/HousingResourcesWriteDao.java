package com.ejavashop.dao.shop.write.house;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.house.HousingResources;

@Repository
public interface HousingResourcesWriteDao {

    /**
     * 根据条件查询count
     * @param queryMap
     * @return
     */
    Integer getHousingResourcesCount(@Param("queryMap") Map<String, String> queryMap);

    /**
     * 分页查询
     * @param queryMap
     * @param start
     * @param size
     * @return
     */
    List<HousingResources> getHousingResourcesList(@Param("queryMap") Map<String, String> queryMap,
                                                   @Param("start") Integer start,
                                                   @Param("size") Integer size);

    /**
     * 根据ID查询
     * @param Id
     * @return
     */
    HousingResources get(Integer id);

    HousingResources getHousingResourcesByCostId(Integer costId);

    /**
     * 根据ID查询
     * @param Id
     * @return
     */
    Integer update(HousingResources record);

    int deleteByPrimaryKey(Integer id);

    int insert(HousingResources record);

    int insertSelective(HousingResources record);

    HousingResources selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HousingResources record);

    int updateByPrimaryKey(HousingResources record);
}