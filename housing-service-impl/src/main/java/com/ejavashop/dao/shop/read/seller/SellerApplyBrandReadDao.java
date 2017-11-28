package com.ejavashop.dao.shop.read.seller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.seller.SellerApplyBrand;

@Repository
public interface SellerApplyBrandReadDao {

    SellerApplyBrand get(java.lang.Integer id);

    Integer queryCount(Map<String, Object> map);

    List<SellerApplyBrand> queryList(Map<String, Object> map);

}
