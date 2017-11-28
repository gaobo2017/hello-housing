package com.ejavashop.web.controller.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.entity.product.ProductType;
import com.ejavashop.entity.product.ProductTypeAttr;
import com.ejavashop.entity.system.Code;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.product.IProductTypeService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;
import com.ejavashop.web.util.freemarkerutil.CodeManager;

/**
 * 商品类型
 */
@Controller
@RequestMapping(value = "admin/product/type")
public class TypeController extends BaseController {
    private String              baseUrl = "admin/product/type/";

    @Resource
    private IProductTypeService productTypeService;

    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("q_useYn", "1");
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return baseUrl + "typelist";
    }

    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ProductType>> list(HttpServletRequest request,
                                                                Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<ProductType>> serviceResult = productTypeService
            .pageProductType(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<ProductType>> jsonResult = new HttpJsonResult<List<ProductType>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(Map<String, Object> dataMap) {
        Code code = new Code();
        //        code.setUseYn(AdminConstants.USE_YN_Y);
        dataMap.put("code", code);
        return baseUrl + "typeadd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult<Object> create(ProductType type, HttpServletRequest request,
                                         Map<String, Object> dataMap) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        if (null == user) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getEJS_URL_RESOURCES() + "/admin/login");
            return jsonResult;
        }
        type.setCreateId(user.getId());

        //规格
        if (!StringUtil.isEmpty(type.getProductNormIds())) {
            String[] normIds = type.getProductNormIds().split(",");
            int[] array = new int[normIds.length];
            for (int i = 0; i < normIds.length; i++) {
                if (!StringUtil.isEmpty(normIds[i])) {
                    array[i] = Integer.valueOf(normIds[i]).intValue();
                }
            }
            //排序
            int length = array.length - 1;
            for (int out = length; out > 0; out--) {
                for (int in = 0; in < out; in++) {
                    if (array[in] > array[in + 1]) {
                        int s = array[in];
                        array[in] = array[in + 1];
                        array[in + 1] = s;
                    }
                }
            }
            //放入原数据集
            String newstr = "";
            for (int i : array) {
                newstr += i + ",";
            }
            type.setProductNormIds(newstr.substring(0, newstr.length() - 1));
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", type);
        map.put("attr", processAttr(request.getParameter("attrVal"), user.getId()));
        ServiceResult<Boolean> serviceResult = productTypeService.saveProductType(map);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(Integer id, Map<String, Object> dataMap) {
        ServiceResult<ProductType> serviceResult = productTypeService.getProductTypeById(id);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            }
        }

        ServiceResult<List<ProductTypeAttr>> result = productTypeService.getAttrByTypeId(id);
        if (!result.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(result.getCode())) {
                throw new RuntimeException(result.getMessage());
            }
        }

        List<ProductTypeAttr> attrList = new ArrayList<ProductTypeAttr>();
        List<ProductTypeAttr> custAttrList = new ArrayList<ProductTypeAttr>();
        dataMap.put("type", serviceResult.getResult());//商品类型
        dataMap.put("attr", attrList);//检索属性
        dataMap.put("custAttr", custAttrList);//自定义属性

        if (null != result.getResult()) {
            List<ProductTypeAttr> list = result.getResult();
            for (int i = 0; i < list.size(); i++) {
                ProductTypeAttr attr = list.get(i);
                if (attr.getType() == 1) {
                    attrList.add(attr);
                } else {
                    custAttrList.add(attr);
                }
            }
        }

        return baseUrl + "typeedit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult<Object> update(ProductType type, HttpServletRequest request) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        if (null == user) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getEJS_URL_RESOURCES() + "/admin/login");
            return jsonResult;

        }

        //规格
        if (!StringUtil.isEmpty(type.getProductNormIds())) {
            String[] normIds = type.getProductNormIds().split(",");
            int[] array = new int[normIds.length];
            for (int i = 0; i < normIds.length; i++) {
                if (!StringUtil.isEmpty(normIds[i])) {
                    array[i] = Integer.valueOf(normIds[i]).intValue();
                }
            }
            //排序
            int length = array.length - 1;
            for (int out = length; out > 0; out--) {
                for (int in = 0; in < out; in++) {
                    if (array[in] > array[in + 1]) {
                        int s = array[in];
                        array[in] = array[in + 1];
                        array[in + 1] = s;
                    }
                }
            }
            //放入原数据集
            String newstr = "";
            for (int i : array) {
                newstr += i + ",";
            }
            type.setProductNormIds(newstr.substring(0, newstr.length() - 1));
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", type);
        map.put("attr", processAttr(request.getParameter("attrVal"), user.getId()));

        ServiceResult<Boolean> serviceResult = productTypeService.updateProductType(map);
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
        ServiceResult<Boolean> serviceResult = productTypeService.delProductType(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "getById", method = { RequestMethod.GET })
    @ResponseBody
    public HttpJsonResult<Object> getById(Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        ServiceResult<ProductType> serviceResult = productTypeService.getProductTypeById(id);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            }
        }
        jsonResult.setRows(serviceResult.getResult());
        return jsonResult;
    }

    private ProductTypeAttr[] processAttr(String str, Integer createId) {
        if (StringUtil.isEmpty(str)) {
            return null;
        }
        JSONArray jsonArray = JSONArray.parseArray(str);
        if (null != jsonArray && jsonArray.size() > 0) {
            ProductTypeAttr attrs[] = new ProductTypeAttr[jsonArray.size()];
            for (int i = 0; i < jsonArray.size(); i++) {
                ProductTypeAttr attr = new ProductTypeAttr();
                Map<String, String> map = (Map<String, String>) jsonArray.get(i);
                attr.setName(map.get("name"));
                attr.setType(Integer.valueOf(map.get("type")));
                String val = map.get("val");
                if (!StringUtil.isEmpty(val)) {
                    attr.setValue(val);
                }
                String productTypeAttrId = map.get("id");
                attr.setId(ConvertUtil.toInt(productTypeAttrId, 0));
                attr.setCreateId(createId);
                attrs[i] = attr;
            }
            return attrs;
        }
        return null;
    }
}
