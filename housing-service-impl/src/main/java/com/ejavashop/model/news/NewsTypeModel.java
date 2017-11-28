package com.ejavashop.model.news;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.dao.shop.write.news.NewsTypeWriteDao;
import com.ejavashop.entity.news.NewsType;

@Component(value = "newsTypeModel")
public class NewsTypeModel {

    @Resource
    private NewsTypeWriteDao newsTypeWriteDao;

    public NewsType get(Integer newsTypeId) {
        return newsTypeWriteDao.get(newsTypeId);
    }

    public Integer save(NewsType newsType) {
        return newsTypeWriteDao.save(newsType);
    }

    public Integer update(NewsType newsType) {
        return newsTypeWriteDao.update(newsType);
    }

    public int getCount(Map<String, String> queryMap) {
        return newsTypeWriteDao.getCount(queryMap);
    }

    public List<NewsType> page(Map<String, String> queryMap, Integer start, Integer size) {
        return newsTypeWriteDao.page(queryMap, start, size);
    }

    public List<NewsType> list() {
        return newsTypeWriteDao.list();
    }

    public int del(Integer id) {
        return newsTypeWriteDao.del(id);
    }

}
