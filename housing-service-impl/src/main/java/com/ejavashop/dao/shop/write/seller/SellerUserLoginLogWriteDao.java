package com.ejavashop.dao.shop.write.seller;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.seller.SellerUserLoginLog;

@Repository
public interface SellerUserLoginLogWriteDao {

    SellerUserLoginLog get(java.lang.Integer id);

    Integer insert(SellerUserLoginLog sellerUserLoginLog);

    Integer update(SellerUserLoginLog sellerUserLoginLog);
}