package com.ejavashop.web.controller.search;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.search.SearchSetting;
import com.ejavashop.service.search.ISearchSettingService;
import com.ejavashop.web.controller.BaseController;

/**
 * 搜索相关设置
 *                       
 * @Filename: SearchAdminController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
@RequestMapping(value = "admin/search")
public class SearchSetingKeyWordController extends BaseController {

    @Resource
    private ISearchSettingService searchSettingService;

    /**
     * 查询关键字
     *
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/keyword", method = { RequestMethod.GET })
    public String keyword(HttpServletRequest request, Map<String, Object> dataMap) throws Exception {

        getById(dataMap);
        return "admin/search/keyword";
    }

    /**
     * 更新查询关键字
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateKeyword", method = { RequestMethod.POST })
    public String updateKeyword(HttpServletRequest request, Map<String, Object> dataMap)
                                                                                        throws Exception {
        String keyword = request.getParameter("keyword");
        ServiceResult<Integer> serviceResult = searchSettingService.updateKeyword(
            ConstantsEJS.SEARCHSETTINGID, keyword);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        getById(dataMap);

        dataMap.put("message", "操作成功");
        return "admin/search/keyword";
    }

    /**
     * 查询关键字
     *
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/keywordfilter", method = { RequestMethod.GET })
    public String keywordFilter(HttpServletRequest request, Map<String, Object> dataMap)
                                                                                        throws Exception {
        getById(dataMap);

        return "admin/search/keywordfilter";
    }

    /**
     * 更新查询关键字
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateKeywordfilter", method = { RequestMethod.POST })
    public String updateKeywordFilter(HttpServletRequest request, Map<String, Object> dataMap)
                                                                                              throws Exception {
        String keywordFilterStr = request.getParameter("keywordFilter");
        int keywordFilter = ConvertUtil.toInt(keywordFilterStr, 1);
        ServiceResult<Integer> serviceResult = searchSettingService.updateKeywordFilter(
            ConstantsEJS.SEARCHSETTINGID, keywordFilter);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        getById(dataMap);
        dataMap.put("message", "操作成功");
        return "admin/search/keywordfilter";
    }

    private void getById(Map<String, Object> dataMap) {
        ServiceResult<SearchSetting> serviceResult = searchSettingService
            .getSearchSettingById(ConstantsEJS.SEARCHSETTINGID);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        SearchSetting searchSetting = serviceResult.getResult();
        dataMap.put("searchSetting", searchSetting);
    }

}
