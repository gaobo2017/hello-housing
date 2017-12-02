package com.ejavashop.dao.shop.write.house;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.house.HousingReminder;
@Repository
public interface HousingReminderWriteDao {
    int deleteByPrimaryKey(Integer id);

    int insert(HousingReminder record);

    int insertSelective(HousingReminder record);

    HousingReminder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HousingReminder record);

    int updateByPrimaryKey(HousingReminder record);
}