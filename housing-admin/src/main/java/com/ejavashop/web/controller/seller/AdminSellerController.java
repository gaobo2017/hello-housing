package com.ejavashop.web.controller.seller;

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
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.service.seller.ISellerService;
import com.ejavashop.web.controller.BaseController;

/**
 * 后台商家管理controller
 * 
 * @Filename: AdminSellerController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/seller/manage")
public class AdminSellerController extends BaseController {

    @Resource(name = "sellerService")
    private ISellerService sellerService;

    /**
     * 默认页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/seller/manage/sellerlist";
    }

    /**
     * 商家列表
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Seller>> list(HttpServletRequest request,
                                                           Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<Seller>> serviceResult = sellerService.getSellers(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<Seller>> jsonResult = new HttpJsonResult<List<Seller>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    //    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    //    public String edit(HttpServletRequest request, Integer id, Map<String, Object> dataMap) {
    //        ServiceResult<Seller> serviceResult = sellerService.getSellerById(id);
    //        if (!serviceResult.getSuccess()) {
    //            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
    //                throw new RuntimeException(serviceResult.getMessage());
    //            } else {
    //                throw new BusinessException(serviceResult.getMessage());
    //            }
    //        }
    //        Seller seller = serviceResult.getResult();
    //
    //        dataMap.put("seller", seller);
    //        return "admin/seller/manage/selleredit";
    //    }
    //
    //    @RequestMapping(value = "update", method = { RequestMethod.POST })
    //    public void update(HttpServletRequest request, HttpServletResponse response, Seller seller,
    //                       Map<String, Object> dataMap) {
    //        String msg = "修改成功";
    //        PrintWriter pw = null;
    //        response.setContentType("text/plain;charset=utf-8");
    //        try {
    //            if (seller == null || seller.getId() == null) {
    //                throw new BusinessException("数据错误");
    //            }
    //            ServiceResult<Integer> result = sellerService.updateSeller(seller);
    //            if (!result.getSuccess()) {
    //                msg = result.getMessage();
    //            }
    //            pw = response.getWriter();
    //        } catch (Exception e) {
    //            msg = e.getMessage();
    //        }
    //        pw.print(msg);
    //    }

    /**
     * 冻结商家
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "freeze", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> freeze(Integer awardId, HttpServletRequest request,
                                                        HttpServletResponse response,
                                                        Integer sellerId) {
        HttpJsonResult<Boolean> jsonResult = null;
        ServiceResult<Seller> serviceResult = sellerService.getSellerById(sellerId);
        if (!serviceResult.getSuccess()) {
            jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            return jsonResult;
        }
        if (serviceResult.getResult() == null) {
            jsonResult = new HttpJsonResult<Boolean>("该商家不存在！");
            return jsonResult;
        }
        Seller seller = serviceResult.getResult();

        if (seller.getAuditStatus() == Seller.AUDIT_STATE_3_FREEZE) {
            jsonResult = new HttpJsonResult<Boolean>("该商家已经被冻结！");
            return jsonResult;
        }

        // 执行冻结操作
        ServiceResult<Boolean> freezeResult = sellerService.freezeSeller(sellerId);
        if (!freezeResult.getSuccess() || !freezeResult.getResult()) {
            jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            return jsonResult;
        } else {
            jsonResult = new HttpJsonResult<Boolean>();
        }

        return jsonResult;
    }

    /**
     * 解冻商家
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "unfreeze", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> unFreeze(Integer awardId,
                                                          HttpServletRequest request,
                                                          HttpServletResponse response,
                                                          Integer sellerId) {
        HttpJsonResult<Boolean> jsonResult = null;
        ServiceResult<Seller> serviceResult = sellerService.getSellerById(sellerId);
        if (!serviceResult.getSuccess()) {
            jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            return jsonResult;
        }
        if (serviceResult.getResult() == null) {
            jsonResult = new HttpJsonResult<Boolean>("该商家不存在！");
            return jsonResult;
        }
        Seller seller = serviceResult.getResult();

        if (seller.getAuditStatus() != Seller.AUDIT_STATE_3_FREEZE) {
            jsonResult = new HttpJsonResult<Boolean>("该商家没有被冻结，无需解冻！");
            return jsonResult;
        }

        ServiceResult<Boolean> freezeResult = sellerService.unFreezeSeller(sellerId);
        if (!freezeResult.getSuccess() || !freezeResult.getResult()) {
            jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            return jsonResult;
        } else {
            jsonResult = new HttpJsonResult<Boolean>();
        }

        return jsonResult;
    }
}
