package com.ejavashop.web.controller.operate;

import java.util.Date;
import java.util.HashMap;
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
import com.ejavashop.entity.operate.CourierCompany;
import com.ejavashop.service.operate.ICourierCompanyService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.UploadUtil;

/**
 * 快递公司管理controller
 *                       
 * @Filename: AdminCourierCompanyController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/operate/courierCompany")
public class AdminCourierCompanyController extends BaseController {
    @Resource
    private ICourierCompanyService courierCompanyService;
    Logger                         log = Logger.getLogger(this.getClass());

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
        return "admin/operate/couriercompany/list";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<CourierCompany>> list(HttpServletRequest request,
                                                                   Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<CourierCompany>> serviceResult = courierCompanyService.page(queryMap,
            pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<CourierCompany>> jsonResult = new HttpJsonResult<List<CourierCompany>>();
        jsonResult.setRows((List<CourierCompany>) serviceResult.getResult());
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
        return "admin/operate/couriercompany/edit";
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
        CourierCompany obj = this.courierCompanyService.getCourierCompanyById(Integer.valueOf(id))
            .getResult();
        dataMap.put("obj", obj);
        return "admin/operate/couriercompany/edit";
    }

    /**
     * 设计快递打印模板
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "editCourierCompany", method = { RequestMethod.GET })
    public String editCourierCompany(HttpServletRequest request, Map<String, Object> dataMap,
                                     Integer id) {
        CourierCompany obj = this.courierCompanyService.getCourierCompanyById(id).getResult();
        dataMap.put("obj", obj);
        return "admin/operate/couriercompany/couriercompany";
    }

    /**
     * 添加快递公司
     * @param request
     * @param response
     * @param news
     * @return
     */
    @RequestMapping(value = "doAdd", method = { RequestMethod.POST })
    public String doAdd(HttpServletRequest request, HttpServletResponse response,
                        CourierCompany obj) {
        Map<String, String> param = new HashMap<>();
        param.put("admin", "couriercompany");

        // 上传图片
        String image = UploadUtil.getInstance().uploadFile2ImageServer("imageFile", request, param);
        if (image != null && !"".equals(image)) {
            obj.setImagePath(image);
        }

        obj.setCreateTime(new Date());
        if (obj.getId() != null && obj.getId() != 0) {
            this.courierCompanyService.updateCourierCompany(obj);
        } else {
            obj.setCreateTime(new Date());
            obj.setSellerId(0);
            this.courierCompanyService.saveCourierCompany(obj);
        }

        return "redirect:/admin/operate/courierCompany";
    }

    /**
     * 删除
     * @param request
     * @param response
     * @param obj
     * @return
     */
    @RequestMapping(value = "del", method = { RequestMethod.GET })
    public void del(HttpServletRequest request, HttpServletResponse response, String id) {
        this.courierCompanyService.del(Integer.valueOf(id));
    }
}
