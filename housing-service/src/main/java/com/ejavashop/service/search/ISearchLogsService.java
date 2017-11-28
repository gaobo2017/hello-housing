package com.ejavashop.service.search;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.search.SearchLogs;

public interface ISearchLogsService {

    /**
     * 根据id取得搜索历史记录表
     * @param  searchLogsId
     * @return
     */
    ServiceResult<SearchLogs> getSearchLogsById(Integer searchLogsId);

    /**
     * 保存搜索历史记录表
     * @param  searchLogs
     * @return
     */
    ServiceResult<Integer> saveSearchLogs(SearchLogs searchLogs);

    /**
    * 更新搜索历史记录表
    * @param  searchLogs
    * @return
    */
    ServiceResult<Integer> updateSearchLogs(SearchLogs searchLogs);

    /**
     * 根据 cookieValue 取得前5条记录用户页面展现
     * @param cookieValue
     * @return
     */
    ServiceResult<List<SearchLogs>> getSearchLogsByCookie(String cookieValue, int number);

    /**
     * 分页查询
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<SearchLogs>> getSearchLogss(Map<String, String> queryMap, PagerInfo pager);
}