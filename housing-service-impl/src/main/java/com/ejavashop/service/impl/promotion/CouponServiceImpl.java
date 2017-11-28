package com.ejavashop.service.impl.promotion;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.promotion.coupon.Coupon;
import com.ejavashop.entity.promotion.coupon.CouponUser;
import com.ejavashop.entity.seller.SellerUser;
import com.ejavashop.model.promotion.CouponModel;
import com.ejavashop.model.promotion.CouponUserModel;
import com.ejavashop.service.promotion.ICouponService;

@Service(value = "couponService")
public class CouponServiceImpl implements ICouponService {
    private static Logger   log = LogManager.getLogger(CouponServiceImpl.class);

    @Resource
    private CouponModel     couponModel;
    @Resource
    private CouponUserModel couponUserModel;

    @Override
    public ServiceResult<Coupon> getCouponById(Integer couponId) {
        ServiceResult<Coupon> result = new ServiceResult<Coupon>();
        try {
            result.setResult(couponModel.getCouponById(couponId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[ICouponService][getCouponById]根据id[" + couponId + "]取得优惠券时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ICouponService][getCouponById]根据id[" + couponId + "]取得优惠券时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> saveCoupon(Coupon coupon) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(couponModel.saveCoupon(coupon));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[ICouponService][saveCoupon]保存优惠券时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ICouponService][saveCoupon]保存优惠券时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> updateCoupon(Coupon coupon) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(couponModel.updateCoupon(coupon));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[ICouponService][updateCoupon]更新优惠券时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ICouponService][updateCoupon]更新优惠券时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> updateCouponStatus(Coupon coupon) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(couponModel.updateCouponStatus(coupon));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[ICouponService][updateCouponStatus]更新优惠券状态时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ICouponService][updateCouponStatus]更新优惠券状态时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> deleteCoupon(Integer couponId, Integer userId, String userName) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(couponModel.deleteCoupon(couponId, userId, userName));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[ICouponService][deleteCoupon]删除优惠券时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ICouponService][deleteCoupon]删除优惠券时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<Coupon>> getCoupons(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<Coupon>> serviceResult = new ServiceResult<List<Coupon>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(couponModel.getCouponsCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(couponModel.getCoupons(queryMap, start, size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[ICouponService][getCoupons]根据条件取得优惠券时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ICouponService][getCoupons]param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[ICouponService][getCoupons]根据条件取得优惠券时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<Coupon>> getEffectiveCoupon(Integer sellerId, Integer channel) {
        ServiceResult<List<Coupon>> result = new ServiceResult<List<Coupon>>();
        try {
            result.setResult(couponModel.getEffectiveCoupon(sellerId, channel));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[ICouponService][getEffectiveCoupon]根据商家ID和渠道取得优惠券时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ICouponService][getEffectiveCoupon]根据商家ID和渠道取得优惠券时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<CouponUser>> exportCoupon(Integer couponId, Integer exportNum,
                                                        Integer sellerId, SellerUser sellerUser) {
        ServiceResult<List<CouponUser>> result = new ServiceResult<List<CouponUser>>();
        try {
            result
                .setResult(couponUserModel.exportCoupon(couponId, exportNum, sellerId, sellerUser));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error(
                "[ICouponService][exportCoupon]根据优惠券ID导出exportNum数量的优惠券信息时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ICouponService][exportCoupon]根据优惠券ID导出exportNum数量的优惠券信息时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<CouponUser>> getCouponUsers(Map<String, String> queryMap,
                                                          PagerInfo pager) {
        ServiceResult<List<CouponUser>> serviceResult = new ServiceResult<List<CouponUser>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(couponUserModel.getCouponUsersCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(couponUserModel.getCouponUsers(queryMap, start, size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[ICouponService][getCouponUsers]根据条件取得优惠券用户时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ICouponService][getCouponUsers]param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[ICouponService][getCouponUsers]根据条件取得优惠券用户时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<CouponUser>> getCouponUserByMemberId(Integer memberId,
                                                                   PagerInfo pager) {
        ServiceResult<List<CouponUser>> serviceResult = new ServiceResult<List<CouponUser>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(couponUserModel.getCouponUserByMemberIdCount(memberId));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(couponUserModel.getCouponUserByMemberId(memberId, start, size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error(
                "[ICouponService][getCouponUserByMemberId]根据用户ID取得优惠券用户时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ICouponService][getCouponUserByMemberId]param1:" + memberId + " &param2:"
                      + JSON.toJSONString(pager));
            log.error("[ICouponService][getCouponUserByMemberId]根据用户ID取得优惠券用户时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> receiveCoupon(Integer couponId, Integer memberId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(couponUserModel.receiveCoupon(couponId, memberId));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[ICouponService][receiveCoupon]用户领取优惠券时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error(
                "[ICouponService][receiveCoupon]couponId:" + couponId + " &memberId:" + memberId);
            log.error("[ICouponService][receiveCoupon]用户领取优惠券时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<CouponUser> getCouponUserById(Integer couponUserId) {
        ServiceResult<CouponUser> result = new ServiceResult<CouponUser>();
        try {
            result.setResult(couponUserModel.getCouponUserById(couponUserId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[ICouponService][getCouponUserById]根据id[" + couponUserId + "]取得优惠券用户表时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ICouponService][getCouponUserById]根据id[" + couponUserId + "]取得优惠券用户表时发生异常:",
                e);
        }
        return result;
    }

    /**
     * 根据用户ID和是否使用取得优惠券用户表
     * @param memberId
     * @param pager
     * @param canUse
     * @return
     * @see com.ejavashop.service.promotion.ICouponService#getCouponUserByMemberIdAndUse(java.lang.Integer, com.ejavashop.core.PagerInfo, java.lang.Integer)
     */
    @Override
    public ServiceResult<List<CouponUser>> getCouponUserByMemberIdAndUse(Integer memberId,
                                                                         PagerInfo pager,
                                                                         Integer canUse) {
        ServiceResult<List<CouponUser>> serviceResult = new ServiceResult<List<CouponUser>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(
                    couponUserModel.getCouponUserByMemberIdAndUseCount(memberId, canUse));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(
                couponUserModel.getCouponUserByMemberIdAndUse(memberId, canUse, start, size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[ICouponService][getCouponUserByMemberIdAndUse]根据用户ID和是否使用取得优惠券用户时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ICouponService][getCouponUserByMemberId]param1:" + memberId + " &param2:"
                      + JSON.toJSONString(pager));
            log.error("[ICouponService][getCouponUserByMemberIdAndUse]根据用户ID和是否使用取得优惠券用户时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<CouponUser>> getEffectiveByMemberIdAndSellerId(Integer memberId,
                                                                             Integer sellerId) {
        ServiceResult<List<CouponUser>> serviceResult = new ServiceResult<List<CouponUser>>();
        try {
            serviceResult
                .setResult(couponUserModel.getEffectiveByMemberIdAndSellerId(memberId, sellerId));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error(
                "[ICouponService][getEffectiveByMemberIdAndSellerId]根据用户ID、商家ID获取当前时间有效可用的优惠券时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ICouponService][getEffectiveByMemberIdAndSellerId]memberId:" + memberId
                      + " &sellerId:" + sellerId);
            log.error(
                "[ICouponService][getEffectiveByMemberIdAndSellerId]根据用户ID、商家ID获取当前时间有效可用的优惠券时发生异常:",
                e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<CouponUser> getCouponUserOnlyByCouponSn(String couponSn) {
        ServiceResult<CouponUser> serviceResult = new ServiceResult<CouponUser>();
        try {
            serviceResult.setResult(couponUserModel.getCouponUserOnlyByCouponSn(couponSn));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[ICouponService][getCouponUserOnlyByCouponSn]根据优惠码序列号取得优惠券用户表时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ICouponService][getCouponUserOnlyByCouponSn]couponSn:" + couponSn);
            log.error("[ICouponService][getCouponUserOnlyByCouponSn]根据优惠码序列号取得优惠券用户表时发生异常:", e);
        }
        return serviceResult;
    }
}