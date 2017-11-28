package com.ejavashop.dao.promotion.write.group;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.group.ActGroupBanner;

@Repository
public interface ActGroupBannerWriteDao {

    ActGroupBanner get(java.lang.Integer id);

    Integer insert(ActGroupBanner actGroupBanner);

    Integer update(ActGroupBanner actGroupBanner);

    Integer delete(Integer id);
}