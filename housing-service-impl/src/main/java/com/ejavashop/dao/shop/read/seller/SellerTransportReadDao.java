package com.ejavashop.dao.shop.read.seller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.seller.SellerTransport;

@Repository
public interface SellerTransportReadDao {

    SellerTransport get(java.lang.Integer id);

    Integer getCount(Map<String, Object> queryMap);

    List<SellerTransport> page(Map<String, Object> queryMap);

    /**
     * 根据sellerId获取该商家下正在使用的运费模板，如果有多个只取第一个
     * @param sellerId
     * @return
     */
    SellerTransport getInUseBySellerId(Integer sellerId);
}
