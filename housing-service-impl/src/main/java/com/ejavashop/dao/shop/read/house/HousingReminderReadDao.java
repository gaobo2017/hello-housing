package com.ejavashop.dao.shop.read.house;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.house.HousingReminder;
@Repository
public interface HousingReminderReadDao {
    int deleteByPrimaryKey(Integer id);

    int insert(HousingReminder record);

    int insertSelective(HousingReminder record);

    HousingReminder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HousingReminder record);

    int updateByPrimaryKey(HousingReminder record);
}