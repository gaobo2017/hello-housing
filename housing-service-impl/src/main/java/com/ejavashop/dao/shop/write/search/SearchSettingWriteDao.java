package com.ejavashop.dao.shop.write.search;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.search.SearchSetting;

@Repository
public interface SearchSettingWriteDao {

    SearchSetting get(java.lang.Integer id);

    Integer insert(SearchSetting searchSetting);

    Integer update(SearchSetting searchSetting);

    Integer updateKeyword(@Param("id") int id, @Param("keyword") String keyword);

    Integer updateKeywordFilter(@Param("id") int id, @Param("keywordFilter") int keywordFilter);

    Integer updateIndexProductId(@Param("id") int id, @Param("indexProductId") int indexProductId);

    Integer updateIndexProductTime(@Param("id") int id);
}