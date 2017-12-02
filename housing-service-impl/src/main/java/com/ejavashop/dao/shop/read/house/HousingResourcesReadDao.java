package com.ejavashop.dao.shop.read.house;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.house.HousingResources;
@Repository
public interface HousingResourcesReadDao {
    int deleteByPrimaryKey(Integer id);

    int insert(HousingResources record);

    int insertSelective(HousingResources record);

    HousingResources selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HousingResources record);

    int updateByPrimaryKey(HousingResources record);
}