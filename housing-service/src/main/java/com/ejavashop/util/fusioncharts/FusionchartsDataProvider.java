package com.ejavashop.util.fusioncharts;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ejavashop.entity.order.Orders;

/**
 * fusioncharts图表XML数据提供工具类
 *                       
 * @Filename: FusionchartsDataProvider.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihl@vip.qq.com
 *
 */
public class FusionchartsDataProvider {

    /**
     * 订单统计xml数据
     * @param orderlist
     * @param endTime 
     * @param startTime 
     * @return
     */
    public static String orderStatistics(List<Orders> orderlist, String startTime, String endTime) {
        StringBuffer sb = new StringBuffer();
        sb.append("<chart palette='4' decimals='0' enableSmartLabels='1' ");
        sb.append("enableRotation='0' bgColor='99CCFF,FFFFFF' ")
            .append("bgAlpha='40,100' bgRatio='0,100' bgAngle='360' ")
            .append("showBorder='1' startingAngle='70' baseFont='Arial' ")
            .append("baseFontSize='12' ");
        if (startTime != null && !"".equals(startTime) && endTime != null && !"".equals(endTime))
            sb.append("caption='").append("[").append(startTime).append("]-[").append(endTime)
                .append("]").append("订单数据统计' ");
        else
            sb.append("caption='订单数据统计' ");
        sb.append("showFCMenuItem='0'>");
        //订单状态：1、未付款的订单；2、待确认的订单；3、待发货的订单；4、已发货的订单；5、已完成的订单；6、取消的订单
        int num1 = 0;
        int num2 = 0;
        int num3 = 0;
        int num4 = 0;
        int num5 = 0;
        int num6 = 0;
        if (orderlist != null && orderlist.size() > 0) {
            for (Orders vo : orderlist) {
                switch (vo.getOrderState()) {
                    case Orders.ORDER_STATE_1:
                        num1++;
                        break;
                    case Orders.ORDER_STATE_2:
                        num2++;
                        break;
                    case Orders.ORDER_STATE_3:
                        num3++;
                        break;
                    case Orders.ORDER_STATE_4:
                        num4++;
                        break;
                    case Orders.ORDER_STATE_5:
                        num5++;
                        break;
                    case Orders.ORDER_STATE_6:
                        num6++;
                        break;
                    default:
                        break;
                }
            }
            sb.append("<set label='未付款的订单' value='").append(num1).append("' />");
            sb.append("<set label='待确认的订单' value='").append(num2).append("' isSliced='1' />");
            sb.append("<set label='待发货的订单' value='").append(num3).append("' isSliced='1' />");
            sb.append("<set label='已发货的订单' value='").append(num4).append("' />");
            sb.append("<set label='已完成的订单' value='").append(num5).append("' />");
            sb.append("<set label='取消的订单' value='").append(num6).append("' />");
        }
        sb.append("</chart>");
        return sb.toString();
    }

    /**
     * 销售统计xml数据
     * @param map
     * @param model
     * @param year
     * @param month
     * @return
     */
    public static String storeSale(Map<String, Object> map, String model, Integer year,
                                   Integer month) {
        StringBuffer sb = new StringBuffer();
        String title = "";
        String xtitle = "";
        if (model.equals("year")) {
            title = year + "年度销售概况";
            xtitle = "月份";
        } else {
            title = year + "年" + month + "月份销售概况";
            xtitle = year + "年" + month + "月";
        }
        sb.append("<chart caption='").append(title).append("' ");
        sb.append("labelDisplay='wrap' ");//X轴不旋转
        sb.append("xaxisname='").append(xtitle).append("' PYaxisname='订单数（单）' ");
        sb.append("SYAxisName='销售总额度（元）'  decimals='2'")
            .append("showPlotBorder='1'  setAdaptiveYMin='1'")
            .append("formatNumber='0' showFCMenuItem='0'")
            .append("palette='3' useRoundEdges='1' baseFont='Arial' ")
            .append("baseFontSize='12' rotateYAxisName='0'>")
            .append("<categories font='Arial' fontSize='12' fontColor='000000' >");
        if (model.equals("year")) {
            for (int i = 1; i <= 12; i++) {
                sb.append("<category label='").append(i + "月").append("' />");
            }
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            for (int i = 1; i <= day; i++) {
                sb.append("<category label='").append(i).append("' />");
            }
        }
        sb.append("</categories>");
        sb.append("<dataset>")
            .append("<dataSet seriesName='订单总数（单）' color='F6BD0F' showValues='0'>");
        if (model.equals("year")) {
            for (int i = 1; i <= 12; i++) {
                sb.append("<set value='" + map.get("orderNum" + i) + "' />");
            }
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            for (int i = 1; i <= day; i++) {
                sb.append("<set value='" + map.get("orderNum" + i) + "' />");
            }
        }
        sb.append("</dataSet>").append("</dataset>");
        sb.append("<lineSet seriesname='销售总额度（元）' showValues='0' lineThickness='2'>");
        if (model.equals("year")) {
            for (int i = 1; i <= 12; i++) {
                sb.append("<set value='" + map.get("orderMoney" + i) + "' />");
            }
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            for (int i = 1; i <= day; i++) {
                sb.append("<set value='" + map.get("orderMoney" + i) + "' />");
            }
        }
        sb.append("</lineSet>").append("</chart>");
        return sb.toString();
    }
}
