package com.ejavashop.dao.shop.write.house;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.house.HousingCost;
@Repository
public interface HousingCostWriteDao {
    int deleteByPrimaryKey(Integer id);

    int insert(HousingCost record);

    int insertSelective(HousingCost record);

    HousingCost selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HousingCost record);

    int updateByPrimaryKey(HousingCost record);
}