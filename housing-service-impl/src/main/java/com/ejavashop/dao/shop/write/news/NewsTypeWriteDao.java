package com.ejavashop.dao.shop.write.news;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.news.NewsType;

@Repository
public interface NewsTypeWriteDao {

    NewsType get(java.lang.Integer id);

    Integer save(NewsType newsType);

    Integer update(NewsType newsType);

    Integer updateNotNull(NewsType newsType);

    Integer getCount(@Param("param1") Map<String, String> queryMap);

    List<NewsType> page(@Param("param1") Map<String, String> queryMap,
                        @Param("start") Integer start, @Param("size") Integer size);

    List<NewsType> list();

    Integer del(Integer id);
}