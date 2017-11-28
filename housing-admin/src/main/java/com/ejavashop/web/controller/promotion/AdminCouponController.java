package com.ejavashop.web.controller.promotion;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.promotion.coupon.Coupon;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.promotion.ICouponService;
import com.ejavashop.service.seller.ISellerService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 优惠券管理controller
 *                       
 * @Filename: AdminCouponController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/promotion/coupon")
public class AdminCouponController extends BaseController {

    @Resource
    private ICouponService couponService;
    @Resource
    private ISellerService sellerService;

    /**
     * 订单满减列表页
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        ServiceResult<List<Seller>> sellers = sellerService
            .getSellers(new HashMap<String, String>(), null);
        dataMap.put("sellers", sellers.getResult());
        return "admin/promotion/coupon/couponlist";
    }

    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Coupon>> list(HttpServletRequest request,
                                                           HttpServletResponse response,
                                                           Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<Coupon>> serviceResult = couponService.getCoupons(queryMap, pager);
        pager = serviceResult.getPager();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<Coupon> list = serviceResult.getResult();

        HttpJsonResult<List<Coupon>> jsonResult = new HttpJsonResult<List<Coupon>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "audit", method = { RequestMethod.GET })
    public String audit(HttpServletRequest request, int couponId, Map<String, Object> dataMap) {

        ServiceResult<Coupon> serviceResult = couponService.getCouponById(couponId);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/coupon/couponlist";
            }
        }
        Coupon coupon = serviceResult.getResult();

        dataMap.put("coupon", coupon);
        return "admin/promotion/coupon/couponaudit";
    }

    @RequestMapping(value = "doaudit", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> doAudit(HttpServletRequest request,
                                                         @RequestParam("id") Integer id,
                                                         @RequestParam("status") Integer status) {

        ServiceResult<Coupon> serviceResult = couponService.getCouponById(id);

        if (!serviceResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(serviceResult.getMessage());
        }
        Coupon couponDb = serviceResult.getResult();
        if (couponDb == null) {
            return new HttpJsonResult<Boolean>("优惠券信息获取失败。");
        }

        if (couponDb.getStatus().intValue() != Coupon.STATUS_2) {
            return new HttpJsonResult<Boolean>("非提交审核状态的优惠券不能执行审核操作。");
        }

        String auditOpinion = request.getParameter("auditOpinion");
        if (status.intValue() == Coupon.STATUS_4 && StringUtil.isEmpty(auditOpinion, true)) {
            return new HttpJsonResult<Boolean>("请填写审核失败原因。");
        }

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);

        Coupon coupon = new Coupon();
        coupon.setId(id);
        coupon.setStatus(status);
        coupon.setAuditOpinion(auditOpinion);
        coupon.setAuditUserId(adminUser.getId());
        coupon.setAuditUserName(adminUser.getName());
        coupon.setAuditTime(new Date());
        coupon.setSellerId(couponDb.getSellerId());
        ServiceResult<Boolean> updateResult = couponService.updateCouponStatus(coupon);
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!updateResult.getSuccess()) {
            jsonResult.setMessage(updateResult.getMessage());
        }
        return jsonResult;
    }

}
