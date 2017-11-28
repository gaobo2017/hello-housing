package com.ejavashop.web.controller.member;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.ejavashop.entity.product.ProductAsk;
import com.ejavashop.service.product.IProductAskService;
import com.ejavashop.web.controller.BaseController;

/**
 * 产品咨询管理controller
 * 
 * @Filename: AdminProdcutAskController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/member/productask")
public class AdminProdcutAskController extends BaseController {

    @Resource
    private IProductAskService productAskService;

    /**
     * 产品咨询管理页面初始化controller接口
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/member/member/productasklist";
    }

    /**
     * 产品咨询管理页面查询按钮controller接口
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ProductAsk>> list(HttpServletRequest request,
                                                               HttpServletResponse response,
                                                               Map<String, Object> dataMap) {

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<ProductAsk>> serviceResult = productAskService.getProductAsksWithInfo(queryMap,
            pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<ProductAsk>> jsonResult = new HttpJsonResult<List<ProductAsk>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    /**
     * 审核商品咨询-通过
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "pass", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> pass(HttpServletRequest request,
                                                      HttpServletResponse response,
                                                      Integer productAskId) {
        ProductAsk productAsk = new ProductAsk();
        productAsk.setId(productAskId);
        productAsk.setState(ProductAsk.STATE_3);
        ServiceResult<Boolean> serviceResult = productAskService.updateProductAsk(productAsk);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        jsonResult.setData(serviceResult.getResult());
        return jsonResult;
    }

    /**
     * 审核商品咨询-不通过
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "reject", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> reject(HttpServletRequest request,
                                                        HttpServletResponse response,
                                                        Integer productAskId) {
        ProductAsk productAsk = new ProductAsk();
        productAsk.setId(productAskId);
        productAsk.setState(ProductAsk.STATE_4);
        ServiceResult<Boolean> serviceResult = productAskService.updateProductAsk(productAsk);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        jsonResult.setData(serviceResult.getResult());
        return jsonResult;
    }
}
