package com.ejavashop.web.controller.order;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.seller.SellerComplaint;
import com.ejavashop.service.order.IAdminComplaintService;
import com.ejavashop.vo.seller.SellerComplaintVO;
import com.ejavashop.web.controller.BaseController;

/**
 * 投诉管理相关action
 *                       
 *
 */
@Controller
@RequestMapping(value = "admin/order/complaint")
public class AdminComplaintController extends BaseController {
    @Resource(name = "adminComplaintServiceImpl")
    IAdminComplaintService sellerComplaintService;

    Logger                 log = Logger.getLogger(this.getClass());

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
        return "admin/order/complaint/list";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<SellerComplaintVO>> list(HttpServletRequest request,
                                                                      Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        //        queryMap.put("q_state", String.valueOf(ConstantsEJS.SELLER_COMPLAINT_1));//1、买家投诉待审核
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<SellerComplaintVO>> serviceResult = sellerComplaintService.page(queryMap,
            pager);
        List<SellerComplaintVO> list = serviceResult.getResult();

        //        queryMap.put("q_state", String.valueOf(ConstantsEJS.SELLER_COMPLAINT_4));//4、卖家申诉待审核
        //        serviceResult = sellerComplaintService.page(queryMap,
        //                pager);
        //        list.addAll(serviceResult.getResult());

        HttpJsonResult<List<SellerComplaintVO>> jsonResult = new HttpJsonResult<List<SellerComplaintVO>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    /**
     * 审核页面
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "auditPage", method = { RequestMethod.GET })
    public String auditPage(HttpServletRequest request, Map<String, Object> dataMap, String id) {
        ServiceResult<SellerComplaintVO> serviceResult = sellerComplaintService
            .getById(Integer.valueOf(id));
        dataMap.put("obj", serviceResult.getResult());
        return "admin/order/complaint/audit";
    }

    /**
     * 投诉仲裁
     * @param request
     * @param dataMap
     * @param id
     * @param optContent
     * @return
     */
    @RequestMapping(value = "doAudit", method = { RequestMethod.POST })
    public String doAudit(HttpServletRequest request, Map<String, Object> dataMap, String id,
                          String optContent, String stateType) {
        ServiceResult<SellerComplaint> serviceResult = sellerComplaintService
            .getSellerComplaintById(Integer.valueOf(id));
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        Assert.notNull(stateType);
        SellerComplaint sc = serviceResult.getResult();

        if (stateType.equals("agree")) {
            //管理员通过,判断是否是商家申拆
            if (sc.getSellerComplaintTime() != null
                && sc.getState() == ConstantsEJS.SELLER_COMPLAINT_4)
                //商家申诉通过
                sc.setState(ConstantsEJS.SELLER_COMPLAINT_6);
            else
                //买家投诉通过
                sc.setState(ConstantsEJS.SELLER_COMPLAINT_3);
        } else {
            //判断是否是商家申拆
            if (sc.getSellerComplaintTime() != null
                && sc.getState() == ConstantsEJS.SELLER_COMPLAINT_4)
                sc.setState(ConstantsEJS.SELLER_COMPLAINT_5);
            else
                sc.setState(ConstantsEJS.SELLER_COMPLAINT_2);
        }
        sc.setOptContent(optContent);
        ServiceResult<Integer> serviceResult1 = sellerComplaintService.updateSellerComplaint(sc);
        if (!serviceResult1.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult1.getCode())) {
                throw new RuntimeException(serviceResult1.getMessage());
            } else {
                throw new BusinessException(serviceResult1.getMessage());
            }
        }
        return "redirect:/admin/order/complaint";
    }
}
