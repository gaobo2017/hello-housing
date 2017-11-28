package com.ejavashop.web.controller.search;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.HttpJsonResultForAjax;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.PingYinUtil;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.entity.search.SearchRecord;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.search.ISearchRecordService;
import com.ejavashop.vo.search.SearchProductVO;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 
 * 搜索模糊搜索匹配表                      
 * @Filename: SearchRecordController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
@RequestMapping(value = "admin/searchrecord")
public class SearchRecordController extends BaseController {

    @Resource
    private ISearchRecordService searchRecordService;

    private static SolrClient getSolrClient() {
        String solrUrl = DomainUrlUtil.getEJS_SOLR_URL();
        String solrServer = DomainUrlUtil.getEJS_SOLR_SERVER();
        return new HttpSolrClient(solrUrl + "/" + solrServer);
    }

    /**
     * 默认，显示全部关键词
     *
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/search/searchrecord/searchrecordlist";
    }

    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<SearchRecord>> list(HttpServletRequest request,
                                                                 Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<SearchRecord>> serviceResult = searchRecordService
            .getSearchRecords(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<SearchRecord>> jsonResult = new HttpJsonResult<List<SearchRecord>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(Map<String, Object> dataMap) {

        return "admin/search/searchrecord/searchrecordadd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(SearchRecord searchRecord, HttpServletRequest request,
                         Map<String, Object> dataMap) {
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        searchRecord.setCreateId(user.getId());
        searchRecord.setCreateName(user.getName());

        String keyword = searchRecord.getKeyword();
        keyword = StringUtil.stringFilterSpecial(keyword);
        String searchKeyword = "(" + keyword + ")";
        int count = getSearchIndexCount(searchKeyword);
        searchRecord.setKeyword(keyword);
        searchRecord.setKeywordIndex(count);
        searchRecord.setKeywordPinyin(PingYinUtil.getPingYin(keyword));

        ServiceResult<Integer> serviceResult = searchRecordService.saveSearchRecord(searchRecord);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("searchRecord", searchRecord);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/search/searchrecord/searchrecordadd";
            }
        }
        return "redirect:/admin/searchrecord";
    }

    /**
     * 统计关键词的索引数量
     * @param searchKeyword
     * @return
     */
    private int getSearchIndexCount(String searchKeyword) {
        int count = 0;
        SolrClient client = getSolrClient();
        SolrQuery query = new SolrQuery();

        String searchIndexAssemblingString = SearchProductVO.searchIndexAssembling(searchKeyword);
        query.setQuery(searchIndexAssemblingString);

        QueryResponse response = null;
        try {
            response = client.query(query);
            SolrDocumentList docs = response.getResults();
            count = new Integer(docs.getNumFound() + "");
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(Integer id, Map<String, Object> dataMap) {
        ServiceResult<SearchRecord> serviceResult = searchRecordService.getSearchRecordById(id);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            }
        }
        dataMap.put("searchRecord", serviceResult.getResult());
        return "admin/search/searchrecord/searchrecordedit";
    }

    /**
     * 更新关键词
     * @param code
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(SearchRecord searchRecord, HttpServletRequest request,
                         Map<String, Object> dataMap) {
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        searchRecord.setCreateId(user.getId());
        searchRecord.setCreateName(user.getName());

        String keyword = searchRecord.getKeyword();
        keyword = StringUtil.stringFilterSpecial(keyword);
        String searchKeyword = "(" + keyword + ")";
        int count = getSearchIndexCount(searchKeyword);
        searchRecord.setKeyword(keyword);
        searchRecord.setKeywordIndex(count);
        searchRecord.setKeywordPinyin(PingYinUtil.getPingYin(keyword));

        ServiceResult<Integer> serviceResult = searchRecordService.updateSearchRecord(searchRecord);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("searchRecord", searchRecord);
                return "/search/searchrecord/searchrecordedit";
            }
        }
        return "redirect:/admin/searchrecord";
    }

    /**
     * 删除关键词
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> del(HttpServletRequest request,
                                                    @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResultForAjax<Object>();
        ServiceResult<Boolean> serviceResult = searchRecordService.delSearchRecord(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

}
