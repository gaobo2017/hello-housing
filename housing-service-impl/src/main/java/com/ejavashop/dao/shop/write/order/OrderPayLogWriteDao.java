package com.ejavashop.dao.shop.write.order;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.order.OrderPayLog;

@Repository
public interface OrderPayLogWriteDao {

    OrderPayLog get(java.lang.Integer id);

    Integer save(OrderPayLog orderPayLog);

    Integer update(OrderPayLog orderPayLog);

    Integer queryCount(Map<String, Object> map);

    List<OrderPayLog> queryList(Map<String, Object> map);

}
