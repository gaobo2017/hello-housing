package com.ejavashop.web.controller.product;

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

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.entity.product.ProductNorm;
import com.ejavashop.entity.product.ProductNormAttr;
import com.ejavashop.entity.product.ProductType;
import com.ejavashop.entity.system.Code;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.product.IProductNormService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 商品规格
 */
@Controller
@RequestMapping(value = "admin/product/norm")
public class NormController extends BaseController {
    private String              baseUrl = "admin/product/norm/";

    @Resource
    private IProductNormService productNormService;

    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("q_state", "1");
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return baseUrl + "normlist";
    }

    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ProductNorm>> list(HttpServletRequest request,
                                                                Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<ProductNorm>> serviceResult = productNormService.pageNorm(queryMap,
            pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<ProductNorm>> jsonResult = new HttpJsonResult<List<ProductNorm>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "list_no_page", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ProductNorm>> listNoPage(HttpServletRequest request,
                                                                      Map<String, Object> dataMap) {
        ServiceResult<List<ProductNorm>> serviceResult = productNormService.listNoPage();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<ProductNorm>> jsonResult = new HttpJsonResult<List<ProductNorm>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(serviceResult.getResult().size());
        return jsonResult;
    }

    /**
     * 商品类型管理，添加或者编辑商品类型的时候，选择的商品规格状态为正常的
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list1", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ProductNorm>> list1(HttpServletRequest request,
                                                                 Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        queryMap.put("q_state", String.valueOf(ProductNorm.Status.DEFAULT.getValue()));
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<ProductNorm>> serviceResult = productNormService.pageNorm(queryMap,
            pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<ProductNorm>> jsonResult = new HttpJsonResult<List<ProductNorm>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(Map<String, Object> dataMap) {
        Code code = new Code();
        //        code.setUseYn(ConstantsJM.USE_YN_Y);
        dataMap.put("code", code);
        return baseUrl + "normadd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult<Object> create(ProductNorm norm, HttpServletRequest request,
                                         Map<String, Object> dataMap) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        if (null == user) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getEJS_URL_RESOURCES() + "/admin/login");
            return jsonResult;
        }
        //norm
        norm.setCreateId(user.getId());
        norm.setUpdateId(user.getId());
        norm.setState(ProductNorm.Status.DEFAULT.getValue());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("norm", norm);
        ProductNormAttr[] attrs = processAttrForAdd(request.getParameter("attrVal"), user.getId());
        if (null != attrs) {
            map.put("attr", attrs);
        }

        //save
        ServiceResult<Boolean> serviceResult = productNormService.saveNorm(map);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    private ProductNormAttr[] processAttrForAdd(String str, Integer userId) {
        if (!StringUtil.isEmpty(str) && null != userId) {
            String attrs[] = str.split(";");
            ProductNormAttr productNormAttrs[] = new ProductNormAttr[attrs.length];
            for (int i = 0; i < attrs.length; i++) {
                ProductNormAttr productNormAttr = new ProductNormAttr();
                String attr[] = attrs[i].split(",");
                productNormAttr.setName(attr[0]);
                productNormAttr.setSort(Integer.valueOf(attr[1]));
                productNormAttr.setCreateId(userId);
                productNormAttrs[i] = productNormAttr;
            }
            return productNormAttrs;
        }
        return null;
    }

    private ProductNormAttr[] processAttr(String str, Integer userId) {
        if (!StringUtil.isEmpty(str) && null != userId) {
            String attrs[] = str.split(";");
            ProductNormAttr productNormAttrs[] = new ProductNormAttr[attrs.length];
            for (int i = 0; i < attrs.length; i++) {
                ProductNormAttr productNormAttr = new ProductNormAttr();
                String attr[] = attrs[i].split(",");
                Integer attrId = ConvertUtil.toInt(attr[0], -1);
                if (-1 != attrId) {
                    productNormAttr.setId(attrId);
                }
                productNormAttr.setName(attr[1]);
                productNormAttr.setSort(Integer.valueOf(attr[2]));
                productNormAttr.setCreateId(userId);
                productNormAttrs[i] = productNormAttr;
            }
            return productNormAttrs;
        }
        return null;
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(Integer id, Map<String, Object> dataMap) {
        ServiceResult<ProductNorm> serviceResult = productNormService.getNormById(id);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            }
        }
        dataMap.put("norm", serviceResult.getResult());

        ServiceResult<List<ProductNormAttr>> attr = productNormService.getAttrByNormId(id);
        if (!attr.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            }
        }
        dataMap.put("attr", attr.getResult());

        //判断是否可以添加商品规格值
        ServiceResult<List<ProductType>> serviceResult1 = productNormService.chkHasUsed(id);
        if (serviceResult1.getSuccess() && serviceResult1.getResult() != null
            && serviceResult1.getResult().size() > 0) {
            dataMap.put("tmp", "tmp");
        }
        return baseUrl + "normedit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult<Object> update(ProductNorm norm, HttpServletRequest request) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        if (null == user) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getEJS_URL_RESOURCES() + "/admin/login");
            return jsonResult;
        }
        norm.setUpdateId(user.getId());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("norm", norm);
        map.put("attr", processAttr(request.getParameter("attrVal"), user.getId()));

        ServiceResult<Boolean> serviceResult = productNormService.updateNorm(map);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "del", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> del(HttpServletRequest request,
                                                    @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        ServiceResult<Boolean> serviceResult = productNormService.delNorm(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "getById", method = RequestMethod.GET)
    public @ResponseBody HttpJsonResult<Object> getById(HttpServletRequest request,
                                                        @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        ServiceResult<ProductNorm> serviceResult = productNormService.getNormById(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        jsonResult.setRows(serviceResult.getResult());
        return jsonResult;
    }

    /**
     * 商家商品类型编辑页面初始化ajax调用
     * @param request
     * @param ids
     * @return
     */
    @RequestMapping(value = "getByIds", method = RequestMethod.GET)
    public @ResponseBody HttpJsonResult<Object> getByIds(HttpServletRequest request,
                                                         @RequestParam("ids") String ids) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        ServiceResult<List<ProductNorm>> serviceResult = productNormService.getNormByIds(ids);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        String str = "";
        for (ProductNorm norm : serviceResult.getResult()) {
            str += norm.getName() + ",";
        }
        if (str.length() > 0)
            str = str.substring(0, str.lastIndexOf(","));
        jsonResult.setRows(str);
        return jsonResult;
    }
}
