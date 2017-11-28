package com.ejavashop.web.controller.order;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.member.IMemberService;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 卖家订单controller
 *                       
 * @Filename: AdminOrdersController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/order/orders")
public class AdminOrdersController extends BaseController {
    @Resource(name = "ordersService")
    private IOrdersService orderService;
    @Resource(name = "memberService")
    private IMemberService memberService;
    Logger                 log = Logger.getLogger(this.getClass());

    /**
     * 默认页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/order/orders/orderslist";
    }

    /**
     * 未付款订单列表页面
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "state1", method = { RequestMethod.GET })
    public String listState1(HttpServletRequest request,
                             Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/order/orders/orderslist1";
    }

    /**
     * 待确认订单列表页面
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "state2", method = { RequestMethod.GET })
    public String listState2(HttpServletRequest request,
                             Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/order/orders/orderslist2";
    }

    /**
     * 待发货订单列表页面
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "state3", method = { RequestMethod.GET })
    public String listState3(HttpServletRequest request,
                             Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/order/orders/orderslist3";
    }

    /**
     * 已发货订单列表页面
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "state4", method = { RequestMethod.GET })
    public String listState4(HttpServletRequest request,
                             Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/order/orders/orderslist4";
    }

    /**
     * 已完成订单列表页面
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "state5", method = { RequestMethod.GET })
    public String listState5(HttpServletRequest request,
                             Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/order/orders/orderslist5";
    }

    /**
     * 已取消订单列表页面
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "state6", method = { RequestMethod.GET })
    public String listState6(HttpServletRequest request,
                             Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/order/orders/orderslist6";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Orders>> list(HttpServletRequest request,
                                                           Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        //        queryMap.put("q_paymentCode", "OFFLINE");
        ServiceResult<List<Orders>> serviceResult = orderService.getOrders(queryMap, pager);
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

    /**
     * 确认收款
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value = "submitpay", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> submitPay(HttpServletRequest request,
                                                           HttpServletResponse response,
                                                           Integer id) {

        ServiceResult<Orders> orderResult = orderService.getOrdersById(id);
        if (!orderResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(orderResult.getCode())) {
                throw new RuntimeException(orderResult.getMessage());
            } else {
                throw new BusinessException(orderResult.getMessage());
            }
        }
        Orders ordersDb = orderResult.getResult();

        if (ordersDb.getPaymentStatus().equals(Orders.PAYMENT_STATUS_1)) {
            return new HttpJsonResult<Boolean>("该订单已经付款，请勿重复操作！");
        }

        if (!ordersDb.getOrderState().equals(Orders.ORDER_STATE_4)
            && !ordersDb.getOrderState().equals(Orders.ORDER_STATE_5)) {
            return new HttpJsonResult<Boolean>("已发货或者已完成的订单才能确认收款。");
        }

        SystemAdmin systemAdmin = WebAdminSession.getAdminUser(request);
        Orders orders = new Orders();
        orders.setId(id);
        //已付款
        orders.setPaymentStatus(Orders.PAYMENT_STATUS_1);
        orders.setPayTime(new Date());
        orders.setCodconfirmId(systemAdmin.getId());
        orders.setCodconfirmName(systemAdmin.getName());
        orders.setCodconfirmState(Orders.CODCONFIRM_STATE_2);
        orders.setMoneyPaidReality(ordersDb.getMoneyOrder().subtract(ordersDb.getMoneyPaidBalance())
            .subtract(ordersDb.getMoneyIntegral()));

        ServiceResult<Integer> serviceResult = orderService.updateOrdersByAdmin(orders,
            Orders.SUBMIT_PAY, systemAdmin, false);

        HttpJsonResult<Boolean> jsonResult = null;
        if (serviceResult.getSuccess()) {
            jsonResult = new HttpJsonResult<Boolean>();
        } else {
            jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
        }

        memberService.memberOrderSendValue(ordersDb.getMemberId(), ordersDb.getMemberName(),
            ordersDb.getId());

        return jsonResult;
    }

    /**
     * 订单打印
     * 
     * @param request
     * @param dataMap
     * @param id
     * @return
     */
    @RequestMapping(value = "print", method = { RequestMethod.GET })
    public String print(HttpServletRequest request, Map<String, Object> dataMap, Integer id) {
        Orders orders = orderService.getOrderWithOPById(id).getResult();
        dataMap.put("orders", orders);
        return "admin/order/orders/ordersprint";
    }
}
