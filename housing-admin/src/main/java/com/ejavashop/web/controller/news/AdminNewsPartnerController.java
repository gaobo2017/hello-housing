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
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.news.NewsPartner;
import com.ejavashop.service.news.INewsPartnerService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.UploadUtil;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 合作伙伴相关action
 *                       
 * @Filename: AdminNewsPartnerController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/system/newsParter")
public class AdminNewsPartnerController extends BaseController {

    @Resource
    private INewsPartnerService newsPartnerService;

    Logger                      log = Logger.getLogger(this.getClass());

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
        return "admin/news/newsparter/list";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<NewsPartner>> list(HttpServletRequest request,
                                                                Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<NewsPartner>> serviceResult = newsPartnerService.page(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<NewsPartner>> jsonResult = new HttpJsonResult<List<NewsPartner>>();
        jsonResult.setRows((List<NewsPartner>) serviceResult.getResult());
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
    public String add(HttpServletRequest request) {
        return "admin/news/newsparter/edit";
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
        NewsPartner np = this.newsPartnerService.getById(Integer.valueOf(id)).getResult();
        dataMap.put("obj", np);
        return "admin/news/newsparter/edit";
    }

    /**
     * 添加分类
     * @param request
     * @param response
     * @param news
     * @return
     */
    @RequestMapping(value = "doAdd", method = { RequestMethod.POST })
    public String doAdd(HttpServletRequest request, HttpServletResponse response, NewsPartner np) {
        String image = (String) UploadUtil.getInstance()
            .uploadFile2LocServer(request, "imagepic", "upload/newsType").get("filePath");

        if (np.getImage() != null && !"".equals(image)) {
            np.setImage(image);
        }

        np.setCreateTime(new Date());
        if (np.getId() != null && np.getId() != 0) {
            np.setUpdateTime(new Date());
            this.newsPartnerService.update(np);
        } else {
            np.setCreateTime(new Date());
            np.setUpdateTime(new Date());
            np.setCreateId(WebAdminSession.getAdminUser(request).getId());
            np.setStatus(ConstantsEJS.NEWS_PARTNER_DISPLAY_YES);
            this.newsPartnerService.save(np);
        }

        return "redirect:/admin/system/newsParter";
    }

    /**
     * 删除
     * @param request
     * @param response
     * @param np
     * @return
     */
    @RequestMapping(value = "del", method = { RequestMethod.GET })
    public void del(HttpServletRequest request, HttpServletResponse response, String id) {
        this.newsPartnerService.del(Integer.valueOf(id));
    }
}
