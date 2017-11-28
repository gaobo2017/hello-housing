package com.ejavashop.model.search;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.StringUtil;
import com.ejavashop.dao.shop.read.search.SearchKeywordReadDao;
import com.ejavashop.dao.shop.read.search.SearchSettingReadDao;
import com.ejavashop.dao.shop.write.search.SearchKeywordWriteDao;
import com.ejavashop.dao.shop.write.search.SearchSettingWriteDao;
import com.ejavashop.entity.search.SearchKeyword;
import com.ejavashop.entity.search.SearchSetting;

/**
 * 搜索相关的设置
 *                       
 * @Filename: SearchSettingModel.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Component
public class SearchSettingModel {

    @Resource
    private SearchSettingWriteDao searchSettingWriteDao;

    @Resource
    private SearchSettingReadDao  searchSettingReadDao;

    @Resource
    private SearchKeywordReadDao  searchKeywordReadDao;

    @Resource
    private SearchKeywordWriteDao searchKeywordWriteDao;

    /**
     * 根据id取得search_setting对象
     * @param  searchSettingId
     * @return
     */
    public SearchSetting getSearchSettingById(Integer searchSettingId) {
        return searchSettingReadDao.get(searchSettingId);
    }

    /**
     * 保存search_setting对象
     * @param  searchSetting
     * @return
     */
    public Integer saveSearchSetting(SearchSetting searchSetting) {
        this.dbConstrains(searchSetting);
        return searchSettingWriteDao.insert(searchSetting);
    }

    /**
    * 更新search_setting对象
    * @param  searchSetting
    * @return
    */
    public Integer updateSearchSetting(SearchSetting searchSetting) {
        this.dbConstrains(searchSetting);
        return searchSettingWriteDao.update(searchSetting);
    }

    private void dbConstrains(SearchSetting searchSetting) {
        searchSetting.setKeyword(StringUtil.dbSafeString(searchSetting.getKeyword(), false, 255));
    }

    /**
     * 更新关键词
     * @param id
     * @param keyword
     * @return
     */
    public Integer updateKeyword(int id, String keyword) {
        return searchSettingWriteDao.updateKeyword(id, keyword);
    }

    /**
     * 更新查询关键字
     * @param id
     * @param keywordFilter
     * @return
     */
    public Integer updateKeywordFilter(int id, int keywordFilter) {
        return searchSettingWriteDao.updateKeywordFilter(id, keywordFilter);
    }

    /**
     * 关键字查询
     * @param queryMap
     * @param pager
     * @return
     */
    public List<SearchKeyword> getSearchKeywords(Map<String, String> queryMap, PagerInfo pager) {
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(searchKeywordReadDao.count(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }

        List<SearchKeyword> list = searchKeywordReadDao.getSearchKeywords(queryMap, start, size);
        return list;
    }

    /**
     * 插入敏感词
     * @param searchKeyword
     * @return
     */
    public Integer createSearchKeyword(SearchKeyword searchKeyword) {
        return searchKeywordWriteDao.insert(searchKeyword);
    }

    /**
     * 根据ID获取敏感词
     * @param id
     * @return
     */
    public SearchKeyword getSearchKeywordById(Integer id) {
        return searchKeywordReadDao.get(id);
    }

    /**
     * 更新敏感词
     * @param searchKeyword
     * @return
     */
    public int updateSearchKeyword(SearchKeyword searchKeyword) {
        return searchKeywordWriteDao.update(searchKeyword);
    }

    /**
     * 删除敏感词
     * @param id
     * @return
     */
    public int delSearchKeyword(Integer id) {
        return searchKeywordWriteDao.del(id);
    }

    public Integer getSearchKeywordsByKeyword(String keyword) {
        return searchKeywordReadDao.countByKeyword(keyword);
    }

}