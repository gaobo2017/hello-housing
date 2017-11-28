package com.ejavashop.dao.promotion.write.flash;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.flash.LogActFlashSaleProduct;

@Repository
public interface LogActFlashSaleProductWriteDao {

    //    LogActFlashSaleProduct get(java.lang.Integer id);

    Integer insert(LogActFlashSaleProduct logActFlashSaleProduct);

    //    Integer update(LogActFlashSaleProduct logActFlashSaleProduct);

    //    Integer delete(java.lang.Integer id);

}