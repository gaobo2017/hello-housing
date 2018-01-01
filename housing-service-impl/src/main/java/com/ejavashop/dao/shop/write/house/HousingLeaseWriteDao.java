package com.ejavashop.dao.shop.write.house;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.house.HousingLease;

@Repository
public interface HousingLeaseWriteDao {

    int deleteByPrimaryKey(Integer id);

    int deleteByHouseId(Integer houseId);

    int insert(HousingLease record);

    int insertSelective(HousingLease record);

    HousingLease selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HousingLease record);

    int updateByPrimaryKey(HousingLease record);

    /**
     * 分页查询
     * @param queryMap
     * @param start
     * @param size
     * @return
     */
    List<HousingLease> getHousingLeaseList(@Param("queryMap") Map<String, String> queryMap,
                                           @Param("start") Integer start,
                                           @Param("size") Integer size);

    /**
     * 根据条件查询count
     * @param queryMap
     * @return
     */
    Integer getHousingLeaseCount(@Param("queryMap") Map<String, String> queryMap);

    /**
     * 统计该income主表ID下的数据 
     * @param houesId
     * @return
     */

    HousingLease getHousingLeaseSum(Integer houesId);

    /**
     * 查询  房源和 该合同开始日期 的  上一条租赁记录    
     * @param houseId
     * @param leaseStartTime
     * @return HousingLease 
     */

    HousingLease getPreviousHousingLease(@Param("houseId") Integer houseId,
                                         @Param("leaseStartTime") Date leaseStartTime);

}
