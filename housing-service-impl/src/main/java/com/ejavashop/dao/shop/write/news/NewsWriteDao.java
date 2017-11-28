package com.ejavashop.dao.shop.write.news;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.news.News;

@Repository
public interface NewsWriteDao {

    News get(java.lang.Integer id);

    Integer save(News news);

    Integer update(News news);

    Integer updateNotNull(News news);

    Integer getCount(@Param("param1") Map<String, String> queryMap);

    List<News> page(@Param("param1") Map<String, String> queryMap, @Param("start") Integer start,
                    @Param("size") Integer size);

    List<News> list();

    Integer del(Integer id);
}