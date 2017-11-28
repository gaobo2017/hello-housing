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
import com.ejavashop.entity.search.SearchKeyword;
import com.ejavashop.entity.search.SearchSetting;
import com.ejavashop.model.search.SearchSettingModel;
import com.ejavashop.service.search.ISearchSettingService;

/**
 * 搜索相关设置
 *                       
 * @Filename: SearchSettingServiceImpl.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Service(value = "searchSettingService")
public class SearchSettingServiceImpl implements ISearchSettingService {

    private static Logger      log = LogManager.getLogger(SearchSettingServiceImpl.class);

    @Resource
    private SearchSettingModel searchSettingModel;

    /**
    * 根据id取得search_setting对象
    * @param  searchSettingId
    * @return
    * @see com.ejavashop.search.service.SearchSettingService#getSearchSettingById(java.lang.Integer)
    */
    @Override
    public ServiceResult<SearchSetting> getSearchSettingById(Integer searchSettingId) {
        ServiceResult<SearchSetting> result = new ServiceResult<SearchSetting>();
        try {
            result.setResult(searchSettingModel.getSearchSettingById(searchSettingId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[SearchSettingServiceImpl][getSearchSettingById]根据id[" + searchSettingId
                      + "]取得search_setting对象时出现未知异常：" + be);
        } catch (Exception e) {
            log.error("[SearchSettingServiceImpl][getSearchSettingById]根据id[" + searchSettingId
                      + "]取得search_setting对象时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("[SearchSettingServiceImpl][getSearchSettingById]根据id["
                              + searchSettingId + "]取得search_setting对象时出现未知异常");
        }
        return result;
    }

    /**
     * 保存search_setting对象
     * @param  searchSetting
     * @return
     * @see com.ejavashop.search.service.SearchSettingService#saveSearchSetting(SearchSetting)
     */
    @Override
    public ServiceResult<Integer> saveSearchSetting(SearchSetting searchSetting) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(searchSettingModel.saveSearchSetting(searchSetting));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[SearchSettingServiceImpl][saveSearchSetting]保存search_setting对象时出现未知异常："
                      + be);
        } catch (Exception e) {
            log.error("[SearchSettingServiceImpl][saveSearchSetting]保存search_setting对象时出现未知异常：" + e);
            result.setSuccess(false);
            result
                .setMessage("[SearchSettingServiceImpl][saveSearchSetting]保存search_setting对象时出现未知异常");
        }
        return result;
    }

    /**
    * 更新search_setting对象
    * @param  searchSetting
    * @return
    * @see com.ejavashop.search.service.SearchSettingService#updateSearchSetting(SearchSetting)
    */
    @Override
    public ServiceResult<Integer> updateSearchSetting(SearchSetting searchSetting) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(searchSettingModel.updateSearchSetting(searchSetting));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[SearchSettingServiceImpl][updateSearchSetting]更新search_setting对象时出现未知异常："
                      + be);
        } catch (Exception e) {
            log.error("[SearchSettingServiceImpl][updateSearchSetting]更新search_setting对象时出现未知异常："
                      + e);
            result.setSuccess(false);
            result
                .setMessage("[SearchSettingServiceImpl][updateSearchSetting]更新search_setting对象时出现未知异常");
        }
        return result;
    }

    /**
     * 更新关键词
     * @param id
     * @param keyword
     * @return
     * @see com.ejavashop.service.search.ISearchSettingService#updateKeyword(int, java.lang.String)
     */
    @Override
    public ServiceResult<Integer> updateKeyword(int id, String keyword) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(searchSettingModel.updateKeyword(id, keyword));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[SearchSettingServiceImpl][updateKeyword]更新关键词出现未知异常：" + be);
        } catch (Exception e) {
            log.error("[SearchSettingServiceImpl][updateKeyword]更新关键词出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("[SearchSettingServiceImpl][updateKeyword]更新关键词出现未知异常");
        }
        return result;
    }

    /**
     * 更新查询关键字
     * @param id
     * @param keywordFilter
     * @return
     * @see com.ejavashop.service.search.ISearchSettingService#updateKeywordFilter(int, int)
     */
    @Override
    public ServiceResult<Integer> updateKeywordFilter(int id, int keywordFilter) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(searchSettingModel.updateKeywordFilter(id, keywordFilter));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[SearchSettingServiceImpl][updateKeywordFilter]更新查询关键字时出现未知异常：" + be);
        } catch (Exception e) {
            log.error("[SearchSettingServiceImpl][updateKeywordFilter]更新查询关键字时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("[SearchSettingServiceImpl][updateKeywordFilter]更新查询关键字时出现未知异常");
        }
        return result;
    }

    /**
     * 关键字查询
     * @param queryMap
     * @param pager
     * @return
     * @see com.ejavashop.service.search.ISearchSettingService#getSearchKeywords(java.util.Map, com.ejavashop.core.PagerInfo)
     */
    @Override
    public ServiceResult<List<SearchKeyword>> getSearchKeywords(Map<String, String> queryMap,
                                                                PagerInfo pager) {
        ServiceResult<List<SearchKeyword>> serviceResult = new ServiceResult<List<SearchKeyword>>();
        try {
            serviceResult.setResult(searchSettingModel.getSearchKeywords(queryMap, pager));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SearchSettingServiceImpl][getSearchKeywords] exception:", e);
        }
        return serviceResult;
    }

    /**
     * 插入敏感词
     * @param searchKeyword
     * @return
     * @see com.ejavashop.service.search.ISearchSettingService#createSearchKeyword(com.ejavashop.entity.search.SearchKeyword)
     */
    @Override
    public ServiceResult<Integer> createSearchKeyword(SearchKeyword searchKeyword) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(searchSettingModel.createSearchKeyword(searchKeyword));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[SearchSettingServiceImpl][createSearchKeyword]插入敏感词出现未知异常：" + be);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[SearchSettingServiceImpl][createSearchKeyword]插入敏感词出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("[SearchSettingServiceImpl][createSearchKeyword]插入敏感词出现未知异常");
        }
        return result;
    }

    /**
     * 根据ID获取敏感词
     * @param id
     * @return
     * @see com.ejavashop.service.search.ISearchSettingService#getSearchKeywordById(java.lang.Integer)
     */
    @Override
    public ServiceResult<SearchKeyword> getSearchKeywordById(Integer id) {
        ServiceResult<SearchKeyword> result = new ServiceResult<SearchKeyword>();
        try {
            result.setResult(searchSettingModel.getSearchKeywordById(id));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[SearchSettingServiceImpl][getSearchKeywordById]根据ID获取敏感词出现未知异常：" + be);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[SearchSettingServiceImpl][getSearchKeywordById]根据ID获取敏感词出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("[SearchSettingServiceImpl][getSearchKeywordById]根据ID获取敏感词出现未知异常");
        }
        return result;
    }

    /**
     * 更新敏感词
     * @param searchKeyword
     * @return
     * @see com.ejavashop.service.search.ISearchSettingService#updateSearchKeyword(com.ejavashop.entity.search.SearchKeyword)
     */
    @Override
    public ServiceResult<Boolean> updateSearchKeyword(SearchKeyword searchKeyword) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(searchSettingModel.updateSearchKeyword(searchKeyword) > 0);
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[SearchSettingServiceImpl][updateSearchKeyword]更新敏感词出现未知异常：" + be);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[SearchSettingServiceImpl][updateSearchKeyword]更新敏感词出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("[SearchSettingServiceImpl][updateSearchKeyword]更新敏感词出现未知异常");
        }
        return result;
    }

    /**
     * 删除敏感词
     * @param id
     * @return
     * @see com.ejavashop.service.search.ISearchSettingService#delSearchKeyword(java.lang.Integer)
     */
    @Override
    public ServiceResult<Boolean> delSearchKeyword(Integer id) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(searchSettingModel.delSearchKeyword(id) > 0);
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[SearchSettingServiceImpl][delSearchKeyword]删除敏感词出现未知异常：" + be);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[SearchSettingServiceImpl][delSearchKeyword]删除敏感词出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("[SearchSettingServiceImpl][delSearchKeyword]删除敏感词出现未知异常");
        }
        return result;
    }

    /**
     * 根据查询词，查询敏感词
     * @param keyword
     * @return
     * @see com.ejavashop.service.search.ISearchSettingService#getSearchKeywordsByKeyword(java.lang.String)
     */
    @Override
    public ServiceResult<Integer> getSearchKeywordsByKeyword(String keyword) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(searchSettingModel.getSearchKeywordsByKeyword(keyword));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[SearchSettingServiceImpl][getSearchKeywordsByKeyword]根据查询词，查询敏感词出现未知异常："
                      + be);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[SearchSettingServiceImpl][getSearchKeywordsByKeyword]根据查询词，查询敏感词出现未知异常："
                      + e);
            result.setSuccess(false);
            result
                .setMessage("[SearchSettingServiceImpl][getSearchKeywordsByKeyword]根据查询词，查询敏感词出现未知异常");
        }
        return result;
    }

}