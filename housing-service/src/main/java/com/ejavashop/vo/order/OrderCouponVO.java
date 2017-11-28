package com.ejavashop.vo.order;

import java.io.Serializable;

import com.ejavashop.entity.promotion.coupon.Coupon;
import com.ejavashop.entity.promotion.coupon.CouponUser;

public class OrderCouponVO implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -1861995200241290043L;

    /** 使用的优惠券类型：0、没有使用，1、已绑定优惠券（不需要验证密码），2、未绑定优惠券（验证密码） */
    public static final int   COUPON_TYPE_0    = 0;
    /** 使用的优惠券类型：0、没有使用，1、已绑定优惠券（不需要验证密码），2、未绑定优惠券（验证密码） */
    public static final int   COUPON_TYPE_1    = 1;
    /** 使用的优惠券类型：0、没有使用，1、已绑定优惠券（不需要验证密码），2、未绑定优惠券（验证密码） */
    public static final int   COUPON_TYPE_2    = 2;

    private Integer           sellerId;                                // 商家ID，记录当前优惠券所属商家
    private Integer           couponType;                              // 使用的优惠券类型：0、没有使用，1、已绑定优惠券（不需要验证密码），2、未绑定优惠券（验证密码）
    private String            couponSn;                                // 优惠券序列号
    private String            couponPassword;                          // 优惠券密码

    private Coupon            coupon;                                  // 优惠券信息
    private CouponUser        couponUser;                              // 优惠券用户信息

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public String getCouponSn() {
        return couponSn;
    }

    public void setCouponSn(String couponSn) {
        this.couponSn = couponSn;
    }

    public String getCouponPassword() {
        return couponPassword;
    }

    public void setCouponPassword(String couponPassword) {
        this.couponPassword = couponPassword;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public CouponUser getCouponUser() {
        return couponUser;
    }

    public void setCouponUser(CouponUser couponUser) {
        this.couponUser = couponUser;
    }

}
