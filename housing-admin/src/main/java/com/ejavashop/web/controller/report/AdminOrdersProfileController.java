package com.ejavashop.web.controller.report;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.echarts.util.EchartsDataProvider;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.report.IStatisticsService;
import com.ejavashop.web.controller.BaseController;

@Controller
@RequestMapping(value = "admin/report/orders")
public class AdminOrdersProfileController extends BaseController {
    @Resource
    private IOrdersService     ordersService;
    @Resource
    private IStatisticsService statisticsService;

    /**
     * 订单概况<br>
     * 默认统计自当前年开始至当前时间的数据
     * @param request
     * @param dataMap
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "orderOverview", method = { RequestMethod.GET })
    public String orderOverview(HttpServletRequest request, ModelMap dataMap, String startTime,
                                String endTime) throws Exception {
        //默认当前年
        Calendar cal = Calendar.getInstance();
        Calendar cur = Calendar.getInstance();
        cur.clear();
        cur.set(Calendar.YEAR, cal.get(Calendar.YEAR));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //今年第一天
        String oneday = sdf.format(cur.getTime());
        //当前时间
        String now = sdf.format(cal.getTime());

        startTime = isNull(startTime) ? oneday : startTime;
        endTime = isNull(endTime) ? now : endTime;

        Map<String, String> map = new HashMap<String, String>();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        ServiceResult<List<Object>> serviceResult = statisticsService.getOrderOverviewData(map);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        dataMap.put("option", EchartsDataProvider.getOrderOverviewData(serviceResult.getResult(),
            startTime, endTime));
        dataMap.put("startTime", startTime);
        dataMap.put("endTime", endTime);
        return "admin/report/orders/orderOverview";
    }
}
