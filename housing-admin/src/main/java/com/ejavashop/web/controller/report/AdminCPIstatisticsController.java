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

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.echarts.util.EchartsDataProvider;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.report.IStatisticsService;
import com.ejavashop.service.seller.ISellerService;
import com.ejavashop.web.controller.BaseController;

@Controller
@RequestMapping(value = "admin/report/orders")
public class AdminCPIstatisticsController extends BaseController {
    @Resource
    private IOrdersService     ordersService;
    @Resource
    private ISellerService     sellerService;
    @Resource
    private IStatisticsService statisticsService;

    /**
     * 人均消费<br>
     * <em>人均消费 = 订单总金额/会员数（购买商品的会员总数）</em>
     * @param request
     * @param dataMap
     * @param model
     * @param year
     * @param month
     * @param byseller
     * @param sel_seller
     * @return
     */
    @RequestMapping(value = "CPIstatistics", method = { RequestMethod.GET })
    public String CPIstatistics(HttpServletRequest request, ModelMap dataMap, String model,
                                Integer year, Integer month, Integer byseller, Integer sel_seller) {
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

        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);

        Map<String, String> map = new HashMap<String, String>();
        if (model.equals("year"))
            map.put("year", year + "");
        if (model.equals("month")) {
            year = cal.get(Calendar.YEAR);
            map.put("year", year + "");
            map.put("month", month + "");
        }
        if (!isNull(byseller) && byseller == 1 && !isNull(sel_seller)) {
            map.put("q_sellerId", sel_seller + "");
            dataMap.put("byseller", byseller);
            dataMap.put("sel_seller", sel_seller);
        }
        map.put("model", model);
        map.put("s_status", "3,4,5");
        ServiceResult<Map<String, List<Object>>> serviceResult = statisticsService
            .getCPIstatistics(map);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        dataMap.put("queryMap", queryMap);

        dataMap.put("option",
            EchartsDataProvider.getCPIstatistics(serviceResult.getResult(), model));
        dataMap.put("currentYear", year);
        dataMap.put("currentMonth", month < 10 ? "0" + month : month);
        dataMap.put("model", model);
        ServiceResult<List<Seller>> sellers = sellerService
            .getSellers(new HashMap<String, String>(), null);
        dataMap.put("sellers", sellers.getResult());
        dataMap.put("theme", "infographic");
        return "admin/report/orders/CPIstatistics";
    }
}
