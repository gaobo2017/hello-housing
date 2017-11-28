package com.ejavashop.dao.shop.read.order;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.order.OrderLog;

@Repository
public interface OrderLogReadDao {

    OrderLog get(java.lang.Integer id);

    Integer queryCount(Map<String, Object> map);

    List<OrderLog> queryList(Map<String, Object> map);

}
