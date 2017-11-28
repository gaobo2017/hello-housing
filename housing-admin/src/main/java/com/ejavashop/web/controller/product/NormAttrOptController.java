package com.ejavashop.web.controller.product;

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
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.entity.product.ProductNormAttrOpt;
import com.ejavashop.entity.system.Code;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.product.IProductNormService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;
import com.ejavashop.web.util.freemarkerutil.CodeManager;

/**
 * 商品规格属性
 */
@Controller
@RequestMapping(value = "admin/product/normattropt")
public class NormAttrOptController extends BaseController {
    private String baseUrl = "admin/product/normattropt/";

    @Resource
    private IProductNormService productNormService;

    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("q_useYn", "1");
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return baseUrl + "attrlist";
    }

    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ProductNormAttrOpt>> list(HttpServletRequest request,
                                                                       Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<ProductNormAttrOpt>> serviceResult = productNormService
            .pageNormAttrOpt(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<ProductNormAttrOpt>> jsonResult = new HttpJsonResult<List<ProductNormAttrOpt>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(Map<String, Object> dataMap) {
        Code code = new Code();
        //        code.setUseYn(AdminConstants.USE_YN_Y);
        dataMap.put("code", code);
        return baseUrl + "attroptadd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult<Object> create(ProductNormAttrOpt opt, HttpServletRequest request,
                                         Map<String, Object> dataMap) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        if (null == user) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getEJS_URL_RESOURCES() + "/admin/login");
            return jsonResult;
        }
        opt.setCreateId(user.getId());
        ServiceResult<Boolean> serviceResult = productNormService.saveNormAttrOpt(opt);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(Integer id, Map<String, Object> dataMap) {
        ServiceResult<ProductNormAttrOpt> serviceResult = productNormService.getNormAttrOptById(id);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            }
        }
        dataMap.put("opt", serviceResult.getResult());
        return baseUrl + "optedit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult<Object> update(ProductNormAttrOpt opt, HttpServletRequest request) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        if (null == user) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getEJS_URL_RESOURCES() + "/admin/login");
            return jsonResult;
        }
        ServiceResult<Boolean> serviceResult = productNormService.updateNormAttrOpt(opt);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        CodeManager.init();
        return jsonResult;
    }

    @RequestMapping(value = "del", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> del(HttpServletRequest request,
                                                    @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        ServiceResult<Boolean> serviceResult = productNormService.delNormAttrOpt(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }
}
