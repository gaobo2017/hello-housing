package com.ejavashop.dao.shop.read.search;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.search.SearchKeyword;

@Repository
public interface SearchKeywordReadDao {

    SearchKeyword get(java.lang.Integer id);

    Integer countByKeyword(String keyword);

    int count(@Param("param1") Map<String, String> queryMap);

    List<SearchKeyword> getSearchKeywords(@Param("param1") Map<String, String> queryMap,
                                          @Param("start") Integer start, @Param("size") Integer size);
}