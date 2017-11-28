package com.ejavashop.echarts.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ejavashop.core.JsonUtil;
import com.ejavashop.dto.PhurchaseRateDto;
import com.ejavashop.dto.ProductSaleDto;
import com.ejavashop.echarts.component.Legend;
import com.ejavashop.echarts.component.Option;
import com.ejavashop.echarts.component.Series;
import com.ejavashop.echarts.component.Series.MarkLine.MarkLineData;
import com.ejavashop.echarts.component.Series.MarkPoint.MarkPointData;
import com.ejavashop.echarts.component.SeriesData;
import com.ejavashop.echarts.component.Title;
import com.ejavashop.echarts.component.Toolbox;
import com.ejavashop.echarts.component.Tooltip;
import com.ejavashop.echarts.component.XAxis;
import com.ejavashop.echarts.component.YAxis;

/**
 * 用以echarts图表数据填充
 *                       
 * @Filename: DataFormatUtil.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihl_0@126.com
 *
 */
public class EchartsDataProvider {

    /**
     * 订单销量统计
     * @param data
     * @param model
     * @return
     */
    public static String getStoreSaleData(Map<String, List<Object>> data, String model) {
        Option opt = new Option();
        opt.setAnimationDuration(1000);

        //提示栏
        Tooltip tooltip = new Tooltip();
        opt.setTooltip(tooltip);

        //操作栏
        Toolbox toolbox = new Toolbox();
        Toolbox.Feature feature = toolbox.new Feature();
        Toolbox.Feature.DataView dataview = feature.new DataView();
        dataview.setReadOnly(false);
        //初始化magicType
        Toolbox.Feature.MagicType magicType = feature.new MagicType();
        magicType.setType(Arrays.asList("line", "bar"));
        //初始化Restore
        Toolbox.Feature.Restore restore = feature.new Restore();
        feature.setDataView(dataview);
        feature.setMagicType(magicType);
        Toolbox.Feature.SaveAsImage img = feature.new SaveAsImage();
        feature.setSaveAsImage(img);
        feature.setRestore(restore);

        toolbox.setFeature(feature);
        opt.setToolbox(toolbox);

        Legend legend = new Legend();
        legend.setData(Arrays.asList("订单数", "订单总额", "客单价"));
        opt.setLegend(legend);

        XAxis xAxis = new XAxis();
        xAxis.setType("category");
        XAxis.SplitLine splitLine = xAxis.new SplitLine();
        splitLine.setShow(false);
        xAxis.setSplitLine(splitLine);
        xAxis.setData(getXText(model));
        opt.setxAxis(xAxis);

        List<YAxis> yaxislist = new ArrayList<YAxis>();
        YAxis yAxis1 = new YAxis();
        yAxis1.setName("金额");
        yAxis1.setType("value");
        YAxis.AxisLabel al = yAxis1.new AxisLabel();
        al.setFormatter("{value} 元");
        yAxis1.setAxisLabel(al);
        yaxislist.add(yAxis1);

        YAxis yAxis2 = new YAxis();
        yAxis2.setName("数量");
        yAxis2.setType("value");
        YAxis.AxisLabel al2 = yAxis2.new AxisLabel();
        al2.setFormatter("{value} 单");
        yAxis2.setAxisLabel(al2);
        yaxislist.add(yAxis2);

        opt.setyAxis(yaxislist);

        //圆角
        List<Float> barBorderRadius = Arrays.asList(25f, 25f, 0f, 0f);

        List<Series> serieslist = new ArrayList<Series>();
        Series series1 = new Series();
        series1.setName("订单总额");
        series1.setType("bar");
        Series.ItemStyle is1 = series1.new ItemStyle();
        Series.ItemStyle.Normal normal1 = is1.new Normal();
        normal1.setBarBorderRadius(barBorderRadius);
        is1.setNormal(normal1);
        series1.setItemStyle(is1);
        series1.setData(data.get("orderMoney"));
        serieslist.add(series1);

        //标注
        Map<String, Double> max = getMarkPointMap(data.get("orderMoney"));
        if (max.get("value") > 0) {
            Series.MarkPoint mp = series1.new MarkPoint();
            Series.MarkPoint.MarkPointData mpd = mp.new MarkPointData();
            mpd.setName("year".equals(model) ? "年最高" : "月最高");
            mpd.setValue(max.get("value"));
            mpd.setxAxis(max.get("xAxis"));
            mpd.setyAxis(max.get("value"));
            List<MarkPointData> mpdlist = new ArrayList<MarkPointData>();
            mpdlist.add(mpd);
            mp.setData(mpdlist);
            series1.setMarkPoint(mp);
        }

        Series series2 = new Series();
        series2.setName("客单价");
        series2.setType("bar");
        Series.ItemStyle is2 = series2.new ItemStyle();
        Series.ItemStyle.Normal normal2 = is2.new Normal();
        normal2.setBarBorderRadius(barBorderRadius);
        is2.setNormal(normal2);
        series2.setItemStyle(is2);

        if (max.get("value") > 0) {
            //平均值
            Series.MarkLine ml = series2.new MarkLine();
            Series.MarkLine.MarkLineData mld = ml.new MarkLineData();
            mld.setName("平均值");
            mld.setType("average");
            List<MarkLineData> mldlist = new ArrayList<MarkLineData>();
            mldlist.add(mld);
            ml.setData(mldlist);
            series2.setMarkLine(ml);
        }

        series2.setData(data.get("kdj"));
        serieslist.add(series2);

        Series series3 = new Series();
        series3.setName("订单数");
        series3.setType("line");
        series3.setSmooth(true);

        Series.LineStyle linestyle = series3.new LineStyle();
        Series.LineStyle.Normal norm3 = linestyle.new Normal();
        norm3.setShadowBlur(10);
        norm3.setShadowColor("#FFEFD5");
        norm3.setWidth(3);
        norm3.setShadowOffsetY(10);
        linestyle.setNormal(norm3);
        series3.setLineStyle(linestyle);

        series3.setyAxisIndex(1);

        series3.setData(data.get("orderNum"));
        serieslist.add(series3);

        opt.setSeries(serieslist);

        return format2Json(opt);
    }

    /**
     * 订单销量标注值
     * @param list
     * @return
     */
    private static Map<String, Double> getMarkPointMap(List<Object> list) {
        Map<String, Double> map = new HashMap<String, Double>();
        Double max = (Double) list.get(0);
        int idx = 0;
        for (int i = 0; i < list.size(); i++) {
            if (max < (Double) list.get(i)) {
                max = (Double) list.get(i);
                idx = i;
            }
        }
        map.put("value", max);
        map.put("xAxis", new Double(idx));
        return map;
    }

    /**
     * 订单概况统计
     * @param data
     * @param startTime
     * @param endTime
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static String getOrderOverviewData(List<Object> data, String startTime, String endTime) {
        Option opt = new Option();

        Title title = new Title();
        title.setText("订单概况统计");
        title.setSubtext(startTime + "-" + endTime);
        title.setX("center");
        opt.setTitle(title);

        //提示栏
        Tooltip tooltip = new Tooltip();
        tooltip.setTrigger("item");
        tooltip.setFormatter("{a} <br/>{b} : {c} ({d}%)");
        opt.setTooltip(tooltip);

        //操作栏
        Toolbox toolbox = new Toolbox();
        Toolbox.Feature feature = toolbox.new Feature();
        feature.setDataView(feature.new DataView());
        Toolbox.Feature.SaveAsImage img = feature.new SaveAsImage();
        feature.setSaveAsImage(img);

        toolbox.setFeature(feature);
        opt.setToolbox(toolbox);

        Legend legend = new Legend();
        legend.setOrient("vertical");
        legend.setX("left");
        List<SeriesData> sdlist = (List) data;
        List<String> datalist = new ArrayList<String>();
        for (SeriesData sd : sdlist) {
            datalist.add(sd.getName());
        }
        legend.setData(datalist);
        opt.setLegend(legend);

        List<Series> serieslist = new ArrayList<Series>();
        Series series = new Series();
        series.setName("订单概况");
        series.setType("pie");
        series.setRadius("76%");
        series.setCenter(Arrays.asList("50%", "60%"));
        Series.ItemStyle is = series.new ItemStyle();
        Series.ItemStyle.Emphasis emphasis = is.new Emphasis();
        emphasis.setShadowBlur(10);
        emphasis.setShadowOffsetX(0);
        emphasis.setShadowColor("rgba(0, 0, 0, 0.5)");
        is.setEmphasis(emphasis);
        series.setItemStyle(is);
        series.setData(data);
        serieslist.add(series);

        opt.setSeries(serieslist);

        return format2Json(opt);
    }

    /**
     * 购买率统计
     * @param month 
     * @param year 
     * @param arrayList
     * @return
     */
    public static String getPhurchaseRateData(PhurchaseRateDto dto, String model, Integer year,
                                              Integer month) {
        Option opt = new Option();
        opt.setAnimationDuration(1000);

        Title title = new Title();
        title.setSubtext("year".equals(model) ? year + "年数据" : year + "年" + month + "月数据");
        title.setX("center");
        opt.setTitle(title);

        //提示栏
        Tooltip tooltip = new Tooltip();
        opt.setTooltip(tooltip);

        //操作栏
        Toolbox toolbox = new Toolbox();
        Toolbox.Feature feature = toolbox.new Feature();
        Toolbox.Feature.DataView dataview = feature.new DataView();
        dataview.setReadOnly(false);
        //初始化magicType
        Toolbox.Feature.MagicType magicType = feature.new MagicType();
        magicType.setType(Arrays.asList("line", "bar"));
        //初始化Restore
        Toolbox.Feature.Restore restore = feature.new Restore();
        feature.setDataView(dataview);
        feature.setMagicType(magicType);
        Toolbox.Feature.SaveAsImage img = feature.new SaveAsImage();
        feature.setSaveAsImage(img);
        feature.setRestore(restore);

        toolbox.setFeature(feature);
        opt.setToolbox(toolbox);

        Legend legend = new Legend();
        legend.setData(Arrays.asList("访客数", "有效订单数", "购买率"));
        opt.setLegend(legend);

        XAxis xAxis = new XAxis();
        xAxis.setType("category");
        XAxis.SplitLine splitLine = xAxis.new SplitLine();
        splitLine.setShow(false);
        xAxis.setSplitLine(splitLine);
        xAxis.setData(getXText(model));
        opt.setxAxis(xAxis);

        List<YAxis> yaxislist = new ArrayList<YAxis>();
        YAxis yAxis1 = new YAxis();
        yAxis1.setName("访问人数");
        yAxis1.setType("value");
        YAxis.AxisLabel al = yAxis1.new AxisLabel();
        al.setFormatter("{value} 人");
        yAxis1.setAxisLabel(al);
        yaxislist.add(yAxis1);

        YAxis yAxis2 = new YAxis();
        yAxis2.setName("购买率");
        yAxis2.setType("value");
        YAxis.AxisLabel al2 = yAxis2.new AxisLabel();
        al2.setFormatter("{value} %");
        yAxis2.setAxisLabel(al2);
        yaxislist.add(yAxis2);

        opt.setyAxis(yaxislist);

        List<Series> serieslist = new ArrayList<Series>();
        Series series1 = new Series();
        series1.setName("访客数");
        series1.setType("bar");
        series1.setStack("s1");

        series1.setData(dto.getPvList());
        serieslist.add(series1);

        Series series2 = new Series();
        series2.setName("有效订单数");
        series2.setType("bar");
        series2.setStack("s1");
        series2.setyAxisIndex(1);
        Series.ItemStyle is2 = series2.new ItemStyle();
        Series.ItemStyle.Normal normal2 = is2.new Normal();
        List<Float> barBorderRadius = new ArrayList<Float>();
        normal2.setColor("#ea3");
        barBorderRadius.add(25f);
        barBorderRadius.add(25f);
        barBorderRadius.add(0f);
        barBorderRadius.add(0f);
        normal2.setBarBorderRadius(barBorderRadius);
        is2.setNormal(normal2);
        series2.setItemStyle(is2);
        series2.setData(dto.getOrderNumList());
        series2.setyAxisIndex(0);
        serieslist.add(series2);

        Series series3 = new Series();
        series3.setName("购买率");
        series3.setType("line");
        series3.setSmooth(true);
        Series.ItemStyle is3 = series3.new ItemStyle();
        Series.ItemStyle.Normal normal3 = is3.new Normal();
        normal3.setColor("#66CDAA");
        is3.setNormal(normal3);
        series3.setItemStyle(is3);

        Series.LineStyle linestyle = series3.new LineStyle();
        Series.LineStyle.Normal norm3 = linestyle.new Normal();
        norm3.setShadowBlur(10);
        norm3.setShadowColor("#FFEFD5");
        norm3.setWidth(3);
        norm3.setShadowOffsetY(10);
        linestyle.setNormal(norm3);
        series3.setLineStyle(linestyle);

        series3.setyAxisIndex(1);

        series3.setData(dto.getRateList());
        serieslist.add(series3);

        opt.setSeries(serieslist);

        return format2Json(opt);
    }

    /**
     * x轴填充数据
     * @param model
     * @return
     */
    private static List<String> getXText(String model) {
        List<String> list = new ArrayList<String>();
        Calendar cal = Calendar.getInstance();
        if ("year".equals(model)) {
            int month = cal.getActualMaximum(Calendar.MONTH) + 1;
            for (int i = 1; i <= month; i++) {
                list.add(i + "月");
            }
        } else {
            int maxday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            for (int i = 1; i <= maxday; i++) {
                list.add(i + "");
            }
        }
        return list;
    }

    /**
     * 格式化为json数据
     * @param opt
     * @return
     */
    public static String format2Json(Option opt) {
        return JsonUtil.toJson(opt);
    }

    /**
     * 退货率统计数据
     * @param data
     * @param model
     * @return
     */
    public static Object goodsReturnRate(Map<String, List<Object>> data, String model) {
        Option opt = new Option();
        opt.setAnimationDuration(1000);

        //提示栏
        Tooltip tooltip = new Tooltip();
        opt.setTooltip(tooltip);

        //操作栏
        Toolbox toolbox = new Toolbox();
        Toolbox.Feature feature = toolbox.new Feature();
        Toolbox.Feature.DataView dataview = feature.new DataView();
        dataview.setReadOnly(false);
        //初始化magicType
        Toolbox.Feature.MagicType magicType = feature.new MagicType();
        magicType.setType(Arrays.asList("line", "bar", "stack", "tiled"));
        //初始化Restore
        Toolbox.Feature.Restore restore = feature.new Restore();
        feature.setDataView(dataview);
        feature.setMagicType(magicType);
        Toolbox.Feature.SaveAsImage img = feature.new SaveAsImage();
        feature.setSaveAsImage(img);
        feature.setRestore(restore);

        toolbox.setFeature(feature);
        opt.setToolbox(toolbox);

        Legend legend = new Legend();
        legend.setData(Arrays.asList("退货数", "总订单数", "退货率"));
        opt.setLegend(legend);

        XAxis xAxis = new XAxis();
        xAxis.setType("category");
        XAxis.SplitLine splitLine = xAxis.new SplitLine();
        splitLine.setShow(false);
        xAxis.setSplitLine(splitLine);
        xAxis.setData(getXText(model));
        opt.setxAxis(xAxis);

        List<YAxis> yaxislist = new ArrayList<YAxis>();
        YAxis yAxis1 = new YAxis();
        yAxis1.setName("数量");
        yAxis1.setType("value");
        YAxis.AxisLabel al = yAxis1.new AxisLabel();
        al.setFormatter("{value} 单");
        yAxis1.setAxisLabel(al);
        yaxislist.add(yAxis1);

        YAxis yAxis2 = new YAxis();
        yAxis2.setName("退货率");
        yAxis2.setType("value");
        YAxis.AxisLabel al2 = yAxis2.new AxisLabel();
        al2.setFormatter("{value} %");
        yAxis2.setAxisLabel(al2);
        yaxislist.add(yAxis2);

        opt.setyAxis(yaxislist);

        List<Series> serieslist = new ArrayList<Series>();
        Series series1 = new Series();
        series1.setName("总订单数");
        series1.setType("bar");
        series1.setStack("s1");
        series1.setData(data.get("orderNum"));
        serieslist.add(series1);

        Series series2 = new Series();
        series2.setName("退货数");
        series2.setType("bar");
        series2.setStack("s1");
        Series.ItemStyle is2 = series2.new ItemStyle();
        Series.ItemStyle.Normal normal2 = is2.new Normal();
        normal2.setBarBorderRadius(Arrays.asList(25f, 25f, 0f, 0f));
        is2.setNormal(normal2);
        series2.setItemStyle(is2);
        series2.setData(data.get("goodsreturnNum"));
        serieslist.add(series2);

        Series series3 = new Series();
        series3.setName("退货率");
        series3.setType("line");
        series3.setSmooth(true);
        Series.ItemStyle is3 = series3.new ItemStyle();
        Series.ItemStyle.Normal normal3 = is3.new Normal();
        is3.setNormal(normal3);
        series3.setItemStyle(is3);

        Series.LineStyle linestyle = series3.new LineStyle();
        Series.LineStyle.Normal norm3 = linestyle.new Normal();
        norm3.setShadowBlur(10);
        norm3.setWidth(3);
        norm3.setShadowOffsetY(10);
        linestyle.setNormal(norm3);
        series3.setLineStyle(linestyle);

        series3.setyAxisIndex(1);

        series3.setData(data.get("rate"));
        serieslist.add(series3);

        opt.setSeries(serieslist);

        return format2Json(opt);
    }

    /**
     * 人均消费统计
     * @param data
     * @param model
     * @return
     */
    public static Object getCPIstatistics(Map<String, List<Object>> data, String model) {
        Option opt = new Option();
        opt.setAnimationDuration(1000);

        //提示栏
        Tooltip tooltip = new Tooltip();
        opt.setTooltip(tooltip);

        //操作栏
        Toolbox toolbox = new Toolbox();
        Toolbox.Feature feature = toolbox.new Feature();
        Toolbox.Feature.DataView dataview = feature.new DataView();
        dataview.setReadOnly(false);
        //初始化magicType
        Toolbox.Feature.MagicType magicType = feature.new MagicType();
        magicType.setType(Arrays.asList("line", "bar"));
        //初始化Restore
        Toolbox.Feature.Restore restore = feature.new Restore();
        feature.setDataView(dataview);
        feature.setMagicType(magicType);
        Toolbox.Feature.SaveAsImage img = feature.new SaveAsImage();
        feature.setSaveAsImage(img);
        feature.setRestore(restore);

        toolbox.setFeature(feature);
        opt.setToolbox(toolbox);

        Legend legend = new Legend();
        legend.setData(Arrays.asList("订单总金额", "购买人数", "人均消费"));
        opt.setLegend(legend);

        XAxis xAxis = new XAxis();
        xAxis.setType("category");
        XAxis.SplitLine splitLine = xAxis.new SplitLine();
        splitLine.setShow(false);
        XAxis.AxisLine aline = xAxis.new AxisLine();
        aline.setShow(true);
        XAxis.AxisLine.LineStyle ls = aline.new LineStyle();
        ls.setWidth(2);
        aline.setLineStyle(ls);
        xAxis.setAxisLine(aline);
        xAxis.setSplitLine(splitLine);
        xAxis.setData(getXText(model));
        opt.setxAxis(xAxis);

        List<YAxis> yaxislist = new ArrayList<YAxis>();
        YAxis yAxis1 = new YAxis();
        yAxis1.setName("金额");
        yAxis1.setType("value");
        YAxis.AxisLabel al = yAxis1.new AxisLabel();
        al.setFormatter("{value} 元");
        yAxis1.setAxisLabel(al);
        yaxislist.add(yAxis1);

        YAxis yAxis2 = new YAxis();
        yAxis2.setName("购买人数");
        yAxis2.setType("value");
        YAxis.AxisLabel al2 = yAxis2.new AxisLabel();
        al2.setFormatter("{value} 人");
        yAxis2.setAxisLabel(al2);
        yaxislist.add(yAxis2);

        opt.setyAxis(yaxislist);

        List<Series> serieslist = new ArrayList<Series>();
        Series series1 = new Series();
        series1.setName("订单总金额");
        series1.setType("bar");
        series1.setStack("s1");
        series1.setData(data.get("orderMoney"));

        Series.ItemStyle is1 = series1.new ItemStyle();
        Series.ItemStyle.Normal normal1 = is1.new Normal();
        normal1.setBarBorderRadius(Arrays.asList(25f, 25f, 0f, 0f));
        is1.setNormal(normal1);
        series1.setItemStyle(is1);

        serieslist.add(series1);

        Series series2 = new Series();
        series2.setName("人均消费");
        series2.setType("line");
        //平均值
        Series.MarkLine ml = series2.new MarkLine();
        Series.MarkLine.MarkLineData mld = ml.new MarkLineData();
        mld.setName("平均值");
        mld.setType("average");
        List<MarkLineData> mldlist = new ArrayList<MarkLineData>();
        mldlist.add(mld);
        ml.setData(mldlist);
        series2.setMarkLine(ml);

        Series.LineStyle linestyle = series2.new LineStyle();
        Series.LineStyle.Normal norm2 = linestyle.new Normal();
        norm2.setShadowBlur(10);
        norm2.setWidth(3);
        norm2.setShadowOffsetY(10);
        linestyle.setNormal(norm2);
        series2.setLineStyle(linestyle);
        series2.setSmooth(true);
        series2.setData(data.get("rate"));
        serieslist.add(series2);

        Series series3 = new Series();
        series3.setName("购买人数");
        series3.setType("line");
        series3.setyAxisIndex(1);

        Series.LineStyle ls3 = series3.new LineStyle();
        Series.LineStyle.Normal nl3 = ls3.new Normal();
        nl3.setType("dotted");
        ls3.setNormal(nl3);
        series3.setLineStyle(ls3);
        series3.setData(data.get("user"));
        series3.setSmooth(true);
        serieslist.add(series3);

        opt.setSeries(serieslist);

        return format2Json(opt);
    }

    /**
     * 商品销量统计
     * @param data
     * @param model
     * @return
     */
    public static Object getProductSale(List<ProductSaleDto> data, String model) {
        Option opt = new Option();
        opt.setAnimationDuration(1000);

        if (data == null) {
            Title title = new Title();
            title.setSubtext("请选择商品");
            title.setX("center");
            opt.setTitle(title);
        } else if (data.size() < 1) {
            Title title = new Title();
            title.setSubtext("没有数据");
            title.setX("center");
            opt.setTitle(title);
        } else {

            //提示栏
            Tooltip tooltip = new Tooltip();
            opt.setTooltip(tooltip);

            //操作栏
            Toolbox toolbox = new Toolbox();
            Toolbox.Feature feature = toolbox.new Feature();
            Toolbox.Feature.DataView dataview = feature.new DataView();
            dataview.setReadOnly(false);
            //初始化magicType
            Toolbox.Feature.MagicType magicType = feature.new MagicType();
            magicType.setType(Arrays.asList("line", "bar"));
            //初始化Restore
            Toolbox.Feature.Restore restore = feature.new Restore();
            feature.setDataView(dataview);
            feature.setMagicType(magicType);
            Toolbox.Feature.SaveAsImage img = feature.new SaveAsImage();
            feature.setSaveAsImage(img);
            feature.setRestore(restore);

            toolbox.setFeature(feature);
            opt.setToolbox(toolbox);

            Legend legend = new Legend();
            if (data != null && data.size() > 0) {
                List<String> legendtext = new ArrayList<String>();
                for (ProductSaleDto dto : data) {
                    if (dto.getIsNorm().intValue() == 1) {
                        //未启用规格
                        legendtext.add("商品");
                        break;
                    } else {
                        legendtext.add(dto.getSpecInfo());
                    }
                }
                legend.setData(legendtext);
                opt.setLegend(legend);
            }

            XAxis xAxis = new XAxis();
            xAxis.setType("category");
            xAxis.setBoundaryGap(false);

            xAxis.setData(getXText(model));
            opt.setxAxis(xAxis);

            List<YAxis> yaxislist = new ArrayList<YAxis>();
            YAxis yAxis = new YAxis();
            yAxis.setType("value");
            yAxis.setName("订单金额");
            YAxis.AxisLabel al = yAxis.new AxisLabel();
            al.setFormatter("{value} 元");
            yAxis.setAxisLabel(al);
            yaxislist.add(yAxis);

            opt.setyAxis(yaxislist);

            List<Series> serieslist = new ArrayList<Series>();
            for (ProductSaleDto dto : data) {
                Series series = new Series();
                series.setName(
                    dto.getIsNorm().intValue() == 1 ? dto.getProductName() : dto.getSpecInfo());
                series.setType("line");
                series.setStack("s1");
                series.setSmooth(true);
                series.setData(dto.getData());
                serieslist.add(series);
            }
            opt.setSeries(serieslist);
        }
        return format2Json(opt);
    }

    /**
     * 浏览量统计
     * @param result
     * @param model
     * @param year
     * @param month
     * @return
     */
    public static Object pvStatistics(List<Object> data, String model, Integer year,
                                      Integer month) {
        Option opt = new Option();
        opt.setAnimationDuration(1000);

        //提示栏
        Tooltip tooltip = new Tooltip();
        opt.setTooltip(tooltip);

        //操作栏
        Toolbox toolbox = new Toolbox();
        Toolbox.Feature feature = toolbox.new Feature();
        Toolbox.Feature.DataView dataview = feature.new DataView();
        dataview.setReadOnly(false);
        //初始化magicType
        Toolbox.Feature.MagicType magicType = feature.new MagicType();
        magicType.setType(Arrays.asList("line", "bar"));
        //初始化Restore
        Toolbox.Feature.Restore restore = feature.new Restore();
        feature.setDataView(dataview);
        feature.setMagicType(magicType);
        Toolbox.Feature.SaveAsImage img = feature.new SaveAsImage();
        feature.setSaveAsImage(img);
        feature.setRestore(restore);

        toolbox.setFeature(feature);
        opt.setToolbox(toolbox);

        XAxis xAxis = new XAxis();
        xAxis.setType("category");
        XAxis.SplitLine splitLine = xAxis.new SplitLine();
        splitLine.setShow(false);
        XAxis.AxisLine aline = xAxis.new AxisLine();
        aline.setShow(true);
        XAxis.AxisLine.LineStyle ls = aline.new LineStyle();
        ls.setWidth(2);
        aline.setLineStyle(ls);
        xAxis.setAxisLine(aline);
        xAxis.setSplitLine(splitLine);
        xAxis.setData(getXText(model));
        opt.setxAxis(xAxis);

        List<YAxis> yaxislist = new ArrayList<YAxis>();
        YAxis yAxis1 = new YAxis();
        yAxis1.setName("pv值");
        yAxis1.setType("value");
        yaxislist.add(yAxis1);
        opt.setyAxis(yaxislist);

        List<Series> serieslist = new ArrayList<Series>();
        Series series1 = new Series();
        series1.setName("浏览量");
        series1.setType("bar");
        series1.setStack("s1");
        series1.setData(data);

        Series.ItemStyle is1 = series1.new ItemStyle();
        Series.ItemStyle.Normal normal1 = is1.new Normal();
        normal1.setBarBorderRadius(Arrays.asList(25f, 25f, 0f, 0f));
        is1.setNormal(normal1);
        series1.setItemStyle(is1);

        serieslist.add(series1);
        opt.setSeries(serieslist);
        return format2Json(opt);
    }

}
