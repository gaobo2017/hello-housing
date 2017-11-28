package com.ejavashop.dao.promotion.write.flash;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.flash.ActFlashSaleLog;

@Repository
public interface ActFlashSaleLogWriteDao {

    ActFlashSaleLog get(java.lang.Integer id);

    Integer insert(ActFlashSaleLog actFlashSaleLog);

    Integer update(ActFlashSaleLog actFlashSaleLog);

    Integer delete(java.lang.Integer id);

}