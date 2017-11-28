package com.ejavashop.web.controller.news;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.news.News;
import com.ejavashop.entity.news.NewsType;
import com.ejavashop.service.news.INewsService;
import com.ejavashop.service.news.INewsTypeService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 文章相关action
 *                       
 * @Filename: AdminNewsController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/system/news")
public class AdminNewsController extends BaseController {

    @Resource
    private INewsService     newsService;

    @Resource
    private INewsTypeService newsTypeService;

    Logger                   log = Logger.getLogger(this.getClass());

    /**
     * 默认页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        dataMap.put("queryMap", queryMap);
        return "admin/news/news/list";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<News>> list(HttpServletRequest request,
                                                         Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<News>> serviceResult = newsService.page(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<News>> jsonResult = new HttpJsonResult<List<News>>();
        jsonResult.setRows((List<News>) serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    /**
     * 新增页面
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request, Map<String, Object> dataMap) {
        List<NewsType> typelist = this.newsTypeService.list();
        dataMap.put("typelist", typelist);

        return "admin/news/news/edit";
    }

    /**
     * 编辑页面
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(HttpServletRequest request, Map<String, Object> dataMap, String id) {
        News news = this.newsService.getNewsById(Integer.valueOf(id)).getResult();

        List<NewsType> typelist = this.newsTypeService.list();
        dataMap.put("typelist", typelist);

        dataMap.put("obj", news);
        return "admin/news/news/edit";
    }

    /**
     * 添加分类
     * @param request
     * @param response
     * @param news
     * @return
     */
    @RequestMapping(value = "doAdd", method = { RequestMethod.POST })
    public String doAdd(HttpServletRequest request, HttpServletResponse response, News news) {
        news.setCreateTime(new Date());

        if (news.getId() != null && news.getId() != 0) {
            news.setUpdateTime(new Date());

            if (!StringUtil.isEmpty(news.getContent())) {
                String description = news.getContent();
                description = description.replaceAll(System.getProperty("line.separator"), "");
                news.setContent(description);
            }

            this.newsService.updateNews(news);
        } else {
            news.setCreateTime(new Date());
            news.setUpdateTime(new Date());
            news.setCreateId(WebAdminSession.getAdminUser(request).getId());
            news.setAuthor(WebAdminSession.getAdminUser(request).getName());
            news.setSource("");

            if (!StringUtil.isEmpty(news.getContent())) {
                String description = news.getContent();
                description = description.replaceAll(System.getProperty("line.separator"), "");
                news.setContent(description);
            }
            this.newsService.saveNews(news);
        }

        return "redirect:/admin/system/news";
    }

    /**
     * 删除
     * @param request
     * @param response
     * @param news
     * @return
     */
    @RequestMapping(value = "del", method = { RequestMethod.GET })
    public void del(HttpServletRequest request, HttpServletResponse response, String id) {
        this.newsService.del(Integer.valueOf(id));
    }
}
