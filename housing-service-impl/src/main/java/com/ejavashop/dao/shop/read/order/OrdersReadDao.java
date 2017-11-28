package com.ejavashop.dao.shop.read.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.dto.OrderDayDto;
import com.ejavashop.entity.order.Orders;

/**
 * 订单表
 * 
 * @Filename: OrdersReadDao.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Repository
public interface OrdersReadDao {

    /**
     * 根据会员ID，订单状态获取 子订单 数量
     * @param memberId
     * @param orderState
     * @return
     */
    Integer getOrderNumByMIdAndState(@Param("memberId") Integer memberId,
                                     @Param("orderState") Integer orderState);

    Orders get(@Param("id") java.lang.Integer id);

    /**
     * 根据商家ID及时间获取已经完成的订单
     * @param sellerId 商家ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    List<Orders> getSettleOrders(@Param("sellerId") Integer sellerId,
                                 @Param("startTime") String startTime,
                                 @Param("endTime") String endTime);

    /**
     * 统计每天订单量
     * @param queryMap
     * @return
     */
    List<OrderDayDto> getOrderDayDto(@Param("queryMap") Map<String, String> queryMap);

    /**
     * 获取deliverTime前发货的订单
     * 
     * @param deliverTime
     * @return
     */
    List<Orders> getUnfinishedOrders(@Param("deliverTime") String deliverTime);

    Integer queryCount(Map<String, Object> map);

    List<Orders> queryList(Map<String, Object> map);

    /**
     * 获取deliverTime前发货的订单
     * 
     * @param cancelTime
     * @return
     */
    List<Orders> getUnPaiedOrders(@Param("cancelTime") String cancelTime);

    /**
     * 根据商家ID获取商家的销售总金额
     * @param sellerId
     * @return
     */
    OrderDayDto getSumMoneyOrderBySellerId(Integer sellerId);

    /**
     * 根据商家ID查询订单量
     * @param sellerId
     * @return
     */
    Integer getCountBySellerId(Integer sellerId);
}
