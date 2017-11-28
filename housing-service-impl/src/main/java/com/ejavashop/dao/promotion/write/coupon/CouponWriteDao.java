package com.ejavashop.dao.promotion.write.coupon;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.coupon.Coupon;

@Repository
public interface CouponWriteDao {

    Coupon get(java.lang.Integer id);

    Integer insert(Coupon coupon);

    Integer update(Coupon coupon);

    /**
     * 只修改活动状态、审核意见、修改者信息
     * @param actFull
     * @return
     */
    Integer updateStatus(Coupon coupon);

    /**
     * 修改优惠券的领取数量，received_num = received_num + num
     * @param id
     * @param num
     * @return
     */
    Integer updateReceivedNum(@Param("id") Integer id, @Param("num") Integer num);

    Integer delete(Integer id);
}