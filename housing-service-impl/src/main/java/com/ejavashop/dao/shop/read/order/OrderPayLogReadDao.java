package com.ejavashop.dao.shop.read.order;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.order.OrderPayLog;

@Repository
public interface OrderPayLogReadDao {

    OrderPayLog get(java.lang.Integer id);

    Integer queryCount(Map<String, Object> map);

    List<OrderPayLog> queryList(Map<String, Object> map);

}
