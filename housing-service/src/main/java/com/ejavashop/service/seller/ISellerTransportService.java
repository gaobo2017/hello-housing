package com.ejavashop.service.seller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.seller.SellerTransport;
import com.ejavashop.vo.seller.TransportJson;

/**
 * 商家运费
 *                       
 * @Filename: ISellerTransportService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface ISellerTransportService {

    /**
     * 根据id取得卖家运费模板
     * @param  sellerTransportId
     * @return
     */
    ServiceResult<SellerTransport> getSellerTransportById(Integer sellerTransportId);

    /**
     * 保存卖家运费模板
     * @param  sellerTransport
     * @return
     */
    ServiceResult<Integer> saveSellerTransport(SellerTransport sellerTransport);

    /**
    * 更新卖家运费模板
    * @param  sellerTransport
    * @return
    */
    ServiceResult<Integer> updateSellerTransport(SellerTransport sellerTransport);

    /**
    * 分页查询
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<SellerTransport>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    ServiceResult<Boolean> del(Integer id);

    HttpJsonResult<List<TransportJson>> assembleTransportInfo(SellerTransport st);

    /**
     * 取卖家的运费模板
     * @param id
     * @return
     */
    List<SellerTransport> getTransportBySellerId(Integer id);

    /**
     * 根据运费末班ID启用某个模板，同时更新该商家的其他模板为禁用状态
     * @param sellerId
     * @param id
     * @return
     */
    ServiceResult<Boolean> transportInUse(Integer sellerId, Integer id);

    /**
     * 计算运费
     * @param sellerId 商家ID
     * @param num 数量
     * @param cityId 订单地址的城市ID
     * @return
     */
    ServiceResult<BigDecimal> calculateTransFee(Integer sellerId, Integer num, Integer cityId);
}