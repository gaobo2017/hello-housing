package com.ejavashop.dao.promotion.write.coupon;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.coupon.CouponUser;

@Repository
public interface CouponUserWriteDao {

    CouponUser get(java.lang.Integer id);

    Integer insert(CouponUser couponUser);

    Integer update(CouponUser couponUser);

    Integer delete(java.lang.Integer id);

    /**
     * 用户下单时修改优惠券可使用次数（can_use = can_use - 1）<br>
     * 并且设定member_id=memberId（未绑定的情况使用时默认绑定）<br>
     * 设定使用时间为当前时间
     * @param memberId
     * @param couponUserId
     * @param orderId 订单ID
     * @return
     */
    Integer updateCanUse(@Param("memberId") Integer memberId,
                         @Param("couponUserId") Integer couponUserId,
                         @Param("orderId") Integer orderId);

    /**
     * 根据用户ID和优惠券ID获取优惠券用户表的数量
     * @param memberId
     * @param couponId
     * @return
     */
    Integer getNumByMemberIdAndCouponId(@Param("memberId") Integer memberId,
                                        @Param("couponId") Integer couponId);

    /**
     * 取消订单（退货）时返回优惠券（can_use = can_use + 1）
     * @param memberId
     * @param couponId
     * @return
     */
    Integer backCouponUser(@Param("memberId") Integer memberId,
                           @Param("couponUserId") Integer couponUserId);
}