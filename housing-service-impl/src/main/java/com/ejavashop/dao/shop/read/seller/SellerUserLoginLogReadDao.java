package com.ejavashop.dao.shop.read.seller;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.seller.SellerUserLoginLog;

@Repository
public interface SellerUserLoginLogReadDao {

    SellerUserLoginLog get(java.lang.Integer id);

}