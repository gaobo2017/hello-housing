package com.ejavashop.service.report;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.dto.PhurchaseRateDto;
import com.ejavashop.dto.ProductSaleDto;

/**
 * 统计相关服务 
 *                       
 * @Filename: IStatisticsService.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihl_0@126.com
 *
 */
public interface IStatisticsService {
    /**
     * 订单概况统计
     * @param map
     * @return
     */
    ServiceResult<List<Object>> getOrderOverviewData(Map<String, String> map);

    /**
     * 退货率统计<br>
     * 退货率=退货总数/订单总数<i>(统计均为已完成的订单)</i>
     * @param querymap 
     * @return
     */
    ServiceResult<Map<String, List<Object>>> goodsReturnRate(Map<String, String> querymap);

    /**
     * 人均消费统计<br>
     * <em>人均消费 = 订单总金额/会员数（购买商品的会员总数）</em>
     * @param map
     * @return
     */
    ServiceResult<Map<String, List<Object>>> getCPIstatistics(Map<String, String> map);

    /**
     * 订单销售统计<br>
     * <i>订单销量包括订单数、订单销量量、客单价</i><br>
     * <em>客单价 = 订单总金额/订单总数</em>
     * @param map
     * @return
     */
    ServiceResult<Map<String, List<Object>>> getSaleStatistics(Map<String, String> map);

    /**
     * 商品销量
     * @param queryMap
     * @return
     */
    ServiceResult<List<ProductSaleDto>> getProductSale(Map<String, String> queryMap);

    /**
     * 商品购买率统计
     * @param map
     * @return
     */
    ServiceResult<PhurchaseRateDto> getPhurchaseRate(Map<String, String> map);

    /**
     * 浏览量统计
     * @param map
     * @return
     */
    ServiceResult<List<Object>> pvStatistics(Map<String, String> map);
}
