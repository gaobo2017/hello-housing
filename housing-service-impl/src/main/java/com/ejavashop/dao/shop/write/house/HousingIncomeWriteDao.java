package com.ejavashop.dao.shop.write.house;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.house.HousingIncome;

@Repository
public interface HousingIncomeWriteDao {

    int deleteByPrimaryKey(Integer id);

    int insert(HousingIncome record);

    int insertSelective(HousingIncome record);

    HousingIncome selectByPrimaryKey(Integer id);

    HousingIncome selectByHouseId(Integer id);

    int updateByPrimaryKeySelective(HousingIncome record);

    int updateByPrimaryKey(HousingIncome record);

    /**
     * 分页查询
     * @param queryMap
     * @param start
     * @param size
     * @return
     */
    List<HousingIncome> getHousingIncomeList(@Param("queryMap") Map<String, String> queryMap,
                                             @Param("start") Integer start,
                                             @Param("size") Integer size);

    /**
     * 根据条件查询count
     * @param queryMap
     * @return
     */
    Integer getHousingIncomeCount(@Param("queryMap") Map<String, String> queryMap);

}