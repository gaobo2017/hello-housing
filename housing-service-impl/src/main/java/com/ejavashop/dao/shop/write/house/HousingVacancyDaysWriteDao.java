package com.ejavashop.dao.shop.write.house;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.house.HousingVacancyDays;

@Repository
public interface HousingVacancyDaysWriteDao {
    int deleteByPrimaryKey(Integer id);

    int deleteByHouseId(Integer houseId);

    int insert(HousingVacancyDays record);

    int insertSelective(HousingVacancyDays record);

    HousingVacancyDays selectByPrimaryKey(Integer id);

    HousingVacancyDays selectByLeaseId(Integer leaseId);

    HousingVacancyDays getHousingVacancDaysSumByHouseId(Integer houseId);

    int updateByPrimaryKeySelective(HousingVacancyDays record);

    int updateByPrimaryKey(HousingVacancyDays record);
}