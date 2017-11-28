package com.ejavashop.dao.shop.read.news;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.news.News;
import com.ejavashop.entity.news.NewsType;

@Repository
public interface NewsReadDao {

    List<NewsType> getNewsType(@Param("start") Integer start, @Param("size") Integer size);

    /**
     * 获取给定分类下所有新闻
     * @param typeid 类型id
     * @param start 开始行
     * @param size 记录条数
     * @return
     */
    List<News> getNewsByType(Map<String, Object> map);

    News get(Integer id);

    NewsType getType(Integer typeid);

    News getPre(@Param("sort") Integer sort, @Param("type") Integer type);

    News getNext(@Param("sort") Integer sort, @Param("type") Integer type);

    Integer queryNewsCount(Map<String, Object> map);

    /**
     * 最新新闻
     * @param start
     * @param size
     * @return
     */
    List<News> getLastedNews(@Param("start") Integer start, @Param("size") Integer size);
}
