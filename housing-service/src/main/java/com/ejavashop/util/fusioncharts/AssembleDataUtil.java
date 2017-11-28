package com.ejavashop.util.fusioncharts;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ejavashop.entity.order.Orders;

/**
 * fusioncharts数据按年份月份组装工具类
 *                       
 * @Filename: AssembleDataUtil.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihl@vip.qq.com
 *
 */
public class AssembleDataUtil {

    /**
     * 月份订单数和销售额度
     * @param orderlist
     * @return
     */
    public static Map<String, Object> putData4YM(List<Orders> orderlist, String model) {
        if (model.equals("year")) {
            return putYearData(orderlist);
        } else if (model.equals("month")) {
            return putMonthData(orderlist);

        } else {
            return new HashMap<String, Object>();
        }
    }

    private static Map<String, Object> putMonthData(List<Orders> orderlist) {
        Map<String, Object> map = new HashMap<String, Object>();

        Calendar cal = Calendar.getInstance();
        //初始化月份值
        int maxday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= maxday; i++) {
            map.put("orderNum" + i, 0);
            map.put("orderMoney" + i, 0.0);
        }
        for (Orders vo : orderlist) {
            if (vo.getFinishTime() == null)
                continue;
            cal.setTime(vo.getFinishTime());
            int day = cal.get(Calendar.DAY_OF_MONTH);
            map.put("orderNum" + day, ((Integer) map.get("orderNum" + day)) + 1);
            map.put("orderMoney" + day, vo.getMoneyOrder()
                .add(new BigDecimal((Double) map.get("orderMoney" + day))).doubleValue());

        }
        return map;
    }

    private static Map<String, Object> putYearData(List<Orders> orderlist) {
        Map<String, Object> map = new HashMap<String, Object>();
        int orderNum1 = 0;
        int orderNum2 = 0;
        int orderNum3 = 0;
        int orderNum4 = 0;
        int orderNum5 = 0;
        int orderNum6 = 0;
        int orderNum7 = 0;
        int orderNum8 = 0;
        int orderNum9 = 0;
        int orderNum10 = 0;
        int orderNum11 = 0;
        int orderNum12 = 0;
        double orderMoney1 = 0;
        double orderMoney2 = 0;
        double orderMoney3 = 0;
        double orderMoney4 = 0;
        double orderMoney5 = 0;
        double orderMoney6 = 0;
        double orderMoney7 = 0;
        double orderMoney8 = 0;
        double orderMoney9 = 0;
        double orderMoney10 = 0;
        double orderMoney11 = 0;
        double orderMoney12 = 0;
        for (Orders vo : orderlist) {
            Calendar cal = Calendar.getInstance();
            if (vo.getFinishTime() == null)
                continue;
            cal.setTime(vo.getFinishTime());
            //month从0开始
            switch (cal.get(Calendar.MONTH)) {
                case 0:
                    orderNum1++;
                    orderMoney1 = new BigDecimal(orderMoney1).add(vo.getMoneyOrder()).doubleValue();
                    break;
                case 1:
                    orderNum2++;
                    orderMoney2 = new BigDecimal(orderMoney2).add(vo.getMoneyOrder()).doubleValue();
                    break;
                case 2:
                    orderNum3++;
                    orderMoney3 = new BigDecimal(orderMoney3).add(vo.getMoneyOrder()).doubleValue();
                    break;
                case 3:
                    orderNum4++;
                    orderMoney4 = new BigDecimal(orderMoney4).add(vo.getMoneyOrder()).doubleValue();
                    break;
                case 4:
                    orderNum5++;
                    orderMoney5 = new BigDecimal(orderMoney5).add(vo.getMoneyOrder()).doubleValue();
                    break;
                case 5:
                    orderNum6++;
                    orderMoney6 = new BigDecimal(orderMoney6).add(vo.getMoneyOrder()).doubleValue();
                    break;
                case 6:
                    orderNum7++;
                    orderMoney7 = new BigDecimal(orderMoney7).add(vo.getMoneyOrder()).doubleValue();
                    break;
                case 7:
                    orderNum8++;
                    orderMoney8 = new BigDecimal(orderMoney8).add(vo.getMoneyOrder()).doubleValue();
                    break;
                case 8:
                    orderNum9++;
                    orderMoney9 = new BigDecimal(orderMoney9).add(vo.getMoneyOrder()).doubleValue();
                    break;
                case 9:
                    orderNum10++;
                    orderMoney10 = new BigDecimal(orderMoney10).add(vo.getMoneyOrder())
                        .doubleValue();
                    break;
                case 10:
                    orderNum11++;
                    orderMoney11 = new BigDecimal(orderMoney11).add(vo.getMoneyOrder())
                        .doubleValue();
                    break;
                case 11:
                    orderNum12++;
                    orderMoney12 = new BigDecimal(orderMoney12).add(vo.getMoneyOrder())
                        .doubleValue();
                    break;
                default:
                    break;
            }
        }
        map.put("orderNum1", orderNum1);
        map.put("orderNum2", orderNum2);
        map.put("orderNum3", orderNum3);
        map.put("orderNum4", orderNum4);
        map.put("orderNum5", orderNum5);
        map.put("orderNum6", orderNum6);
        map.put("orderNum7", orderNum7);
        map.put("orderNum8", orderNum8);
        map.put("orderNum9", orderNum9);
        map.put("orderNum10", orderNum10);
        map.put("orderNum11", orderNum11);
        map.put("orderNum12", orderNum12);

        map.put("orderMoney1", orderMoney1);
        map.put("orderMoney2", orderMoney2);
        map.put("orderMoney3", orderMoney3);
        map.put("orderMoney4", orderMoney4);
        map.put("orderMoney5", orderMoney5);
        map.put("orderMoney6", orderMoney6);
        map.put("orderMoney7", orderMoney7);
        map.put("orderMoney8", orderMoney8);
        map.put("orderMoney9", orderMoney9);
        map.put("orderMoney10", orderMoney10);
        map.put("orderMoney11", orderMoney11);
        map.put("orderMoney12", orderMoney12);

        return map;
    }
}
