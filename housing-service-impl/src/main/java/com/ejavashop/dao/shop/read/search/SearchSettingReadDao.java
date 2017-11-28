package com.ejavashop.dao.shop.read.search;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.search.SearchSetting;

@Repository
public interface SearchSettingReadDao {

    SearchSetting get(java.lang.Integer id);

}