package com.ejavashop.dao.shop.write.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.order.OrdersProduct;

@Repository
public interface OrdersProductWriteDao {

    OrdersProduct get(Integer id);

    /**
     * 根据订单ID和商家ID查询网单
     * @param ordersId 订单ID
     * @param sellerId 商家ID
     * @return
     */
    List<OrdersProduct> getByOrderIdAndSellerId(@Param("ordersId") Integer ordersId,
                                                @Param("sellerId") Integer sellerId);

    /**
     * 根据订单ID获取网单
     * @param ordersId
     * @return
     */
    List<OrdersProduct> getByOrderId(@Param("ordersId") Integer ordersId);

    Integer insert(OrdersProduct ordersProduct);

    Integer update(OrdersProduct ordersProduct);

    Integer getCount(Map<String, Object> queryMap);

    List<OrdersProduct> page(Map<String, Object> queryMap);

    Integer del(Integer id);

}
