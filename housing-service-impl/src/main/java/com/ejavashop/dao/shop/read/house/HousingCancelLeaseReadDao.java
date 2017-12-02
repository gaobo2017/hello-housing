package com.ejavashop.dao.shop.read.house;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.house.HousingCancelLease;


@Repository
public interface HousingCancelLeaseReadDao {
    int deleteByPrimaryKey(Integer id);

    int insert(HousingCancelLease record);

    int insertSelective(HousingCancelLease record);

    HousingCancelLease selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HousingCancelLease record);

    int updateByPrimaryKey(HousingCancelLease record);
}