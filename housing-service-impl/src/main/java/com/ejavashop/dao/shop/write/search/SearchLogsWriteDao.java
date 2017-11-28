package com.ejavashop.dao.shop.write.search;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.search.SearchLogs;

@Repository
public interface SearchLogsWriteDao {

    SearchLogs get(java.lang.Integer id);

    Integer insert(SearchLogs searchLogs);

    Integer update(SearchLogs searchLogs);

    Integer countByKeyWordCookie(@Param("keyword") String keyword,
                                 @Param("siteCookie") String siteCookie);

    Integer updateByKeyWordCookie(@Param("keyword") String keyword,
                                  @Param("siteCookie") String siteCookie);
}