package com.ejavashop.web.controller.search;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.HttpJsonResultForAjax;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.search.SearchKeyword;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.search.ISearchSettingService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 搜索敏感词相关内容
 *                       
 * @Filename: SearchKeyWordController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
@RequestMapping(value = "admin/searchkeyword")
public class SearchKeyWordController extends BaseController {

    @Resource
    private ISearchSettingService searchSettingService;

    /**
     * 默认，显示全部搜索词
     *
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/search/keywordlist";
    }

    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<SearchKeyword>> list(HttpServletRequest request,
                                                                  Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<SearchKeyword>> serviceResult = searchSettingService
            .getSearchKeywords(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<SearchKeyword>> jsonResult = new HttpJsonResult<List<SearchKeyword>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(Map<String, Object> dataMap) {

        return "admin/search/keywordadd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(SearchKeyword searchKeyword, HttpServletRequest request,
                         Map<String, Object> dataMap) {
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        searchKeyword.setCreateId(user.getId());
        searchKeyword.setCreateName(user.getName());

        ServiceResult<Integer> serviceResult = searchSettingService
            .createSearchKeyword(searchKeyword);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("searchKeyword", searchKeyword);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/search/keywordadd";
            }
        }
        return "redirect:/admin/searchkeyword";
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(Integer id, Map<String, Object> dataMap) {
        ServiceResult<SearchKeyword> serviceResult = searchSettingService.getSearchKeywordById(id);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            }
        }
        dataMap.put("searchKeyword", serviceResult.getResult());
        return "admin/search/keywordedit";
    }

    /**
     * 更新敏感词
     * @param code
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(SearchKeyword searchKeyword, HttpServletRequest request,
                         Map<String, Object> dataMap) {
        ServiceResult<Boolean> serviceResult = searchSettingService
            .updateSearchKeyword(searchKeyword);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("searchKeyword", searchKeyword);
                return "admin/search/keywordedit";
            }
        }
        return "redirect:/admin/searchkeyword";
    }

    /**
     * 删除敏感词
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> del(HttpServletRequest request,
                                                    @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResultForAjax<Object>();
        ServiceResult<Boolean> serviceResult = searchSettingService.delSearchKeyword(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

}
