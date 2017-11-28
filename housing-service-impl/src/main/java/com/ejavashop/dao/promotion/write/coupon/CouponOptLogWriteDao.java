package com.ejavashop.dao.promotion.write.coupon;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.coupon.CouponOptLog;

@Repository
public interface CouponOptLogWriteDao {

    CouponOptLog get(java.lang.Integer id);

    Integer insert(CouponOptLog couponOptLog);

    Integer update(CouponOptLog couponOptLog);

    /**
     * 批量新增优惠券用户操作日志
     * @param couponOptLogList
     * @return
     */
    Integer batchInsertCouponOptLog(List<CouponOptLog> couponOptLogList);
}