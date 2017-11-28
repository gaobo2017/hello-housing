package com.ejavashop.dao.shop.write.seller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.seller.SellerQq;

@Repository
public interface SellerQqWriteDao {

    List<SellerQq> getInusedSellerQqBySId(Integer sellerId);

    SellerQq get(Integer id);

    Integer save(SellerQq sellerQq);

    Integer update(SellerQq sellerQq);

    Integer getCount(Map<String, String> queryMap);

    List<SellerQq> page(Map<String, Object> queryMap);

    List<SellerQq> list();

    Integer del(Integer id);
}
