package com.ejavashop.dao.promotion.write.flash;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.flash.LogActFlashSale;

@Repository
public interface LogActFlashSaleWriteDao {

    //    LogActFlashSale get(java.lang.Integer id);

    Integer insert(LogActFlashSale logActFlashSale);

    //    Integer update(LogActFlashSale logActFlashSale);

    //    Integer delete(java.lang.Integer id);

}