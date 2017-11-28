package com.ejavashop.service.impl.search;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.search.SearchLogs;
import com.ejavashop.model.search.SearchLogsModel;
import com.ejavashop.service.search.ISearchLogsService;

@Service(value = "searchLogsService")
public class SearchLogsServiceImpl implements ISearchLogsService {

    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(SearchLogsServiceImpl.class);

    @Resource
    private SearchLogsModel                searchLogsModel;

    /**
    * 根据id取得搜索历史记录表
    * @param  searchLogsId
    * @return
    * @see com.ejavashop.service.SearchLogsService#getSearchLogsById(java.lang.Integer)
    */
    @Override
    public ServiceResult<SearchLogs> getSearchLogsById(Integer searchLogsId) {
        ServiceResult<SearchLogs> result = new ServiceResult<SearchLogs>();
        try {
            result.setResult(searchLogsModel.getSearchLogsById(searchLogsId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[SearchLogsServiceImpl][getSearchLogsById]根据id[" + searchLogsId
                      + "]取得搜索历史记录表时出现未知异常：" + be);
        } catch (Exception e) {
            log.error("[SearchLogsServiceImpl][getSearchLogsById]根据id[" + searchLogsId
                      + "]取得搜索历史记录表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("[SearchLogsServiceImpl][getSearchLogsById]根据id[" + searchLogsId
                              + "]取得搜索历史记录表时出现未知异常");
        }
        return result;
    }

    /**
     * 保存搜索历史记录表
     * @param  searchLogs
     * @return
     * @see com.ejavashop.service.SearchLogsService#saveSearchLogs(SearchLogs)
     */
    @Override
    public ServiceResult<Integer> saveSearchLogs(SearchLogs searchLogs) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(searchLogsModel.saveSearchLogs(searchLogs));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[SearchLogsServiceImpl][saveSearchLogs]保存搜索历史记录表时出现未知异常：" + be);
        } catch (Exception e) {
            log.error("[SearchLogsServiceImpl][saveSearchLogs]保存搜索历史记录表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("[SearchLogsServiceImpl][saveSearchLogs]保存搜索历史记录表时出现未知异常");
        }
        return result;
    }

    /**
    * 更新搜索历史记录表
    * @param  searchLogs
    * @return
    * @see com.ejavashop.service.SearchLogsService#updateSearchLogs(SearchLogs)
    */
    @Override
    public ServiceResult<Integer> updateSearchLogs(SearchLogs searchLogs) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(searchLogsModel.updateSearchLogs(searchLogs));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[SearchLogsServiceImpl][updateSearchLogs]更新搜索历史记录表时出现未知异常：" + be);
        } catch (Exception e) {
            log.error("[SearchLogsServiceImpl][updateSearchLogs]更新搜索历史记录表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("[SearchLogsServiceImpl][updateSearchLogs]更新搜索历史记录表时出现未知异常");
        }
        return result;
    }

    /**
     * 根据cookie获取搜索最近N条记录
     * @param cookieValue
     * @return
     * @see com.ejavashop.service.search.ISearchLogsService#getSearchLogsByCookie(java.lang.String)
     */
    @Override
    public ServiceResult<List<SearchLogs>> getSearchLogsByCookie(String cookieValue, int number) {
        ServiceResult<List<SearchLogs>> result = new ServiceResult<List<SearchLogs>>();
        try {
            result.setResult(searchLogsModel.getSearchLogsByCookie(cookieValue, number));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[SearchLogsServiceImpl][getSearchLogsByCookie]根据cookie获取搜索最近N条记录出现业务异常："
                      + be);
        } catch (Exception e) {
            log.error("[SearchLogsServiceImpl][getSearchLogsByCookie]根据cookie获取搜索最近N条记录时出现未知异常："
                      + e);
            result.setSuccess(false);
            result
                .setMessage("[SearchLogsServiceImpl][getSearchLogsByCookie]根据cookie获取搜索最近N条记录出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<List<SearchLogs>> getSearchLogss(Map<String, String> queryMap,
                                                          PagerInfo pager) {
        ServiceResult<List<SearchLogs>> serviceResult = new ServiceResult<List<SearchLogs>>();
        try {
            serviceResult.setResult(searchLogsModel.getSearchLogss(queryMap, pager));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SearchLogsServiceImpl][getSearchLogss] exception:", e);
        }
        return serviceResult;
    }
}