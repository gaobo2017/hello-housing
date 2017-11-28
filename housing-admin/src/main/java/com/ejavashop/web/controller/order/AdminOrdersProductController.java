package com.ejavashop.web.controller.order;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.order.OrdersProduct;
import com.ejavashop.service.order.IOrdersProductService;
import com.ejavashop.web.controller.BaseController;

/**
 * 网单
 *
 */
@Controller
@RequestMapping(value = "admin/order/ordersProduct")
public class AdminOrdersProductController extends BaseController {
    @Resource
    private IOrdersProductService ordersProductService;

    //    @RequestMapping(value = "getOrdersProduct", method = { RequestMethod.GET })
    //    public void getOrdersProduct(HttpServletRequest request, HttpServletResponse response,
    //                                 Integer orderId) {
    //        ServiceResult<List<OrdersProduct>> res = ordersProductService
    //            .getOrdersProductByOId(orderId);
    //        PrintWriter pw = null;
    //        try {
    //            response.setContentType("text/html;charset=utf-8");
    //            pw = response.getWriter();
    //            HttpJsonResult<List<OrdersProduct>> json = new HttpJsonResult<List<OrdersProduct>>();
    //            json.setRows(res.getResult());
    //            json.setTotal(res.getResult().size());
    //            pw.write(JSON.toJSONString(json));
    //            pw.flush();
    //            pw.close();
    //        } catch (IOException e) {
    //            log.error(
    //                "[" + this.getClass().getName() + "][getOrdersProduct] 调用出错" + e.getMessage());
    //            e.printStackTrace();
    //        }
    //    }

    @RequestMapping(value = "getOrdersProduct", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<OrdersProduct>> getOrdersProduct(HttpServletRequest request,
                                                                              HttpServletResponse response,
                                                                              Integer orderId) {
        ServiceResult<List<OrdersProduct>> res = ordersProductService
            .getOrdersProductByOId(orderId);
        HttpJsonResult<List<OrdersProduct>> json = new HttpJsonResult<List<OrdersProduct>>();
        json.setRows(res.getResult());
        json.setTotal(res.getResult().size());
        return json;
    }
}
