package com.ejavashop.model.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.order.OrderLogReadDao;
import com.ejavashop.dao.shop.write.order.OrderLogWriteDao;
import com.ejavashop.entity.order.OrderLog;

@Component(value = "orderLogModel")
public class OrderLogModel {

    @Resource
    private OrderLogWriteDao orderLogWriteDao;

    @Resource
    private OrderLogReadDao  orderLogReadDao;

    /**
    * 根据id取得订单操作日志表
    * @param  orderLogId
    * @return
    */
    public OrderLog getOrderLogById(Integer orderLogId) {
        return orderLogWriteDao.get(orderLogId);
    }

    /**
     * 保存订单操作日志表
     * @param  orderLog
     * @return
     */
    public Integer saveOrderLog(OrderLog orderLog) {
        return orderLogWriteDao.save(orderLog);
    }

    /**
    * 更新订单操作日志表
    * @param  orderLog
    * @return
    */
    public Integer updateOrderLog(OrderLog orderLog) {
        return orderLogWriteDao.update(orderLog);
    }

    /**
     * 根据订单ID 取得订单日志
     * @param orderId
     * @return
     */
    public List<OrderLog> getOrderLogByOrderId(Integer orderId) {

        // 参数校验
        if (orderId == null || orderId == 0) {
            throw new BusinessException("订单ID不能为空，请重试！");
        }
        //组合查询条件
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("q_ordersId", orderId);
        List<OrderLog> orderLogList = orderLogReadDao.queryList(queryMap);

        return orderLogList;

    }
}
