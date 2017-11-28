package com.ejavashop.dao.shop.write.news;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.news.NewsPartner;

@Repository
public interface NewsPartnerWriteDao {

    NewsPartner get(java.lang.Integer id);

    Integer save(NewsPartner newsPartner);

    Integer update(NewsPartner newsPartner);

    Integer getCount(@Param("param1") Map<String, String> queryMap);

    List<NewsPartner> page(@Param("param1") Map<String, String> queryMap,
                           @Param("start") Integer start, @Param("size") Integer size);

    List<NewsPartner> list();

    Integer del(Integer id);
}