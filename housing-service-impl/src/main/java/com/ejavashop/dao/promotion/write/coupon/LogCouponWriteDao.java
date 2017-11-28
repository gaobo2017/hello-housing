package com.ejavashop.dao.promotion.write.coupon;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.coupon.LogCoupon;

@Repository
public interface LogCouponWriteDao {

    // LogCoupon get(java.lang.Integer id);

    Integer insert(LogCoupon logCoupon);

    // Integer update(LogCoupon logCoupon);
}