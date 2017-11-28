package com.ejavashop.service.search;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.search.SearchRecord;

public interface ISearchRecordService {

    /**
     * 根据id取得模糊搜索匹配表
     * @param  searchRecordId
     * @return
     */
    ServiceResult<SearchRecord> getSearchRecordById(Integer searchRecordId);

    /**
     * 保存模糊搜索匹配表
     * @param  searchRecord
     * @return
     */
    ServiceResult<Integer> saveSearchRecord(SearchRecord searchRecord);

    /**
    * 更新模糊搜索匹配表
    * @param  searchRecord
    * @return
    */
    ServiceResult<Integer> updateSearchRecord(SearchRecord searchRecord);

    /**
     * 关键字查询
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<SearchRecord>> getSearchRecords(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 删除敏感词
     * @param id
     * @return
     */
    ServiceResult<Boolean> delSearchRecord(Integer id);

    /**
     * 前台搜索框动态匹配
     * @param keyword
     * @param number
     * @return
     */
    ServiceResult<List<SearchRecord>> getSearchRecordByKeyword(String keyword, int number);

}