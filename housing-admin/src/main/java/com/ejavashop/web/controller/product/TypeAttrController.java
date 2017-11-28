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
import com.ejavashop.entity.product.ProductTypeAttr;
import com.ejavashop.entity.system.Code;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.product.IProductTypeAttrService;
import com.ejavashop.vo.product.ProductTypeAttrVO;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;
import com.ejavashop.web.util.freemarkerutil.CodeManager;

/**
 * 商品类型
 */
@Controller
@RequestMapping(value = "admin/product/typeattr1")
public class TypeAttrController extends BaseController {
    private String baseUrl = "admin/product/typeattr/";

    @Resource
    private IProductTypeAttrService productTypeAttrService;

    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("q_useYn", "1");
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return baseUrl + "attrlist";
    }

    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ProductTypeAttrVO>> list(HttpServletRequest request,
                                                                      Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<ProductTypeAttrVO>> serviceResult = productTypeAttrService
            .pageProductTypeAttr(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<ProductTypeAttrVO>> jsonResult = new HttpJsonResult<List<ProductTypeAttrVO>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(Map<String, Object> dataMap) {
        Code code = new Code();
        //        code.setUseYn(ConstantsJM.USE_YN_Y);
        dataMap.put("code", code);
        return baseUrl + "attradd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult<Object> create(ProductTypeAttr attr, HttpServletRequest request,
                                         Map<String, Object> dataMap) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        if (null == user) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getEJS_URL_RESOURCES() + "/admin/login");
            return jsonResult;
        }
        attr.setCreateId(user.getId());
        ServiceResult<Boolean> serviceResult = productTypeAttrService.saveProductTypeAttr(attr);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(Integer id, Map<String, Object> dataMap) {
        ServiceResult<ProductTypeAttr> serviceResult = productTypeAttrService
            .getProductTypeAttrById(id);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            }
        }
        dataMap.put("attr", serviceResult.getResult());
        return baseUrl + "attredit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult<Object> update(ProductTypeAttr attr, HttpServletRequest request) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        if (null == user) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getEJS_URL_RESOURCES() + "/admin/login");
            return jsonResult;
        }
        ServiceResult<Boolean> serviceResult = productTypeAttrService.updateProductTypeAttr(attr);
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
        ServiceResult<Boolean> serviceResult = productTypeAttrService.delProductTypeAttr(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }
}
