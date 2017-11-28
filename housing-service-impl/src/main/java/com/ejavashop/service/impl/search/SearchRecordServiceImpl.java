package com.ejavashop.service.impl.search;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.search.SearchRecord;
import com.ejavashop.model.search.SearchRecordModel;
import com.ejavashop.service.search.ISearchRecordService;

@Service(value = "searchRecordService")
public class SearchRecordServiceImpl implements ISearchRecordService {

    private static Logger     log = LogManager.getLogger(SearchRecordServiceImpl.class);

    @Resource
    private SearchRecordModel searchRecordModel;

    /**
    * 根据id取得模糊搜索匹配表
    * @param  searchRecordId
    * @return
    * @see com.ejavashop.service.SearchRecordService#getSearchRecordById(java.lang.Integer)
    */
    @Override
    public ServiceResult<SearchRecord> getSearchRecordById(Integer searchRecordId) {
        ServiceResult<SearchRecord> result = new ServiceResult<SearchRecord>();
        try {
            result.setResult(searchRecordModel.getSearchRecordById(searchRecordId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[SearchRecordServiceImpl][getSearchRecordById]根据id[" + searchRecordId
                      + "]取得模糊搜索匹配表时出现未知异常：" + be);
        } catch (Exception e) {
            log.error("[SearchRecordServiceImpl][getSearchRecordById]根据id[" + searchRecordId
                      + "]取得模糊搜索匹配表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("[SearchRecordServiceImpl][getSearchRecordById]根据id["
                              + searchRecordId + "]取得模糊搜索匹配表时出现未知异常");
        }
        return result;
    }

    /**
     * 保存模糊搜索匹配表
     * @param  searchRecord
     * @return
     * @see com.ejavashop.service.SearchRecordService#saveSearchRecord(SearchRecord)
     */
    @Override
    public ServiceResult<Integer> saveSearchRecord(SearchRecord searchRecord) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(searchRecordModel.saveSearchRecord(searchRecord));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[SearchRecordServiceImpl][saveSearchRecord]保存模糊搜索匹配表时出现未知异常：" + be);
        } catch (Exception e) {
            log.error("[SearchRecordServiceImpl][saveSearchRecord]保存模糊搜索匹配表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("[SearchRecordServiceImpl][saveSearchRecord]保存模糊搜索匹配表时出现未知异常");
        }
        return result;
    }

    /**
    * 更新模糊搜索匹配表
    * @param  searchRecord
    * @return
    * @see com.ejavashop.service.SearchRecordService#updateSearchRecord(SearchRecord)
    */
    @Override
    public ServiceResult<Integer> updateSearchRecord(SearchRecord searchRecord) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(searchRecordModel.updateSearchRecord(searchRecord));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[SearchRecordServiceImpl][updateSearchRecord]更新模糊搜索匹配表时出现未知异常：" + be);
        } catch (Exception e) {
            log.error("[SearchRecordServiceImpl][updateSearchRecord]更新模糊搜索匹配表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("[SearchRecordServiceImpl][updateSearchRecord]更新模糊搜索匹配表时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<List<SearchRecord>> getSearchRecords(Map<String, String> queryMap,
                                                              PagerInfo pager) {
        ServiceResult<List<SearchRecord>> serviceResult = new ServiceResult<List<SearchRecord>>();
        try {
            serviceResult.setResult(searchRecordModel.getSearchRecords(queryMap, pager));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SearchSettingServiceImpl][getSearchRecords] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> delSearchRecord(Integer id) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(searchRecordModel.delSearchRecord(id) > 0);
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[SearchSettingServiceImpl][delSearchRecord]删除模糊搜索出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("[SearchSettingServiceImpl][delSearchRecord]删除模糊搜索现未知异常");
        }
        return result;
    }

    /**
     * 前台搜索框动态匹配
     * @param keyword
     * @param number
     * @return
     * @see com.ejavashop.service.search.ISearchRecordService#getSearchRecordByKeyword(java.lang.String, int)
     */
    @Override
    public ServiceResult<List<SearchRecord>> getSearchRecordByKeyword(String keyword, int number) {
        ServiceResult<List<SearchRecord>> serviceResult = new ServiceResult<List<SearchRecord>>();
        try {
            serviceResult.setResult(searchRecordModel.getSearchRecordByKeyword(keyword, number));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SearchSettingServiceImpl][getSearchRecordByKeyword] exception:", e);
        }
        return serviceResult;
    }
}