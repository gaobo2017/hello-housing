package com.ejavashop.dao.shop.write.operate;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.operate.Config;

@Repository
public interface ConfigWriteDao {

    Config get(java.lang.Integer id);

    Integer insert(Config config);

    Integer update(Config config);
}