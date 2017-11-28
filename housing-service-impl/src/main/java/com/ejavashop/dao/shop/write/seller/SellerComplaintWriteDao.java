package com.ejavashop.dao.shop.write.seller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.seller.SellerComplaint;

@Repository
public interface SellerComplaintWriteDao {

    SellerComplaint get(java.lang.Integer id);

    Integer save(SellerComplaint sellerComplaint);

    Integer update(SellerComplaint sellerComplaint);

    Integer queryCount(Map<String, Object> map);

    List<SellerComplaint> queryList(Map<String, Object> map);

    Integer getCount(Map<String, Object> queryMap);

    List<SellerComplaint> page(Map<String, Object> queryMap);

    Integer del(Integer id);
}
