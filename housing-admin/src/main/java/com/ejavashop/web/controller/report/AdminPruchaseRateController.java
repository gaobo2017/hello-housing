package com.ejavashop.web.controller.report;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.dto.PhurchaseRateDto;
import com.ejavashop.echarts.util.EchartsDataProvider;
import com.ejavashop.service.report.IStatisticsService;
import com.ejavashop.web.controller.BaseController;

@Controller
@RequestMapping(value = "admin/report/product")
public class AdminPruchaseRateController extends BaseController {
    @Resource
    private IStatisticsService statisticsService;

    /**
     * 购买率统计
     * 购买率=有效订单数/总访客数
     * TODO
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "phurchaseRate", method = RequestMethod.GET)
    public String phurchaseRate(HttpServletRequest request, ModelMap dataMap, String model,
                                Integer year, Integer month) {
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

        Map<String, String> map = new HashMap<String, String>();
        if (model.equals("year"))
            map.put("year", year + "");
        if (model.equals("month")) {
            year = cal.get(Calendar.YEAR);
            map.put("year", year + "");
            map.put("month", month + "");
        }
        map.put("model", model);
        ServiceResult<PhurchaseRateDto> dtolist = statisticsService.getPhurchaseRate(map);

        dataMap.put("model", model);
        dataMap.put("currentYear", year);
        dataMap.put("currentMonth", month < 10 ? "0" + month : month);
        dataMap.put("option",
            EchartsDataProvider.getPhurchaseRateData(dtolist.getResult(), model, year, month));
        return "admin/report/product/phurchaseRate";
    }
}
