package com.ejavashop.dao.shop.read.house;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.house.HousingLease;
@Repository
public interface HousingLeaseReadDao {
    int deleteByPrimaryKey(Integer id);

    int insert(HousingLease record);

    int insertSelective(HousingLease record);

    HousingLease selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HousingLease record);

    int updateByPrimaryKey(HousingLease record);
}