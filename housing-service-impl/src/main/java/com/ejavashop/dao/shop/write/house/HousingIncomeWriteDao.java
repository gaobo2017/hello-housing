package com.ejavashop.dao.shop.write.house;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.house.HousingIncome;
@Repository
public interface HousingIncomeWriteDao {
    int deleteByPrimaryKey(Integer id);

    int insert(HousingIncome record);

    int insertSelective(HousingIncome record);

    HousingIncome selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HousingIncome record);

    int updateByPrimaryKey(HousingIncome record);
}