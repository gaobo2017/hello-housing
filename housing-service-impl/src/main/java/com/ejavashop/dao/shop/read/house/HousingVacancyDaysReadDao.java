package com.ejavashop.dao.shop.read.house;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.house.HousingVacancyDays;
@Repository
public interface HousingVacancyDaysReadDao {
    int deleteByPrimaryKey(Integer id);

    int insert(HousingVacancyDays record);

    int insertSelective(HousingVacancyDays record);

    HousingVacancyDays selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HousingVacancyDays record);

    int updateByPrimaryKey(HousingVacancyDays record);
}