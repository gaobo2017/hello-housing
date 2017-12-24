package com.ejavashop.dao.shop.write.house;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.house.HousingCost;

@Repository
public interface HousingCostWriteDao {

    /**
     * 根据条件查询count
     * @param queryMap
     * @return
     */
    Integer getHousingCostCount(@Param("queryMap") Map<String, String> queryMap);

    /**
     * 分页查询
     * @param queryMap
     * @param start
     * @param size
     * @return
     */
    List<HousingCost> getHousingCostList(@Param("queryMap") Map<String, String> queryMap,
                                         @Param("start") Integer start,
                                         @Param("size") Integer size);

    int deleteByPrimaryKey(Integer id);

    int insert(HousingCost record);

    int insertSelective(HousingCost record);

    HousingCost selectByPrimaryKey(Integer id);

    HousingCost selectByHouseId(Integer houseId);

    int updateByPrimaryKeySelective(HousingCost record);

    int updateByPrimaryKey(HousingCost record);
}