package com.ejavashop.service.promotion;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.promotion.coupon.Coupon;
import com.ejavashop.entity.promotion.coupon.CouponUser;
import com.ejavashop.entity.seller.SellerUser;

public interface ICouponService {

    /**
     * 根据id取得优惠券
     * @param  couponId
     * @return
     */
    ServiceResult<Coupon> getCouponById(Integer couponId);

    /**
     * 保存优惠券
     * @param  coupon
     * @return
     */
    ServiceResult<Boolean> saveCoupon(Coupon coupon);

    /**
     * 更新优惠券
     * @param coupon
     * @return
     */
    ServiceResult<Boolean> updateCoupon(Coupon coupon);

    /**
     * 更新优惠券状态（只修改状态、审核意见、修改者信息）
     * @param coupon
     * @return
     */
    ServiceResult<Boolean> updateCouponStatus(Coupon coupon);

    /**
     * 删除优惠券
     * @param couponId
     * @param userId 删除人ID
     * @param userName 删除人名称
     * @return
     */
    ServiceResult<Boolean> deleteCoupon(Integer couponId, Integer userId, String userName);

    /**
     * 根据条件取得优惠券
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<Coupon>> getCoupons(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 根据商家ID、渠道取得优惠券（当前时间在发放时间内、上架、未超发放限额、在线领取类型的优惠券）
     * 
     * @param sellerId
     * @param channel 渠道
     * @return
     */
    ServiceResult<List<Coupon>> getEffectiveCoupon(Integer sellerId, Integer channel);

    /**
     * 根据优惠券ID导出exportNum数量的优惠券信息，用于线下发放
     * 
     * @param couponId 优惠券ID
     * @param exportNum 导出数量
     * @param sellerId 商家ID
     * @param sellerUser 店铺管理员
     * @return
     */
    ServiceResult<List<CouponUser>> exportCoupon(Integer couponId, Integer exportNum,
                                                 Integer sellerId, SellerUser sellerUser);

    /**
     * 根据条件取得优惠券用户表
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<CouponUser>> getCouponUsers(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 根据用户ID取得优惠券用户表
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<CouponUser>> getCouponUserByMemberId(Integer memberId, PagerInfo pager);

    /**
     * 根据用户ID和是否使用取得优惠券用户表
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<CouponUser>> getCouponUserByMemberIdAndUse(Integer memberId, PagerInfo pager,
                                                                  Integer canUse);

    /**
     * 用户领取优惠券
     * @param couponId 优惠券ID
     * @param memberId 用户ID
     * @return
     */
    ServiceResult<Boolean> receiveCoupon(Integer couponId, Integer memberId);

    /**
     * 根据id取得优惠券用户表
     * @param  couponUserId
     * @return
     */
    ServiceResult<CouponUser> getCouponUserById(Integer couponUserId);

    /**
     * 根据用户ID、商家ID获取当前时间有效可用的优惠券
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<CouponUser>> getEffectiveByMemberIdAndSellerId(Integer memberId,
                                                                      Integer sellerId);

    /**
     * 根据优惠码序列号取得优惠券用户表
     * @param couponSn
     * @return
     */
    ServiceResult<CouponUser> getCouponUserOnlyByCouponSn(String couponSn);

}