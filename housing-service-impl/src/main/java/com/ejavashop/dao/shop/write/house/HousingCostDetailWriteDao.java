package com.ejavashop.dao.shop.write.house;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.house.HousingCost;
import com.ejavashop.entity.house.HousingCostDetail;

@Repository
public interface HousingCostDetailWriteDao {
    
	int deleteByPrimaryKey(Integer id);

    int insert(HousingCostDetail record);

    int insertSelective(HousingCostDetail record);

    HousingCostDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HousingCostDetail record);

    int updateByPrimaryKey(HousingCostDetail record);

    /**
     * 分页查询
     * @param queryMap
     * @param start
     * @param size
     * @return
     */
    List<HousingCostDetail> getHousingCostDetailList(@Param("queryMap") Map<String, String> queryMap,
                                                     @Param("start") Integer start,
                                                     @Param("size") Integer size);
    /**
     * 统计该成本主表ID下的数据 
     * @param costId
     * @return
     */
    
    List<HousingCostDetail> getHousingCostDetailSum(Integer costId);

    /**
     * 根据条件查询count
     * @param queryMap
     * @return
     */
    Integer getHousingCostDetailCount(@Param("queryMap") Map<String, String> queryMap);

}