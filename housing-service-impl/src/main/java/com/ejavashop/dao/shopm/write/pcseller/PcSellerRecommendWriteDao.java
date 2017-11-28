package com.ejavashop.dao.shopm.write.pcseller;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.shopm.pcseller.PcSellerRecommend;

@Repository
public interface PcSellerRecommendWriteDao {

    PcSellerRecommend get(java.lang.Integer id);

    Integer insert(PcSellerRecommend pcSellerRecommend);

    Integer update(PcSellerRecommend pcSellerRecommend);

    Integer delete(Integer id);
}