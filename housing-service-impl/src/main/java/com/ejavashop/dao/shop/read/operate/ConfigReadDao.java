package com.ejavashop.dao.shop.read.operate;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.operate.Config;

@Repository
public interface ConfigReadDao {

    Config get(java.lang.Integer id);

}