package com.ejavashop.model.report;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;

import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.analysis.read.BrowseLogReadDao;
import com.ejavashop.dao.shop.write.order.OrdersWriteDao;
import com.ejavashop.dao.shop.write.seller.SellerWriteDao;
import com.ejavashop.dto.OrdersReturnDto;
import com.ejavashop.dto.PVDto;
import com.ejavashop.dto.PhurchaseRateDto;
import com.ejavashop.dto.ProductSaleDto;
import com.ejavashop.echarts.component.SeriesData;
import com.ejavashop.entity.order.Orders;

/**
 * 统计Model
 *                       
 * @Filename: StatisticsModel.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihl_0@126.com
 *
 */
@Component(value = "statisticsModel")
public class StatisticsModel {
    @Resource
    private OrdersWriteDao   ordersWriteDao;
    @Resource
    private SellerWriteDao   sellerWriteDao;
    @Resource
    private BrowseLogReadDao browseLogReadDao;

    public List<Orders> getOrders(Map<String, String> queryMap, Integer start,
                                  Integer size) throws Exception {
        Map<String, Object> parammap = new HashMap<String, Object>(queryMap);
        List<Orders> list = ordersWriteDao.getOrders(parammap, start, size);
        for (Orders o : list) {
            o.setSellerName(sellerWriteDao.get(o.getSellerId()).getSellerName());
        }
        return list;
    }

    /**
     * 订单概况统计数据
     * @param queryMap 参数map
     * @return
     * @throws Exception
     */
    public List<Object> getOrderOverviewData(Map<String, String> queryMap) throws Exception {
        //所有订单,不分页
        List<Orders> orderlist = getOrders(queryMap, -1, -1);
        //订单状态：1、未付款的订单；2、待确认的订单；3、待发货的订单；4、已发货的订单；5、已完成的订单；6、取消的订单
        List<Object> datalist = new ArrayList<Object>();
        int num1 = 0;
        int num2 = 0;
        int num3 = 0;
        int num4 = 0;
        int num5 = 0;
        int num6 = 0;
        if (orderlist != null && orderlist.size() > 0) {
            for (Orders vo : orderlist) {
                //按订单状态分组统计
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
            SeriesData data1 = new SeriesData();
            data1.setName("未付款的订单");
            data1.setValue(num1);
            SeriesData data2 = new SeriesData();
            data2.setName("待确认的订单");
            data2.setValue(num2);
            SeriesData data3 = new SeriesData();
            data3.setName("待发货的订单");
            data3.setValue(num3);
            SeriesData data4 = new SeriesData();
            data4.setName("已发货的订单");
            data4.setValue(num4);
            SeriesData data5 = new SeriesData();
            data5.setName("已完成的订单");
            data5.setValue(num5);
            SeriesData data6 = new SeriesData();
            data6.setName("取消的订单");
            data6.setValue(num6);

            datalist.add(data1);
            datalist.add(data2);
            datalist.add(data3);
            datalist.add(data4);
            datalist.add(data5);
            datalist.add(data6);
        }
        return datalist;
    }

    /**
     * 退货率=退货总数/订单总数<i>(统计均为已完成的订单)</i>
     * @param queryMap 参数
     * @param model
     * @return
     */
    public Map<String, List<Object>> goodsReturnRate(Map<String, String> queryMap) {
        String model = queryMap.get("model");
        List<OrdersReturnDto> orderlist = ordersWriteDao.goodsReturnRate(queryMap);

        Map<String, List<Object>> map = new HashMap<String, List<Object>>();
        List<Object> goodsreturnNumList = new ArrayList<Object>();
        List<Object> orderNumList = new ArrayList<Object>();
        List<Object> rateList = new ArrayList<Object>();
        DecimalFormat df = new DecimalFormat("###.##");
        Calendar cal = Calendar.getInstance();
        //最大月份/天
        int max = "year".equals(model) ? cal.getActualMaximum(Calendar.MONTH) + 1
            : cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 1; i <= max; i++) {
            int goodsreturnNum = 0;
            int orderNum = 0;
            double rate = 0;

            for (OrdersReturnDto or : orderlist) {
                //只创建一次实例
                cal.clear();
                cal.setTime(or.getFinishTime());
                int scale = "year".equals(model) ? cal.get(Calendar.MONTH) + 1
                    : cal.get(Calendar.DAY_OF_MONTH);
                if (i == scale) {
                    //退款到账户
                    if (or.getStateMoney() != null && or.getStateMoney() == 2) {
                        goodsreturnNum += 1;
                    }
                    orderNum += 1;
                }
            }
            //退货率=退货总数/订单总数
            rate = orderNum == 0 ? 0
                : (new BigDecimal(goodsreturnNum).divide(new BigDecimal(orderNum), 2,
                    BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100)).doubleValue();

            goodsreturnNumList.add(goodsreturnNum);
            orderNumList.add(orderNum);
            rateList.add(df.format(rate));

        }
        map.put("goodsreturnNum", goodsreturnNumList);
        map.put("orderNum", orderNumList);
        map.put("rate", rateList);

        return map;
    }

    /**
     * 人均消费 = 订单总金额/会员数（购买商品的会员总数）
     * @param queryMap 参数
     * @return
     */
    public Map<String, List<Object>> getCPIstatistics(Map<String, String> queryMap) {
        String model = queryMap.get("model");

        Map<String, Object> parammap = new HashMap<String, Object>(queryMap);
        if (queryMap.get("s_status") != null) {
            parammap.put("s_status", Arrays.asList(queryMap.get("s_status").split(",")));
        }
        List<Orders> orderlist = ordersWriteDao.getOrders(parammap, -1, -1);

        Map<String, List<Object>> map = new HashMap<String, List<Object>>();
        List<Object> orderMoneyList = new ArrayList<Object>();
        List<Object> userList = new ArrayList<Object>();
        List<Object> rateList = new ArrayList<Object>();
        DecimalFormat df = new DecimalFormat("###.##");

        Calendar cal = Calendar.getInstance();
        int max = "year".equals(model) ? cal.getActualMaximum(Calendar.MONTH) + 1
            : cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //购买商品的会员只算不同会员,即同一会员下多单,则只计为一个会员 
        Map<Integer, Integer> usermap = new HashMap<Integer, Integer>();

        for (int i = 1; i <= max; i++) {
            //避免创建多个对象,每次只清空而不是new
            if (usermap.size() > 0) {
                usermap.clear();
            }
            double orderMoney = 0;
            int user = 0;
            double rate = 0;

            for (Orders or : orderlist) {
                cal.clear();
                cal.setTime(or.getFinishTime());
                int scale = "year".equals(model) ? cal.get(Calendar.MONTH) + 1
                    : cal.get(Calendar.DAY_OF_MONTH);
                if (i == scale) {
                    orderMoney = new BigDecimal(orderMoney).add(or.getMoneyOrder()).doubleValue();
                    if (usermap.get(or.getMemberId()) == null) {
                        user += 1;
                    }
                    usermap.put(or.getMemberId(), or.getMemberId());
                }
            }
            //人均消费 = 订单总金额/会员数
            rate = user == 0 ? 0
                : (new BigDecimal(orderMoney).divide(new BigDecimal(user), 2,
                    BigDecimal.ROUND_HALF_UP)).doubleValue();

            orderMoneyList.add(orderMoney);
            userList.add(user);
            rateList.add(df.format(rate));

        }
        map.put("orderMoney", orderMoneyList);
        map.put("user", userList);
        map.put("rate", rateList);

        return map;
    }

    /**
     * 销售统计数据<br>
     * 客单价 = 订单总金额/订单总数
     * @param querymap
     * @return
     */
    public Map<String, List<Object>> getSaleStatistics(Map<String, String> queryMap) {
        String model = queryMap.get("model");

        List<Orders> orderlist = getDuleOrdersWithNoPage(queryMap);

        Map<String, List<Object>> map = new HashMap<String, List<Object>>();
        List<Object> orderMoneyList = new ArrayList<Object>();
        List<Object> orderNumList = new ArrayList<Object>();
        List<Object> kdjList = new ArrayList<Object>();
        DecimalFormat df = new DecimalFormat("###.##");
        Calendar cal = Calendar.getInstance();
        //最大月份/天
        int max = "year".equals(model) ? cal.getActualMaximum(Calendar.MONTH) + 1
            : cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 1; i <= max; i++) {
            double orderMoney = 0;
            int orderNum = 0;
            double kdj = 0;

            for (Orders or : orderlist) {
                //只创建一次实例
                cal.clear();
                cal.setTime(or.getFinishTime());
                int scale = "year".equals(model) ? cal.get(Calendar.MONTH) + 1
                    : cal.get(Calendar.DAY_OF_MONTH);

                if (i == scale) {
                    orderNum++;
                    orderMoney = new BigDecimal(orderMoney).add(or.getMoneyOrder()).doubleValue();
                }

            }
            //客单价 = 订单总金额/订单总数
            kdj = orderMoney / orderNum;

            orderMoneyList.add(orderMoney);
            orderNumList.add(orderNum);
            kdjList.add(df.format(kdj));

        }
        map.put("orderMoney", orderMoneyList);
        map.put("orderNum", orderNumList);
        map.put("kdj", kdjList);

        return map;
    }

    /**
     * 商品销量
     * @param queryMap
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public List<ProductSaleDto> getProductSale(Map<String, String> queryMap) throws IllegalAccessException,
                                                                             InvocationTargetException,
                                                                             NoSuchMethodException {
        List<ProductSaleDto> datalist = null;
        try {
            String model = queryMap.get("model");
            if (model == null) {
                throw new BusinessException("请选择统计模式");
            }
            if (queryMap.get("productId") == null || "".equals(queryMap.get("productId"))) {
                throw new BusinessException("请选择商品");
            }

            Map<String, Object> parammap = new HashMap<String, Object>(queryMap);
            if (queryMap.get("status") != null) {
                parammap.put("s_status", Arrays.asList(queryMap.get("status").split(",")));
            }
            datalist = ordersWriteDao.getProductSale(parammap);

            Calendar cal = Calendar.getInstance();
            int max = "year".equals(model) ? cal.getActualMaximum(Calendar.MONTH) + 1
                : cal.getActualMaximum(Calendar.DAY_OF_MONTH);

            List<ProductSaleDto> enableNorm = new ArrayList<ProductSaleDto>();
            ProductSaleDto psd = null;
            //启用规格
            boolean isnorm = false;
            LinkedHashMap<Integer, BigDecimal> singleMap = new LinkedHashMap<Integer, BigDecimal>();

            for (ProductSaleDto dto : datalist) {
                List<Object> data = new ArrayList<Object>();

                for (int i = 1; i <= max; i++) {
                    BigDecimal orderMoney = new BigDecimal(0);

                    //只创建一次实例
                    cal.clear();
                    cal.setTime(dto.getPayTime());
                    int scale = "year".equals(model) ? cal.get(Calendar.MONTH) + 1
                        : cal.get(Calendar.DAY_OF_MONTH);
                    if (i == scale) {
                        orderMoney = orderMoney.add(dto.getMoneyOrder());
                    }
                    if (dto.getIsNorm().intValue() == 1) {
                        isnorm = true;
                        psd = new ProductSaleDto();
                        PropertyUtils.copyProperties(psd, dto);
                        //未启用规格
                        BigDecimal thisVal_ = singleMap.get(i) == null ? new BigDecimal(0)
                            : singleMap.get(i);
                        singleMap.put(i, orderMoney.add(thisVal_));
                    } else {
                        data.add(orderMoney);
                    }
                }
                dto.setData(data);
            }
            if (isnorm) {
                Iterator<Entry<Integer, BigDecimal>> it = singleMap.entrySet().iterator();
                List<Object> singlelist = new ArrayList<Object>(singleMap.size());
                while (it.hasNext()) {
                    singlelist.add(it.next().getValue());
                }
                psd.setData(singlelist);
                enableNorm.add(psd);
                datalist = enableNorm;
            }
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                throw e;
            } else {
                e.printStackTrace();
            }
        }
        return datalist;
    }

    /**
     * 商品购买率
     * @param queryMap
     * @return
     */
    public PhurchaseRateDto getPhurchaseRate(Map<String, String> queryMap) {
        String model = queryMap.get("model");

        PhurchaseRateDto dto = null;
        try {
            if (model == null) {
                throw new BusinessException("请选择统计模式");
            }

            dto = new PhurchaseRateDto();

            //有效订单
            List<Orders> orderlist = getDuleOrdersWithNoPage(queryMap);
            //pv
            List<PVDto> pvlist = browseLogReadDao.getProductPV(queryMap);

            List<Object> orderNumList = new ArrayList<Object>();
            List<Object> pvList = new ArrayList<Object>();
            List<Object> rateList = new ArrayList<Object>();

            DecimalFormat df = new DecimalFormat("###.##");
            Calendar cal = Calendar.getInstance();
            //最大月份/天
            int max = "year".equals(model) ? cal.getActualMaximum(Calendar.MONTH) + 1
                : cal.getActualMaximum(Calendar.DAY_OF_MONTH);

            for (int i = 1; i <= max; i++) {
                double orderNum = 0;
                double pv = 0;
                double rate = 0;

                for (Orders or : orderlist) {
                    //只创建一次实例
                    cal.clear();
                    cal.setTime(or.getFinishTime());
                    int scale = "year".equals(model) ? cal.get(Calendar.MONTH) + 1
                        : cal.get(Calendar.DAY_OF_MONTH);

                    if (i == scale) {
                        orderNum++;
                    }

                }
                for (PVDto pvdto : pvlist) {
                    cal.clear();
                    cal.setTime(pvdto.getCreateTime());
                    int scale = "year".equals(model) ? cal.get(Calendar.MONTH) + 1
                        : cal.get(Calendar.DAY_OF_MONTH);
                    if (i == scale) {
                        pv++;
                    }
                }
                //购买率=有效订单/pv
                rate = (pv == 0 ? 0 : orderNum / pv) * 100;

                orderNumList.add(orderNum);
                pvList.add(pv);
                rateList.add(df.format(rate));
            }
            dto.setOrderNumList(orderNumList);
            dto.setPvList(pvList);
            dto.setRateList(rateList);
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                throw e;
            } else {
                e.printStackTrace();
            }
        }
        return dto;
    }

    /**
     * 获取交易完成的订单
     * @param queryMap
     * @return
     */
    public List<Orders> getDuleOrdersWithNoPage(Map<String, String> queryMap) {
        Map<String, Object> parammap = new HashMap<String, Object>(queryMap);
        parammap.put("s_status", Arrays.asList("3,4,5".split(",")));
        return ordersWriteDao.getOrders(parammap, -1, -1);
    }

    /**
     * 商品浏览量统计
     * @param queryMap
     * @return
     */
    public List<Object> pvStatistics(Map<String, String> queryMap) {
        String model = queryMap.get("model");

        List<PVDto> datalist = browseLogReadDao.getProductPV(queryMap);
        List<Object> pvlist = new ArrayList<Object>();

        Calendar cal = Calendar.getInstance();
        //最大月份/天
        int max = "year".equals(model) ? cal.getActualMaximum(Calendar.MONTH) + 1
            : cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 1; i <= max; i++) {
            int pvNum = 0;

            for (PVDto pv : datalist) {
                //只创建一次实例
                cal.clear();
                cal.setTime(pv.getCreateTime());
                int scale = "year".equals(model) ? cal.get(Calendar.MONTH) + 1
                    : cal.get(Calendar.DAY_OF_MONTH);

                if (i == scale) {
                    pvNum++;
                    System.out.println(i + "有数据--->pvNum:" + pvNum);
                }

            }
            pvlist.add(pvNum);
        }
        return pvlist;
    }

}
