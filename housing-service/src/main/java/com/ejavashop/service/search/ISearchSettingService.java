package com.ejavashop.service.search;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.search.SearchKeyword;
import com.ejavashop.entity.search.SearchSetting;

/**
 * 搜索相关设置
 *                       
 * @Filename: ISearchSettingService.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
public interface ISearchSettingService {

    /**
     * 根据id取得search_setting对象
     * @param  searchSettingId
     * @return
     */
    ServiceResult<SearchSetting> getSearchSettingById(Integer searchSettingId);

    /**
     * 保存search_setting对象
     * @param  searchSetting
     * @return
     */
    ServiceResult<Integer> saveSearchSetting(SearchSetting searchSetting);

    /**
    * 更新search_setting对象
    * @param  searchSetting
    * @return
    */
    ServiceResult<Integer> updateSearchSetting(SearchSetting searchSetting);

    /**
     * 更新关键词
     * @param searchsettingid
     * @param keyword
     * @return
     */
    ServiceResult<Integer> updateKeyword(int searchsettingid, String keyword);

    /**
     * 更新过滤的敏感词
     * @param searchsettingid
     * @param keywordFilter
     * @return
     */
    ServiceResult<Integer> updateKeywordFilter(int searchsettingid, int keywordFilter);

    /**
     * 关键字查询
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<SearchKeyword>> getSearchKeywords(Map<String, String> queryMap,
                                                         PagerInfo pager);

    /**
     * 插入敏感词
     * @param searchKeyword
     * @return
     */
    ServiceResult<Integer> createSearchKeyword(SearchKeyword searchKeyword);

    /**
     * 根据ID查询敏感词
     * @param id
     * @return
     */
    ServiceResult<SearchKeyword> getSearchKeywordById(Integer id);

    /**
     * 更新敏感词
     * @param searchKeyword
     * @return
     */
    ServiceResult<Boolean> updateSearchKeyword(SearchKeyword searchKeyword);

    /**
     * 删除敏感词
     * @param id
     * @return
     */
    ServiceResult<Boolean> delSearchKeyword(Integer id);

    /**
     * 根据查询词，查询敏感词
     * @param keyword
     * @return
     */
    ServiceResult<Integer> getSearchKeywordsByKeyword(String keyword);

}