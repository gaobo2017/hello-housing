package com.ejavashop.dao.shop.read.search;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.search.SearchRecord;

@Repository
public interface SearchRecordReadDao {

    SearchRecord get(java.lang.Integer id);

    int count(@Param("param1") Map<String, String> queryMap);

    List<SearchRecord> getSearchRecords(@Param("param1") Map<String, String> queryMap,
                                        @Param("start") Integer start, @Param("size") Integer size);

    List<SearchRecord> getSearchRecordByKeyword(@Param("keyword") String keyword,
                                                @Param("number") int number);

}