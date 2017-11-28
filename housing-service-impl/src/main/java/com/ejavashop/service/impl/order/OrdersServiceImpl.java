package com.ejavashop.service.impl.order;

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
import com.ejavashop.dto.OrderDayDto;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.entity.seller.SellerUser;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.model.order.OrdersModel;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.vo.order.OrderCommitVO;
import com.ejavashop.vo.order.OrderSuccessVO;

@Service(value = "ordersService")
public class OrdersServiceImpl implements IOrdersService {
    private static Logger log = LogManager.getLogger(OrdersServiceImpl.class);

    @Resource
    private OrdersModel   ordersModel;

    @Override
    public ServiceResult<Orders> getOrdersById(Integer ordersId) {
        ServiceResult<Orders> serviceResult = new ServiceResult<Orders>();
        try {
            serviceResult.setResult(ordersModel.getOrdersById(ordersId));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error(
                "[OrdersService][getOrdersById]根据id[" + ordersId + "]取得订单表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][getOrdersById]根据id[" + ordersId + "]取得订单表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Orders> getOrdersBySn(String orderSn) {
        ServiceResult<Orders> serviceResult = new ServiceResult<Orders>();
        try {
            serviceResult.setResult(ordersModel.getOrdersBySn(orderSn));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][getOrdersBySn]根据orderSn[" + orderSn + "]取得订单表时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][getOrdersBySn]根据orderSn[" + orderSn + "]取得订单表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<String> getOrderSnById(Integer ordersId) {
        ServiceResult<String> serviceResult = new ServiceResult<String>();
        try {
            serviceResult.setResult(ordersModel.getOrderSnById(ordersId));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][getOrderSnById]根据id[" + ordersId + "]取得订单号时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][getOrderSnById]根据id[" + ordersId + "]取得订单号时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> saveOrders(Orders orders) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setResult(ordersModel.saveOrders(orders));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][saveOrders]保存订单表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][saveOrders] param:" + JSON.toJSONString(orders));
            log.error("[OrdersService][saveOrders]保存订单表时出现未知异常：", e);
        }
        return serviceResult;
    }

    /**
    * 更新订单
    * @param  orders
    * @return
    */
    @Override
    public ServiceResult<Integer> updateOrders(Orders orders) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setResult(ordersModel.updateOrders(orders));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][updateOrders]修改订单表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][updateOrders] param:" + JSON.toJSONString(orders));
            log.error("[OrdersService][updateOrders]修改订单表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> deleteOrder(Integer ordersId) {

        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(ordersModel.deleteOrder(ordersId));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error(
                "[OrdersService][deleteOrder]根据id[" + ordersId + "]删除订单表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][deleteOrder]根据id[" + ordersId + "]删除订单表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<Orders>> getOrders(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<Orders>> serviceResult = new ServiceResult<List<Orders>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(ordersModel.getOrdersCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            List<Orders> list = ordersModel.getOrders(queryMap, start, size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][getOrders]根据条件取得订单表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][getOrders] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[OrdersService][getOrders]根据条件取得订单表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> updateOrdersBySeller(Orders orders, int type,
                                                       SellerUser sellerUser, boolean updateStore) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult
                .setResult(ordersModel.updateOrdersBySeller(orders, type, sellerUser, updateStore));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][updateOrdersBySeller]更新订单表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][updateOrdersBySeller]更新订单表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> updateOrdersByAdmin(Orders orders, int type,
                                                      SystemAdmin systemAdmin,
                                                      boolean updateStore) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult
                .setResult(ordersModel.updateOrdersByAdmin(orders, type, systemAdmin, updateStore));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][updateOrdersByAdmin]更新订单表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][updateOrdersByAdmin]更新订单表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> cancelOrderBySeller(Integer ordersId, SellerUser sellerUser) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(ordersModel.cancelOrderBySeller(ordersId, sellerUser));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][cancelOrderBySeller]取消订单表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][cancelOrderBySeller]取消订单表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> getOrderNumByMIdAndState(Integer memberId, Integer orderState) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setResult(ordersModel.getOrderNumByMIdAndState(memberId, orderState));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][getOrderNumByMIdAndState]根据会员ID，订单状态获取子订单数量时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][getOrderNumByMIdAndState]根据会员ID，订单状态获取子订单数量时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<Orders>> getOrderWithOrderProduct(Map<String, String> queryMap,
                                                                PagerInfo pager) {
        ServiceResult<List<Orders>> serviceResult = new ServiceResult<List<Orders>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(ordersModel.getOrdersCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }

            List<Orders> returnList = ordersModel.getOrderWithOrderProduct(queryMap, start, size);
            serviceResult.setResult(returnList);
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error(
                "[OrderService][getOrderWithOrderProduct]根据用户ID获取用户订单时发生异常:" + be.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][getOrderWithOrderProduct]根据用户ID获取用户订单时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> cancelOrder(Integer ordersId, Integer optId, String optName) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(ordersModel.cancelOrder(ordersId, optId, optName));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][cancelOrder]根据ID取消用户订单时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][cancalOrder]根据ID取消用户订单时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Orders> getOrderWithOPById(Integer orderId) {
        ServiceResult<Orders> serviceResult = new ServiceResult<Orders>();
        try {
            serviceResult.setResult(ordersModel.getOrderWithOPById(orderId));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error(
                "[OrderService][getOrderWithOPById]根据订单id取订单、网单及产品图片信息时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][getOrderWithOPById]根据订单id 取订单、网单及产品图片信息时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<OrderSuccessVO> orderCommit(OrderCommitVO orderCommitVO) {
        ServiceResult<OrderSuccessVO> serviceResult = new ServiceResult<OrderSuccessVO>();
        try {
            serviceResult.setResult(ordersModel.orderCommit(orderCommitVO));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][orderCommit]会员下单时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][orderCommit]会员下单时发生异常:", e);
        }
        return serviceResult;
    }

    /**
     * 确认收货
     * @param ordersId
     * @param request
     * @return
     */
    @Override
    public ServiceResult<Boolean> goodsReceipt(Member member, Integer ordersId) {

        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(ordersModel.goodsReceipt(member, ordersId));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][goodsReceipt]订单确认收货时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][goodsReceipt]订单确认收货时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<OrderDayDto>> getOrderDayDto(Map<String, String> queryMap) {
        ServiceResult<List<OrderDayDto>> serviceResult = new ServiceResult<List<OrderDayDto>>();
        try {
            serviceResult.setResult(ordersModel.getOrderDayDto(queryMap));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][getOrderDayDto]统计每天订单量时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][getOrderDayDto]统计每天订单量时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> jobSystemFinishOrder() {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(ordersModel.jobSystemFinishOrder());
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][jobSystemFinishOrder]系统完成订单时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][jobSystemFinishOrder]系统完成订单时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> jobSystemCancelOrder() {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(ordersModel.jobSystemCancelOrder());
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][jobSystemCancelOrder]系统取消订单时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][jobSystemCancelOrder]系统取消订单时发生异常:", e);
        }
        return serviceResult;
    }

    /**
     * 根据关联订单 查询订单信息 用于非在线支付 查询几个关联子订单之间的信息
     * @param relationOrderSn
     * @param request
     * @return
     */
    @Override
    public ServiceResult<OrderSuccessVO> getOrdersByRelationOrderSn(String relationOrderSn,
                                                                    Integer memberId) {
        ServiceResult<OrderSuccessVO> serviceResult = new ServiceResult<OrderSuccessVO>();
        try {
            serviceResult
                .setResult(ordersModel.getOrdersByRelationOrderSn(relationOrderSn, memberId));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][getOrdersByRelationOrderSn]查询订单时发生异常:", e);
        }
        return serviceResult;
    }

    /**
     * 支付之前的调用，获取订单列表，以及用余额支付等逻辑<br/>
     * 假如余额够支付，那么直接更改订单的状态，返回支付成功页面
     * @param relationOrderSn 订单号以逗号隔开
     * @param isBalancePay 是否用余额支付
     * @param balancePassword 余额密码，未加密
     * @param member
     * @return
     */
    @Override
    public ServiceResult<OrderSuccessVO> orderPayBefore(String relationOrderSn,
                                                        boolean isBalancePay,
                                                        String balancePassword, Member member) {
        ServiceResult<OrderSuccessVO> serviceResult = new ServiceResult<OrderSuccessVO>();
        try {
            serviceResult.setResult(
                ordersModel.orderPayBefore(relationOrderSn, isBalancePay, balancePassword, member));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error(
                "[OrderService][orderPayBefore]支付之前的调用，获取订单列表，以及用余额支付等逻辑发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][orderPayBefore]支付之前的调用，获取订单列表，以及用余额支付等逻辑发生异常:", e);
            e.printStackTrace();
        }
        return serviceResult;
    }

    /**
     * 支付成功之后更改订单的状态
     * @param trade_no 订单
     * @param total_fee 金额
     * @param paycode 支付方式
     * @param payname 支付方式
     * @param tradeSn 交易流水号
     * @param tradeContent 交易返回信息
     * @return
     */
    @Override
    public ServiceResult<Boolean> orderPayAfter(String trade_no, String total_fee, String paycode,
                                                String payname, String tradeSn,
                                                String tradeContent) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(ordersModel.orderPayAfter(trade_no, total_fee, paycode, payname,
                tradeSn, tradeContent));
        } catch (BusinessException be) {
            be.printStackTrace();
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][orderPayAfter]支付成功之后更改订单的状态发生异常:" + be.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][orderPayAfter]支付成功之后更改订单的状态辑发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<OrderSuccessVO> orderCommitForFlash(OrderCommitVO orderCommitVO) {
        ServiceResult<OrderSuccessVO> serviceResult = new ServiceResult<OrderSuccessVO>();
        try {
            serviceResult.setResult(ordersModel.orderCommitForFlash(orderCommitVO));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][orderCommitForFlash]会员限时抢购下单时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][orderCommitForFlash]会员限时抢购下单时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<OrderSuccessVO> orderCommitForGroup(OrderCommitVO orderCommitVO) {
        ServiceResult<OrderSuccessVO> serviceResult = new ServiceResult<OrderSuccessVO>();
        try {
            serviceResult.setResult(ordersModel.orderCommitForGroup(orderCommitVO));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][orderCommitForGroup]会员团购下单时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][orderCommitForGroup]会员团购下单时发生异常:", e);
        }
        return serviceResult;
    }
}