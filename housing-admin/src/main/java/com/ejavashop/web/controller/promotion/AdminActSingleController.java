package com.ejavashop.web.controller.promotion;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.promotion.single.ActSingle;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.product.IProductService;
import com.ejavashop.service.promotion.IActSingleService;
import com.ejavashop.service.seller.ISellerService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 单品立减管理controller
 *                       
 * @Filename: AdminActSingleController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/promotion/single")
public class AdminActSingleController extends BaseController {

    @Resource
    private IActSingleService actSingleService;
    @Resource
    private IProductService   productService;
    @Resource
    private ISellerService    sellerService;

    /**
     * 单品立减列表页
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) {
        ServiceResult<List<Seller>> sellers = sellerService
            .getSellers(new HashMap<String, String>(), null);
        dataMap.put("sellers", sellers.getResult());
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/promotion/single/actsinglelist";
    }

    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ActSingle>> list(HttpServletRequest request,
                                                              HttpServletResponse response,
                                                              Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<ActSingle>> serviceResult = actSingleService.getActSingles(queryMap,
            pager);
        pager = serviceResult.getPager();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<ActSingle> list = serviceResult.getResult();

        HttpJsonResult<List<ActSingle>> jsonResult = new HttpJsonResult<List<ActSingle>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "detail", method = { RequestMethod.GET })
    public String detail(HttpServletRequest request, int actSingleId, Map<String, Object> dataMap) {

        ServiceResult<ActSingle> serviceResult = actSingleService.getActSingleById(actSingleId);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/single/actsinglelist";
            }
        }
        ActSingle actSingle = serviceResult.getResult();

        String ids = actSingle.getProductIds().substring(1, actSingle.getProductIds().length() - 1);
        ServiceResult<List<Product>> productsByIds = productService.getProductsByIds(getIds(ids));

        dataMap.put("actSingle", actSingle);
        dataMap.put("productList", productsByIds.getResult());
        return "admin/promotion/single/actsingledetail";
    }

    @RequestMapping(value = "audit", method = { RequestMethod.GET })
    public String audit(HttpServletRequest request, int actSingleId, Map<String, Object> dataMap) {

        ServiceResult<ActSingle> serviceResult = actSingleService.getActSingleById(actSingleId);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/single/actsinglelist";
            }
        }
        ActSingle actSingle = serviceResult.getResult();

        String ids = actSingle.getProductIds().substring(1, actSingle.getProductIds().length() - 1);
        ServiceResult<List<Product>> productsByIds = productService.getProductsByIds(getIds(ids));

        dataMap.put("actSingle", actSingle);
        dataMap.put("productList", productsByIds.getResult());
        return "admin/promotion/single/actsingleaudit";
    }

    private List<Integer> getIds(String ids) {
        List<Integer> list = new ArrayList<Integer>();
        if (ids != null) {
            String[] split = ids.split(",");
            for (String id : split) {
                if (!StringUtil.isEmpty(id, true)) {
                    list.add(ConvertUtil.toInt(id, 0));
                }
            }
        }
        return list;
    }

    @RequestMapping(value = "doaudit", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> doAudit(HttpServletRequest request,
                                                         @RequestParam("id") Integer id,
                                                         @RequestParam("status") Integer status) {

        ServiceResult<ActSingle> serviceResult = actSingleService.getActSingleById(id);

        if (!serviceResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(serviceResult.getMessage());
        }
        ActSingle actSingleDb = serviceResult.getResult();
        if (actSingleDb == null) {
            return new HttpJsonResult<Boolean>("活动信息获取失败。");
        }

        if (actSingleDb.getStatus().intValue() != ActSingle.STATUS_2) {
            return new HttpJsonResult<Boolean>("非提交审核状态的活动不能执行审核操作。");
        }

        String auditOpinion = request.getParameter("auditOpinion");
        if (status.intValue() == ActSingle.STATUS_4 && StringUtil.isEmpty(auditOpinion, true)) {
            return new HttpJsonResult<Boolean>("请填写审核失败原因。");
        }

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);

        ActSingle actSingle = new ActSingle();
        actSingle.setId(id);
        actSingle.setStatus(status);
        actSingle.setAuditOpinion(auditOpinion);
        actSingle.setAuditUserId(adminUser.getId());
        actSingle.setAuditUserName(adminUser.getName());
        actSingle.setAuditTime(new Date());
        actSingle.setSellerId(actSingleDb.getSellerId());
        ServiceResult<Boolean> updateResult = actSingleService.updateActSingleStatus(actSingle);
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!updateResult.getSuccess()) {
            jsonResult.setMessage(updateResult.getMessage());
        }
        return jsonResult;
    }

    //    @RequestMapping(value = "doaudit", method = { RequestMethod.POST })
    //    public String doAudit(HttpServletRequest request, Map<String, Object> dataMap,
    //                          @RequestParam("id") Integer id, @RequestParam("status") Integer status) {
    //
    //        ServiceResult<ActSingle> serviceResult = actSingleService.getActSingleById(id);
    //
    //        if (!serviceResult.getSuccess()) {
    //            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
    //                throw new RuntimeException(serviceResult.getMessage());
    //            } else {
    //                dataMap.put("message", serviceResult.getMessage());
    //                return "admin/promotion/single/actsinglelist";
    //            }
    //        }
    //        ActSingle actSingleDb = serviceResult.getResult();
    //
    //        String ids = actSingleDb.getProductIds().substring(1,
    //            actSingleDb.getProductIds().length() - 1);
    //        ServiceResult<List<Product>> productsByIds = productService.getProductsByIds(getIds(ids));
    //
    //        dataMap.put("actSingle", actSingleDb);
    //        dataMap.put("productList", productsByIds.getResult());
    //
    //        if (actSingleDb.getStatus().intValue() != ActSingle.STATUS_2) {
    //            dataMap.put("message", "非提交审核状态的活动不能执行审核操作。");
    //            return "admin/promotion/single/actsingleaudit";
    //        }
    //
    //        String auditOpinion = request.getParameter("auditOpinion");
    //        if (status.intValue() == ActSingle.STATUS_4 && StringUtil.isEmpty(auditOpinion, true)) {
    //            dataMap.put("message", "请填写审核失败原因。");
    //            return "admin/promotion/single/actsingleaudit";
    //        }
    //
    //        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
    //
    //        ActSingle actSingle = new ActSingle();
    //        actSingle.setId(id);
    //        actSingle.setStatus(status);
    //        actSingle.setAuditOpinion(auditOpinion);
    //        actSingle.setAuditUserId(adminUser.getId());
    //        actSingle.setAuditUserName(adminUser.getName());
    //        actSingle.setAuditTime(new Date());
    //        actSingle.setSellerId(actSingleDb.getSellerId());
    //        ServiceResult<Boolean> updateResult = actSingleService.updateActSingleStatus(actSingle);
    //        if (!updateResult.getSuccess()) {
    //            dataMap.put("message", updateResult.getMessage());
    //            return "admin/promotion/single/actsingleaudit";
    //        }
    //
    //        return "redirect://promotion/single";
    //    }

}
