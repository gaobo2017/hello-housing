package com.ejavashop.web.controller.promotion;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.excel.CellConfig;
import com.ejavashop.core.excel.ExcelConfig;
import com.ejavashop.core.excel.ExcelManager;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.promotion.coupon.Coupon;
import com.ejavashop.entity.promotion.coupon.CouponUser;
import com.ejavashop.service.promotion.ICouponService;
import com.ejavashop.web.controller.BaseController;

/**
 * 优惠券发放管理controller
 *                       
 * @Filename: SellerCouponUserController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/promotion/couponuser")
public class AdminCouponUserController extends BaseController {

    @Resource
    private ICouponService couponService;

    /**
     * 优惠券发放列表页
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap, @RequestParam("couponId") Integer couponId) {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        ServiceResult<Coupon> serviceResult = couponService.getCouponById(couponId);
        dataMap.put("coupon", serviceResult.getResult());
        return "admin/promotion/couponuser/couponuserlist";
    }

    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<CouponUser>> list(HttpServletRequest request,
                                                               HttpServletResponse response,
                                                               Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

        // 优惠券ID为必须条件
        if (queryMap.get("q_couponId") == null || "0".equals(queryMap.get("q_couponId"))) {
            return new HttpJsonResult<List<CouponUser>>();
        }

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<CouponUser>> serviceResult = couponService.getCouponUsers(queryMap,
            pager);
        pager = serviceResult.getPager();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<CouponUser> list = serviceResult.getResult();

        HttpJsonResult<List<CouponUser>> jsonResult = new HttpJsonResult<List<CouponUser>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "doexport", method = { RequestMethod.GET })
    public void doExportExcel(HttpServletRequest request, HttpServletResponse response,
                              @RequestHeader(value = "user-agent") String userAgent) {

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

        String busiErrMsg = "";
        // 优惠券ID为必须条件
        Integer couponId = ConvertUtil.toInt(queryMap.get("q_couponId"), null);
        if (couponId == null || couponId.intValue() == 0) {
            busiErrMsg = "优惠券ID不能为空。";
        }

        ServiceResult<Coupon> serviceResult = couponService.getCouponById(couponId);
        if (!serviceResult.getSuccess()) {
            busiErrMsg = serviceResult.getMessage();
        }
        if (serviceResult.getResult() == null) {
            busiErrMsg = "优惠券信息获取失败。";
        }
        Coupon coupon = serviceResult.getResult();

        ServiceResult<List<CouponUser>> couponUserResult = couponService.getCouponUsers(queryMap,
            null);
        if (!couponUserResult.getSuccess()) {
            busiErrMsg = couponUserResult.getMessage();
        }
        if (couponUserResult.getResult() == null) {
            busiErrMsg = "优惠券发放信息获取为空。";
        }

        if (!StringUtil.isEmpty(busiErrMsg, true)) {
            try {
                Cookie msgCookie = new Cookie("busiErrMsg", URLEncoder.encode(busiErrMsg, "utf-8"));
                msgCookie.setPath("/");
                response.addCookie(msgCookie);
                Cookie fileDownloadCookie = new Cookie("fileDownload", "false");
                fileDownloadCookie.setPath("/");
                response.addCookie(fileDownloadCookie);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            List<CouponUser> list = couponUserResult.getResult();

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            queryMap.put("couponName", coupon.getCouponName());
            queryMap.put("couponValue", coupon.getCouponValue().toString());
            queryMap.put("minAmount", coupon.getMinAmount().toString());
            queryMap.put("sendStartTime", format.format(coupon.getSendStartTime()));
            queryMap.put("sendEndTime", format.format(coupon.getSendEndTime()));
            queryMap.put("useStartTime", format.format(coupon.getUseStartTime()));
            queryMap.put("useEndTime", format.format(coupon.getUseEndTime()));
            queryMap.put("personLimitNum", coupon.getPersonLimitNum().toString());
            queryMap.put("totalLimitNum", coupon.getTotalLimitNum().toString());
            queryMap.put("receivedNum", coupon.getReceivedNum().toString());
            if (coupon.getType().intValue() == 1) {
                queryMap.put("type", "在线发放");
            } else if (coupon.getType().intValue() == 2) {
                queryMap.put("type", "线下发放");
            }
            if (coupon.getChannel().intValue() == 1) {
                queryMap.put("channel", "通用");
            } else if (coupon.getChannel().intValue() == 2) {
                queryMap.put("channel", "PC");
            } else if (coupon.getChannel().intValue() == 3) {
                queryMap.put("channel", "mobile");
            }
            this.export(response, userAgent, list, queryMap);
        }
    }

    /**
     * 导出excel数据
     * @param response
     * @param userAgent
     * @param result
     * @return
     */
    private void export(HttpServletResponse response, String userAgent,
                        List<CouponUser> couponUserList, Map<String, String> queryMap) {
        ExcelConfig<CouponUser> config = new ExcelConfig<CouponUser>();
        config.setData(couponUserList);
        config.setExcelVersion(ExcelConfig.ExcelVersion.EXECL_VERSION_2007);
        config.setFileName(queryMap.get("couponName") + "-"
                           + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        config.setLineConfig(getLineConfig());
        this.setSearchCondition(config, queryMap);
        config.setSheetName(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        config.setUserAgent(userAgent);
        ExcelManager.export(response, config);
    }

    /**
     * 设置搜索条件
     * @param config
     * @param queryMap
     */
    private void setSearchCondition(ExcelConfig<CouponUser> config, Map<String, String> queryMap) {
        if (queryMap == null)
            return;

        config.setSearchConditionKeys(
            new String[] { "优惠券名称", "优惠券金额", "订单最低金额", "发放开始时间", "发放结束时间", "使用开始时间", "使用结束时间",
                           "用户限制领取数量", "总数量", "已发放数量", "优惠券类型", "使用渠道", "领取开始时间（查询）", "领取结束时间（查询）",
                           "使用开始时间（查询）", "使用结束时间（查询）" });
        config.setSearchConditionValues(
            new String[] { queryMap.get("couponName"), queryMap.get("couponValue"),
                           queryMap.get("minAmount"), queryMap.get("sendStartTime"),
                           queryMap.get("sendEndTime"), queryMap.get("useStartTime"),
                           queryMap.get("useEndTime"), queryMap.get("personLimitNum"),
                           queryMap.get("totalLimitNum"), queryMap.get("receivedNum"),
                           queryMap.get("type"), queryMap.get("channel"),
                           queryMap.get("q_receiveTimeStart"), queryMap.get("q_receiveTimeEnd"),
                           queryMap.get("q_useTimeStart"), queryMap.get("q_useTimeEnd") });

    }

    /**
     * 取得行配置
     * @return
     */
    private LinkedHashMap<String, CellConfig> getLineConfig() {
        LinkedHashMap<String, CellConfig> config = new LinkedHashMap<String, CellConfig>();
        CellConfig sellerConfig = new CellConfig("店铺名称");
        config.put("sellerName", sellerConfig);

        CellConfig memberConfig = new CellConfig("会员名称");
        config.put("memberName", memberConfig);

        CellConfig couponSnConfig = new CellConfig("序列号");
        config.put("couponSn", couponSnConfig);

        CellConfig canUseConfig = new CellConfig("可使用次数");
        config.put("canUse", canUseConfig);

        CellConfig receiveTimeConfig = new CellConfig("领取时间");
        config.put("receiveTime", receiveTimeConfig);

        CellConfig useTimeConfig = new CellConfig("使用时间");
        config.put("useTime", useTimeConfig);

        CellConfig orderSnConfig = new CellConfig("订单号");
        config.put("orderSn", orderSnConfig);

        return config;
    }
}
