package com.ejavashop.service.order;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.dto.ProductDayDto;
import com.ejavashop.entity.order.OrdersProduct;

public interface IOrdersProductService {

    /**
     * 根据id取得网单表
     * @param  ordersProductId
     * @return
     */
    ServiceResult<OrdersProduct> getOrdersProductById(Integer ordersProductId);

    /**
     * 保存网单表
     * @param  ordersProduct
     * @return
     */
    ServiceResult<Integer> saveOrdersProduct(OrdersProduct ordersProduct);

    /**
    * 更新网单表
    * @param  ordersProduct
    * @return
    */
    ServiceResult<Integer> updateOrdersProduct(OrdersProduct ordersProduct);

    /**
    * 分页查询
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<OrdersProduct>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    ServiceResult<Boolean> del(Integer id);

    /**
     * 根据订单号获取对应的网单
     * @param orderId
     * @return
     */
    ServiceResult<List<OrdersProduct>> getOrdersProductByOId(Integer orderId);

    /**
     * 根据id 取得网单信息
     * @param request
     * @return
     */
    ServiceResult<OrdersProduct> getOrdersProductWithImgById(Integer orderProductId);

    /**
     * 统计商品每天销量
     * @param queryMap
     * @return
     */
    ServiceResult<List<ProductDayDto>> getProductDayDto(Map<String, String> queryMap);

}