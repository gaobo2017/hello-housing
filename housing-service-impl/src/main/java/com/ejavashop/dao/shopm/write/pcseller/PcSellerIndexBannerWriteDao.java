package com.ejavashop.dao.shopm.write.pcseller;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.shopm.pcseller.PcSellerIndexBanner;

@Repository
public interface PcSellerIndexBannerWriteDao {

    PcSellerIndexBanner get(java.lang.Integer id);

    Integer insert(PcSellerIndexBanner pcSellerIndexBanner);

    Integer update(PcSellerIndexBanner pcSellerIndexBanner);

    Integer delete(Integer id);
}