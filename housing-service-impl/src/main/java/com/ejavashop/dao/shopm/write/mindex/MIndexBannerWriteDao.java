package com.ejavashop.dao.shopm.write.mindex;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.shopm.MIndexBanner;

@Repository
public interface MIndexBannerWriteDao {

    MIndexBanner get(java.lang.Integer id);

    Integer insert(MIndexBanner mIndexBanner);

    Integer update(MIndexBanner mIndexBanner);

    Integer delete(Integer id);
}