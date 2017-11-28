package com.ejavashop.dao.shop.write.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.dto.OrdersReturnDto;
import com.ejavashop.dto.ProductSaleDto;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.entity.product.Product;

@Repository
public interface OrdersWriteDao {

    Orders get(Integer id);

    Orders getByOrderSn(String orderSn);

    Integer insert(Orders orders);

    Integer update(Orders orders);

    Integer delete(Integer id);

    Integer getOrdersCount(@Param("queryMap") Map<String, String> queryMap);

    List<Orders> getOrders(@Param("queryMap") Map<String, Object> queryMap,
                           @Param("start") Integer start, @Param("size") Integer size);

    Integer updateMoneyBack(@Param("id") Integer id, @Param("moneyBack") String moneyBack);

    List<OrdersReturnDto> goodsReturnRate(Map<String, String> queryMap);

    String getOrderStateComment();

    List<ProductSaleDto> getProductSale(Map<String, Object> queryMap);

    List<Product> getProductByOrderId(Integer orderid);
}
