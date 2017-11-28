package com.ejavashop.dao.shopm.write.mseller;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.shopm.MSellerIndexBanner;

@Repository
public interface MSellerIndexBannerWriteDao {

    MSellerIndexBanner get(java.lang.Integer id);

    Integer insert(MSellerIndexBanner mIndexBanner);

    Integer update(MSellerIndexBanner mIndexBanner);

    Integer delete(Integer id);
}