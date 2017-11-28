package com.ejavashop.dao.promotion.read.coupon;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.coupon.Coupon;

@Repository
public interface CouponReadDao {

    Coupon get(java.lang.Integer id);

    Integer getCouponsCount(@Param("queryMap") Map<String, String> queryMap);

    List<Coupon> getCoupons(@Param("queryMap") Map<String, String> queryMap,
                            @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 根据商家ID、渠道取得优惠券（当前时间在发放时间内、上架、未超发放限额的优惠券）
     * 
     * @return
     */
    List<Coupon> getEffectiveCoupon(@Param("sellerId") Integer sellerId,
                                    @Param("channel") Integer channel);
}