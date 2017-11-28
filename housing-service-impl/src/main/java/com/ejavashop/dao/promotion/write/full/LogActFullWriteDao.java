package com.ejavashop.dao.promotion.write.full;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.full.LogActFull;

@Repository
public interface LogActFullWriteDao {

    // LogActFull get(java.lang.Integer id);

    Integer insert(LogActFull logActFull);

    // Integer update(LogActFull logActFull);
}