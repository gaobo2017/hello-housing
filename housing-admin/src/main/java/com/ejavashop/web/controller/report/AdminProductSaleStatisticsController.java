package com.ejavashop.web.controller.report;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.dto.ProductSaleDto;
import com.ejavashop.echarts.util.EchartsDataProvider;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.report.IStatisticsService;
import com.ejavashop.service.seller.ISellerService;
import com.ejavashop.web.controller.BaseController;

/**
 * 商品销量统计
 *                       
 * @Filename: AdminProductSaleStatisticsController.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihl_0@126.com
 *
 */
@Controller
@RequestMapping(value = "admin/report/product")
public class AdminProductSaleStatisticsController extends BaseController {
    @Resource
    private ISellerService     sellerService;
    @Resource
    private IOrdersService     ordersService;
    @Resource
    private IStatisticsService statisticsService;

    @RequestMapping(value = "productSale", method = RequestMethod.GET)
    public String productSale(HttpServletRequest request, ModelMap dataMap, String model,
                              Integer year, Integer month, Integer productId, String proName) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        //默认按年统计
        if (isNull(model) || "".equals(model))
            model = "year";

        //默认当前时间
        if (isNull(year) && isNull(month)) {
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH) + 1;
        }

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        dataMap.put("queryMap", queryMap);
        if (model.equals("year"))
            queryMap.put("year", year + "");
        if (model.equals("month")) {
            year = cal.get(Calendar.YEAR);
            queryMap.put("year", year + "");
            queryMap.put("month", month + "");
        }

        queryMap.put("model", model);
        queryMap.put("status", "3,4,5");
        if (!isNull(productId)) {
            queryMap.put("productId", productId + "");
        }
        ServiceResult<List<ProductSaleDto>> serviceResult = statisticsService
            .getProductSale(queryMap);
        dataMap.put("option", EchartsDataProvider.getProductSale(serviceResult.getResult(), model));
        dataMap.put("currentYear", year);
        dataMap.put("currentMonth", month < 10 ? "0" + month : month);
        dataMap.put("proName", proName);
        dataMap.put("productId", productId);
        dataMap.put("model", model);
        ServiceResult<List<Seller>> sellers = sellerService
            .getSellers(new HashMap<String, String>(), null);
        dataMap.put("sellers", sellers.getResult());
        return "admin/report/product/productSale";
    }
}
