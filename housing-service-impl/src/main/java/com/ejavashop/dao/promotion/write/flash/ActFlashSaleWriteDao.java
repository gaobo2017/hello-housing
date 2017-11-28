package com.ejavashop.dao.promotion.write.flash;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.flash.ActFlashSale;

@Repository
public interface ActFlashSaleWriteDao {

    ActFlashSale get(java.lang.Integer id);

    Integer insert(ActFlashSale actFlashSale);

    Integer update(ActFlashSale actFlashSale);

    Integer delete(java.lang.Integer id);

    /**
     * 只修改活动状态、修改者信息
     * @param actFlashSale
     * @return
     */
    Integer updateStatus(ActFlashSale actFlashSale);

}