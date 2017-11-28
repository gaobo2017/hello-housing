package com.ejavashop.dao.shop.write.order;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.order.OrderLog;

@Repository
public interface OrderLogWriteDao {

    OrderLog get(Integer id);

    Integer save(OrderLog orderLog);

    Integer update(OrderLog orderLog);

    Integer getCount(Map<String, Object> queryMap);

    List<OrderLog> page(Map<String, Object> queryMap);

    Integer del(Integer id);

}
