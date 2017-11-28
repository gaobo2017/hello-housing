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

import com.ejavashop.core.ServiceResult;
import com.ejavashop.echarts.util.EchartsDataProvider;
import com.ejavashop.service.report.IStatisticsService;
import com.ejavashop.web.controller.BaseController;

/**
 * 浏览量统计
 *                       
 * @Filename: AdminPVStatisticsController.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihl_0@126.com
 *
 */
@Controller
@RequestMapping(value = "admin/report/product")
public class AdminPVStatisticsController extends BaseController {
    @Resource
    private IStatisticsService statisticsService;

    @RequestMapping(value = "pvStatistics")
    public String pvStatistics(HttpServletRequest request, ModelMap dataMap, String model,
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
        ServiceResult<List<Object>> dtolist = statisticsService.pvStatistics(map);

        dataMap.put("model", model);
        dataMap.put("currentYear", year);
        dataMap.put("currentMonth", month < 10 ? "0" + month : month);
        dataMap.put("option",
            EchartsDataProvider.pvStatistics(dtolist.getResult(), model, year, month));
        System.out.println(dataMap.get("option"));
        return "admin/report/product/pvStatistics";
    }
}
