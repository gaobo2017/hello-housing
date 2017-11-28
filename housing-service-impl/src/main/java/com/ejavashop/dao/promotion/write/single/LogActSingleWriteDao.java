package com.ejavashop.dao.promotion.write.single;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.single.LogActSingle;

@Repository
public interface LogActSingleWriteDao {

    // LogActSingle get(java.lang.Integer id);

    Integer insert(LogActSingle logActSingle);

    // Integer update(LogActSingle logActSingle);
}