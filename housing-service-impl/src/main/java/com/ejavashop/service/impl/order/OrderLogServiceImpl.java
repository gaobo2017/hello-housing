package com.ejavashop.service.impl.order;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.order.OrderLog;
import com.ejavashop.model.order.OrderLogModel;
import com.ejavashop.service.order.IOrderLogService;

@Service(value = "orderLogService")
public class OrderLogServiceImpl implements IOrderLogService {
    private static Logger log = LogManager.getLogger(OrderLogServiceImpl.class);

    @Resource
    private OrderLogModel orderLogModel;

    @Override
    public ServiceResult<OrderLog> getOrderLogById(Integer orderLogId) {
        ServiceResult<OrderLog> serviceResult = new ServiceResult<OrderLog>();
        try {
            serviceResult.setResult(orderLogModel.getOrderLogById(orderLogId));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrderLogService][getOrderLogById]根据id[" + orderLogId + "]取得订单操作日志表时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderLogService][getOrderLogById]根据id[" + orderLogId + "]取得订单操作日志表时出现未知异常：",
                e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> saveOrderLog(OrderLog orderLog) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setResult(orderLogModel.saveOrderLog(orderLog));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrderLogService][saveOrderLog]保存订单操作日志表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderLogService][saveOrderLog] param:" + JSON.toJSONString(orderLog));
            log.error("[OrderLogService][saveOrderLog]保存订单操作日志表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> updateOrderLog(OrderLog orderLog) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setResult(orderLogModel.updateOrderLog(orderLog));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrderLogService][updateOrderLog]修改订单操作日志表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderLogService][updateOrderLog] param:" + JSON.toJSONString(orderLog));
            log.error("[OrderLogService][updateOrderLog]修改订单操作日志表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<OrderLog>> getOrderLogByOrderId(Integer orderId) {
        ServiceResult<List<OrderLog>> serviceResult = new ServiceResult<List<OrderLog>>();
        try {
            serviceResult.setResult(orderLogModel.getOrderLogByOrderId(orderId));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error(
                "[OrderLogService][getOrderLogByOrderId]根据订单ID取得订单日志时出现异常：" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderLogService][getOrderLogByOrderId]根据订单ID取得订单日志时发生异常:", e);
        }
        return serviceResult;

    }
}