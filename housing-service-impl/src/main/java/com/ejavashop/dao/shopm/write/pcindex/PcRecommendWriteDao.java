package com.ejavashop.dao.shopm.write.pcindex;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.shopm.pcindex.PcRecommend;

@Repository
public interface PcRecommendWriteDao {

    PcRecommend get(java.lang.Integer id);

    Integer insert(PcRecommend pcRecommend);

    Integer update(PcRecommend pcRecommend);

    Integer delete(Integer id);
}