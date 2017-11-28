package com.ejavashop.web.controller.news;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.ejavashop.entity.news.NewsType;
import com.ejavashop.service.news.INewsTypeService;
import com.ejavashop.vo.system.TreeModel;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.freemarkerutil.CodeManager;
import com.google.gson.Gson;

/**
 * 文章分类相关action
 *                       
 * @Filename: AdminNewsTypeController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/system/newstype")
public class AdminNewsTypeController extends BaseController {

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
        return "admin/news/newstype/list";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<NewsType>> list(HttpServletRequest request,
                                                             Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<NewsType>> serviceResult = newsTypeService.page(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<NewsType>> jsonResult = new HttpJsonResult<List<NewsType>>();
        jsonResult.setRows((List<NewsType>) serviceResult.getResult());
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
        return "admin/news/newstype/edit";
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
        NewsType newstype = this.newsTypeService.getNewsTypeById(Integer.valueOf(id)).getResult();
        dataMap.put("obj", newstype);
        return "admin/news/newstype/edit";
    }

    /**
     * 分类树
     * @param request
     * @param response
     * @param dataMap
     */
    @RequestMapping(value = "newsTypeTree", method = { RequestMethod.GET })
    public void newsTypeTree(HttpServletRequest request, HttpServletResponse response,
                             Map<String, Object> dataMap) {
        response.setContentType("text/html;charset=utf-8");

        List<NewsType> typelist = this.newsTypeService.list();

        List<TreeModel> trees = new ArrayList<TreeModel>();
        TreeModel tree = new TreeModel();
        tree.setId(0);
        tree.setText(CodeManager.getCodeText("NEWS_TREE_ROOT_TEXT", "0"));
        tree = generateTree(typelist, tree, 0);
        trees.add(tree);

        Gson gson = new Gson();

        String jsonStr = gson.toJson(trees);
        try {
            PrintWriter pw = response.getWriter();
            pw.write(jsonStr);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成树
     * @param listResources
     * @param tree
     * @param id
     * @param resIds
     * @return
     */
    public TreeModel generateTree(List<NewsType> typelist, TreeModel tree, Integer id) {
        TreeModel tm = null;
        for (NewsType res : typelist) {
            if (res.getPid().intValue() == id.intValue()) {
                tm = new TreeModel();
                tm.setId(res.getId());
                tm.setText(res.getName());
                tm.setPid(res.getPid());
                tree.getChildren().add(tm);

                generateTree(typelist, tm, tm.getId());
            }
        }
        return tree;
    }

    /**
     * 添加分类
     * @param request
     * @param response
     * @param newstype
     * @return
     */
    @RequestMapping(value = "doAdd", method = { RequestMethod.POST })
    public String doAdd(HttpServletRequest request, HttpServletResponse response,
                        NewsType newstype) {
        //        String image = (String) UploadUtil.getInstance()
        //            .uploadFile2LocServer(request, "imagepic", "upload/newsType").get("filePath");

        //暂时不分层级
        newstype.setPid(0);
        newstype.setParentPath("");
        newstype.setImage("");

        //        if (newstype.getImage() != null && !"".equals(image))
        //            newstype.setImage(image);
        newstype.setCreateTime(new Date());
        if (newstype.getId() != null && newstype.getId() != 0)
            this.newsTypeService.updateNewsType(newstype);
        else
            this.newsTypeService.saveNewsType(newstype);

        return "redirect:/admin/system/newstype";
    }

    /**
     * 删除
     * @param request
     * @param response
     * @param newstype
     * @return
     */
    @RequestMapping(value = "del", method = { RequestMethod.GET })
    public void del(HttpServletRequest request, HttpServletResponse response, String id) {
        this.newsTypeService.del(Integer.valueOf(id));
    }
}
