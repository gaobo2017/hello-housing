package com.ejavashop.dao.shop.read.house;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.house.HousingCostDetail;
@Repository
public interface HousingCostDetailReadDao {
    int deleteByPrimaryKey(Integer id);

    int insert(HousingCostDetail record);

    int insertSelective(HousingCostDetail record);

    HousingCostDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HousingCostDetail record);

    int updateByPrimaryKey(HousingCostDetail record);
}