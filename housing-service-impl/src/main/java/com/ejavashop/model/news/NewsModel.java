package com.ejavashop.model.news;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;

import com.ejavashop.core.PaginationUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.dao.shop.read.news.NewsReadDao;
import com.ejavashop.dao.shop.write.news.NewsWriteDao;
import com.ejavashop.entity.news.News;
import com.ejavashop.entity.news.NewsType;
import com.ejavashop.vo.news.FrontNewsTypeVO;
import com.ejavashop.vo.news.FrontNewsVO;

@Component(value = "newsModel")
public class NewsModel {

    @Resource
    private NewsReadDao  newsReadDao;

    @Resource
    private NewsWriteDao newsWriteDao;

    /**
     * 首页新闻类型<br>
     * 该方法只会查询最多六条数据
     * @return
     */
    public List<FrontNewsTypeVO> getNewsType() {
        List<FrontNewsTypeVO> volist = new ArrayList<FrontNewsTypeVO>();
        List<NewsType> types = newsReadDao.getNewsType(0, 6);
        for (NewsType type : types) {
            FrontNewsTypeVO vo = new FrontNewsTypeVO();
            try {
                PropertyUtils.copyProperties(vo, type);
            } catch (Exception e) {
                e.printStackTrace();
            }
            volist.add(vo);
        }
        return volist;
    }

    /**
     * @param queryMap
     * @param pager
     * @return
     */
    public List<FrontNewsVO> getNewsByType(Map<String, Object> queryMap, PaginationUtil page) {
        if (queryMap.get("typeid") == null)
            throw new BusinessException("请指定新闻类型");

        Integer start = 0, size = 0;
        if (page != null) {
            page.createPagination(newsReadDao.queryNewsCount(queryMap));
            start = page.getStartNumber();
            size = page.getSize();
        }
        queryMap.put("start", start);
        queryMap.put("size", size);

        List<News> newslist = newsReadDao.getNewsByType(queryMap);
        NewsType newtype = newsReadDao.getType((Integer) queryMap.get("typeid"));

        List<FrontNewsVO> volist = new ArrayList<FrontNewsVO>();
        for (News news : newslist) {
            FrontNewsVO newsvo = new FrontNewsVO();
            try {
                PropertyUtils.copyProperties(newsvo, news);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException("拷贝属性出错");
            }
            newsvo.setTypeName(newtype.getName());
            volist.add(newsvo);
        }
        return volist;
    }

    public FrontNewsVO get(Integer id) throws IllegalAccessException, InvocationTargetException,
                                      NoSuchMethodException {
        News news = newsReadDao.get(id);
        FrontNewsVO vo = new FrontNewsVO();

        PropertyUtils.copyProperties(vo, news);
        setAround(vo);

        return vo;
    }

    /**
     * 文章的上一篇下一篇
     * @param vo
     */
    private void setAround(FrontNewsVO vo) {

        News pre = newsReadDao.getPre(vo.getSort(), vo.getTypeId());
        if (pre != null) {
            vo.setPreId(pre.getId());
            vo.setPreURL(DomainUrlUtil.EJS_URL_RESOURCES + "/index/article.html?id="
                         + vo.getPreId() + "&date_=" + new Date().getTime());
            vo.setPreTitle(pre.getTitle());
            vo.setPreTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(pre.getCreateTime()));
        } else {
            vo.setPreTitle("没有了");
            vo.setPreURL("javascript:;");
        }

        News next = newsReadDao.getNext(vo.getSort(), vo.getTypeId());
        if (next != null) {
            vo.setNextId(next.getId());
            vo.setNextURL(DomainUrlUtil.EJS_URL_RESOURCES + "/index/article.html?id="
                          + vo.getNextId() + "&date_=" + new Date().getTime());
            vo.setNextTitle(next.getTitle());
            vo.setNextTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(next.getCreateTime()));
        } else {
            vo.setNextTitle("没有了");
            vo.setNextURL("javascript:;");
        }
    }

    public List<NewsType> getNewsType4Article() {
        return newsReadDao.getNewsType(0, 6);
    }

    public List<News> getLastedNews() {
        return newsReadDao.getLastedNews(0, 4);
    }

    public int del(Integer id) {
        return newsWriteDao.del(id);
    }

    public List<News> page(Map<String, String> queryMap, Integer start, Integer size) {
        return newsWriteDao.page(queryMap, start, size);
    }

    public int getCount(Map<String, String> queryMap) {
        return newsWriteDao.getCount(queryMap);
    }

    public Integer update(News news) {
        return newsWriteDao.update(news);
    }

    public Integer save(News news) {
        return newsWriteDao.save(news);
    }

    public News getNewId(Integer newsId) {
        return newsWriteDao.get(newsId);
    }

}
