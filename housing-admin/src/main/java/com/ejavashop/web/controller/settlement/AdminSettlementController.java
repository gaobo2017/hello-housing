package com.ejavashop.web.controller.settlement;

import java.math.BigDecimal;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.member.MemberProductBack;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.entity.settlement.Settlement;
import com.ejavashop.entity.settlement.SettlementOp;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.member.IMemberProductBackService;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.settlement.ISettlementOpService;
import com.ejavashop.service.settlement.ISettlementService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 结算管理controller
 *
 * @Filename: AdminSettlementController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/settlement")
public class AdminSettlementController extends BaseController {

    @Resource(name = "settlementService")
    private ISettlementService        settlementService;

    @Resource(name = "settlementOpService")
    private ISettlementOpService      settlementOpService;

    @Resource(name = "ordersService")
    private IOrdersService            ordersService;

    @Resource(name = "memberProductBackService")
    private IMemberProductBackService memberProductBackService;

    /**
     * 初始化controller接口
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/settlement/settlementlist";
    }

    /**
     * 管理页面查询按钮controller接口
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Settlement>> list(HttpServletRequest request,
                                                               HttpServletResponse response,
                                                               Map<String, Object> dataMap) {

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<Settlement>> serviceResult = settlementService.getSettlements(queryMap,
            pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<Settlement>> jsonResult = new HttpJsonResult<List<Settlement>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    /**
     * 详情页
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "detail", method = { RequestMethod.GET })
    public String detail(HttpServletRequest request, Map<String, Object> dataMap, Integer id) {

        ServiceResult<Settlement> settlementResult = settlementService.getSettlementById(id);
        if (!settlementResult.getSuccess()) {
            dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
            dataMap.put("message", settlementResult.getMessage());
            return "admin/settlement/settlementlist";
        }
        if (settlementResult.getResult() == null) {
            dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
            dataMap.put("message", "结算账单获取失败！");
            return "admin/settlement/settlementlist";
        }

        dataMap.put("settlement", settlementResult.getResult());

        return "admin/settlement/settlementdetail";
    }

    @RequestMapping(value = "orderlist", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Orders>> orderlist(HttpServletRequest request,
                                                                HttpServletResponse response,
                                                                Map<String, Object> dataMap,
                                                                Integer settlementId) {

        ServiceResult<Settlement> settlementRlt = settlementService.getSettlementById(settlementId);
        if (!settlementRlt.getSuccess()) {
            return new HttpJsonResult<List<Orders>>();
        }

        Settlement settlement = settlementRlt.getResult();

        String settleCycle = settlement.getSettleCycle();
        // 周期开始时间，1号0时0分0秒
        String startTime = this.getStartTime(settleCycle);
        // 周期结束时间，周期月最后一个天23时59分59秒
        // 周期最后一天
        String endTime = this.getEndTime(settleCycle);

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("q_sellerId", settlement.getSellerId() + "");
        queryMap.put("q_orderState", Orders.ORDER_STATE_5 + "");
        queryMap.put("q_finishStartTime", startTime);
        queryMap.put("q_finishEndTime", endTime);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<Orders>> serviceResult = ordersService.getOrders(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<Orders>> jsonResult = new HttpJsonResult<List<Orders>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "getSettlementOp", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<SettlementOp>> getSettlementOp(HttpServletRequest request,
                                                                            HttpServletResponse response,
                                                                            Integer orderId) {
        ServiceResult<List<SettlementOp>> res = settlementOpService.getSettlementOpByOId(orderId);
        HttpJsonResult<List<SettlementOp>> json = new HttpJsonResult<List<SettlementOp>>();
        json.setRows(res.getResult());
        json.setTotal(res.getResult().size());
        return json;
    }

    @RequestMapping(value = "backlist", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<MemberProductBack>> backlist(HttpServletRequest request,
                                                                          Map<String, Object> dataMap,
                                                                          Integer settlementId) {

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<Settlement> settlementRlt = settlementService.getSettlementById(settlementId);
        if (!settlementRlt.getSuccess()) {
            return new HttpJsonResult<List<MemberProductBack>>();
        }

        Settlement settlement = settlementRlt.getResult();

        String settleCycle = settlement.getSettleCycle();
        // 周期开始时间，1号0时0分0秒
        String startTime = this.getStartTime(settleCycle);
        // 周期结束时间，周期月最后一个天23时59分59秒
        // 周期最后一天
        String endTime = this.getEndTime(settleCycle);

        ServiceResult<List<MemberProductBack>> serviceResult = memberProductBackService
            .getSettleBacks(settlement.getSellerId(), startTime, endTime, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<MemberProductBack>> jsonResult = new HttpJsonResult<List<MemberProductBack>>();
        jsonResult.setRows((List<MemberProductBack>) serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    private String getStartTime(String settleCycle) {
        // 结算年
        String year = settleCycle.substring(0, 4);
        // 结算月
        String month = settleCycle.substring(4);
        // 周期开始时间，1号0时0分0秒
        String startTime = year + "-" + month + "-01 00:00:00";
        return startTime;
    }

    private String getEndTime(String settleCycle) {
        // 结算年
        String year = settleCycle.substring(0, 4);
        // 结算月
        String month = settleCycle.substring(4);
        // 周期结束时间，周期月最后一个天23时59分59秒
        // 周期最后一天
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, ConvertUtil.toInt(year, 1000));
        cal.set(Calendar.MONDAY, (ConvertUtil.toInt(month, 0) - 1));
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        String endTime = year + "-" + month + "-" + lastDay + " 23:59:59";
        return endTime;
    }

    /**
     * 商家核对后发起结算
     * @param settlement
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "settleStart", method = { RequestMethod.POST })
    public String settleStart(Settlement settlement, HttpServletRequest request,
                              Map<String, Object> dataMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);

        BigDecimal moneyOther = settlement.getMoneyOther();
        if (moneyOther != null && moneyOther.compareTo(BigDecimal.ZERO) > 0) {
            if (settlement.getMoneyOtherType() != Settlement.MONEY_OTHER_TYPE_1
                && settlement.getMoneyOtherType() != Settlement.MONEY_OTHER_TYPE_2) {
                dataMap.put("settlement", settlement);
                dataMap.put("message", "请选择其他金额类型！");
                return "admin/settlement/settlementdetail";
            }

            if (StringUtil.isEmpty(settlement.getMoneyOtherReason(), true)) {
                dataMap.put("settlement", settlement);
                dataMap.put("message", "请填写其他金额的产生理由！");
                return "admin/settlement/settlementdetail";
            }
        }

        ServiceResult<Settlement> settlementRlt = settlementService
            .getSettlementById(settlement.getId());

        if (!settlementRlt.getSuccess()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", settlementRlt.getMessage());
            return "admin/settlement/settlementdetail";
        }
        if (settlementRlt.getResult() == null) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "结算信息获取失败，请重试！");
            return "admin/settlement/settlementdetail";
        }
        Settlement settlementDb = settlementRlt.getResult();
        // 只能对刚生产的账单发起结算
        if (!settlementDb.getStatus().equals(Settlement.STATUS_1)) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "只能对刚生产的账单发起结算，谢谢！");
            return "admin/settlement/settlementdetail";
        }

        Settlement settlementNew = new Settlement();
        settlementNew.setId(settlement.getId());
        if (moneyOther != null && moneyOther.compareTo(BigDecimal.ZERO) > 0) {
            settlementNew.setMoneyOther(settlement.getMoneyOther());
            settlementNew.setMoneyOtherType(settlement.getMoneyOtherType());
            settlementNew.setMoneyOtherReason(settlement.getMoneyOtherReason());
            //            if (settlement.getMoneyOtherType().equals(Settlement.MONEY_OTHER_TYPE_1)) {
            //                settlementNew.setPayable(settlementDb.getPayable().add(settlement.getMoneyOther()));
            //            } else if (settlement.getMoneyOtherType().equals(Settlement.MONEY_OTHER_TYPE_2)) {
            //                settlementNew
            //                    .setPayable(settlementDb.getPayable().subtract(settlement.getMoneyOther()));
            //            }
        }
        settlementNew.setStatus(Settlement.STATUS_2);
        settlementNew.setUpdateUserId(adminUser.getId());
        settlementNew.setUpdateUserName(adminUser.getName());
        settlementNew.setUpdateTime(new Date());

        ServiceResult<Boolean> updateSettlement = settlementService.updateSettlement(settlementNew);

        if (!updateSettlement.getSuccess()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", updateSettlement.getMessage());
            return "admin/settlement/settlementdetail";
        }
        if (updateSettlement.getResult() == null || !updateSettlement.getResult()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "发起结算失败，请重试！");
            return "admin/settlement/settlementdetail";
        }
        return "redirect:";
    }

    @RequestMapping(value = "platformExplain", method = { RequestMethod.POST })
    public String platformExplain(Settlement settlement, HttpServletRequest request,
                                  Map<String, Object> dataMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);

        if (StringUtil.isEmpty(settlement.getPlatformExplain(), true)) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "请输入理由！");
            return "admin/settlement/settlementdetail";
        }

        BigDecimal moneyOther = settlement.getMoneyOther();
        if (moneyOther != null && moneyOther.compareTo(BigDecimal.ZERO) > 0) {
            if (settlement.getMoneyOtherType() != Settlement.MONEY_OTHER_TYPE_1
                && settlement.getMoneyOtherType() != Settlement.MONEY_OTHER_TYPE_2) {
                dataMap.put("settlement", settlement);
                dataMap.put("message", "请选择其他金额类型！");
                return "admin/settlement/settlementdetail";
            }

            if (StringUtil.isEmpty(settlement.getMoneyOtherReason(), true)) {
                dataMap.put("settlement", settlement);
                dataMap.put("message", "请填写其他金额的产生理由！");
                return "admin/settlement/settlementdetail";
            }
        }

        ServiceResult<Settlement> settlementRlt = settlementService
            .getSettlementById(settlement.getId());

        if (!settlementRlt.getSuccess()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", settlementRlt.getMessage());
            return "admin/settlement/settlementdetail";
        }
        if (settlementRlt.getResult() == null) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "结算信息获取失败，请重试！");
            return "admin/settlement/settlementdetail";
        }
        Settlement settlementDb = settlementRlt.getResult();
        // 只能对商家质疑的账单进行回复
        if (!settlementDb.getStatus().equals(Settlement.STATUS_4)) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "只能对商家质疑的账单进行回复，谢谢！");
            return "admin/settlement/settlementdetail";
        }

        Settlement settlementNew = new Settlement();
        settlementNew.setId(settlement.getId());
        if (moneyOther != null && moneyOther.compareTo(BigDecimal.ZERO) > 0) {
            settlementNew.setMoneyOther(settlement.getMoneyOther());
            settlementNew.setMoneyOtherType(settlement.getMoneyOtherType());
            settlementNew.setMoneyOtherReason(settlement.getMoneyOtherReason());
            //            if (settlement.getMoneyOtherType().equals(Settlement.MONEY_OTHER_TYPE_1)) {
            //                settlementNew.setPayable(settlementDb.getPayable().add(settlement.getMoneyOther()));
            //            } else if (settlement.getMoneyOtherType().equals(Settlement.MONEY_OTHER_TYPE_2)) {
            //                settlementNew
            //                    .setPayable(settlementDb.getPayable().subtract(settlement.getMoneyOther()));
            //            }
        }
        settlementNew.setStatus(Settlement.STATUS_4);
        settlementNew.setPlatformExplain(settlement.getPlatformExplain());
        settlementNew.setUpdateUserId(adminUser.getId());
        settlementNew.setUpdateUserName(adminUser.getName());
        settlementNew.setUpdateTime(new Date());

        ServiceResult<Boolean> updateSettlement = settlementService.updateSettlement(settlementNew);

        if (!updateSettlement.getSuccess()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", updateSettlement.getMessage());
            return "admin/settlement/settlementdetail";
        }
        if (updateSettlement.getResult() == null || !updateSettlement.getResult()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "操作失败，请重试！");
            return "admin/settlement/settlementdetail";
        }
        return "redirect:";
    }

    /**
     * 平台对商家通过的账单进行完成核对操作
     * @param settlement
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "checkOver", method = { RequestMethod.POST })
    public String checkOver(Settlement settlement, HttpServletRequest request,
                            Map<String, Object> dataMap) {

        ServiceResult<Settlement> settlementRlt = settlementService
            .getSettlementById(settlement.getId());

        if (!settlementRlt.getSuccess()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", settlementRlt.getMessage());
            return "admin/settlement/settlementdetail";
        }
        if (settlementRlt.getResult() == null) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "结算信息获取失败，请重试！");
            return "admin/settlement/settlementdetail";
        }
        Settlement settlementDb = settlementRlt.getResult();
        // 只能对对账完成的账单进行支付完成
        if (!settlementDb.getStatus().equals(Settlement.STATUS_3)) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "只能对通过了商家审核的账单进行完成对账操作，谢谢！");
            return "admin/settlement/settlementdetail";
        }

        SystemAdmin admin = WebAdminSession.getAdminUser(request);
        Settlement settlementNew = new Settlement();
        settlementNew.setId(settlement.getId());
        settlementNew.setStatus(Settlement.STATUS_5);
        settlementNew.setUpdateUserId(admin.getId());
        settlementNew.setUpdateUserName(admin.getName());
        settlementNew.setUpdateTime(new Date());

        ServiceResult<Boolean> updateSettlement = settlementService.updateSettlement(settlementNew);

        if (!updateSettlement.getSuccess()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", updateSettlement.getMessage());
            return "admin/settlement/settlementdetail";
        }
        if (updateSettlement.getResult() == null || !updateSettlement.getResult()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "操作失败，请重试！");
            return "admin/settlement/settlementdetail";
        }
        return "redirect:";
    }

    /**
     * 平台向商家打款后更改支付状态
     * @param settlement
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "payOver", method = { RequestMethod.POST })
    public String payOver(Settlement settlement, HttpServletRequest request,
                          Map<String, Object> dataMap) {

        ServiceResult<Settlement> settlementRlt = settlementService
            .getSettlementById(settlement.getId());

        if (!settlementRlt.getSuccess()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", settlementRlt.getMessage());
            return "admin/settlement/settlementdetail";
        }
        if (settlementRlt.getResult() == null) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "结算信息获取失败，请重试！");
            return "admin/settlement/settlementdetail";
        }
        Settlement settlementDb = settlementRlt.getResult();
        // 只能对对账完成的账单进行支付完成
        if (!settlementDb.getStatus().equals(Settlement.STATUS_5)) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "只能对对账完成的账单进行支付完成操作，谢谢！");
            return "admin/settlement/settlementdetail";
        }

        SystemAdmin admin = WebAdminSession.getAdminUser(request);
        Settlement settlementNew = new Settlement();
        settlementNew.setId(settlement.getId());
        settlementNew.setStatus(Settlement.STATUS_6);
        settlementNew.setUpdateUserId(admin.getId());
        settlementNew.setUpdateUserName(admin.getName());
        settlementNew.setUpdateTime(new Date());

        ServiceResult<Boolean> updateSettlement = settlementService.updateSettlement(settlementNew);

        if (!updateSettlement.getSuccess()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", updateSettlement.getMessage());
            return "admin/settlement/settlementdetail";
        }
        if (updateSettlement.getResult() == null || !updateSettlement.getResult()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "操作失败，请重试！");
            return "admin/settlement/settlementdetail";
        }
        return "redirect:";
    }
}
