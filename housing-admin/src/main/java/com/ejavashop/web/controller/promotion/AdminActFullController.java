package com.ejavashop.web.controller.promotion;

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
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.promotion.full.ActFull;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.promotion.IActFullService;
import com.ejavashop.service.seller.ISellerService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 满减活动管理controller
 *                       
 * @Filename: AdminActFullController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/promotion/full")
public class AdminActFullController extends BaseController {

    @Resource
    private IActFullService actFullService;
    @Resource
    private ISellerService  sellerService;

    /**
     * 订单满减列表页
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) {
        ServiceResult<List<Seller>> sellers = sellerService
            .getSellers(new HashMap<String, String>(), null);
        dataMap.put("sellers", sellers.getResult());
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/promotion/full/actfulllist";
    }

    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ActFull>> list(HttpServletRequest request,
                                                            HttpServletResponse response,
                                                            Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<ActFull>> serviceResult = actFullService.getActFulls(queryMap, pager);
        pager = serviceResult.getPager();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<ActFull> list = serviceResult.getResult();

        HttpJsonResult<List<ActFull>> jsonResult = new HttpJsonResult<List<ActFull>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "audit", method = { RequestMethod.GET })
    public String audit(HttpServletRequest request, int actFullId, Map<String, Object> dataMap) {

        ServiceResult<ActFull> serviceResult = actFullService.getActFullById(actFullId);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/full/actfulllist";
            }
        }
        ActFull actFull = serviceResult.getResult();

        dataMap.put("actFull", actFull);
        return "admin/promotion/full/actfullaudit";
    }

    @RequestMapping(value = "doaudit", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> doAudit(HttpServletRequest request,
                                                         @RequestParam("id") Integer id,
                                                         @RequestParam("status") Integer status) {

        ServiceResult<ActFull> serviceResult = actFullService.getActFullById(id);

        if (!serviceResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(serviceResult.getMessage());
        }
        ActFull actFullDb = serviceResult.getResult();
        if (actFullDb == null) {
            return new HttpJsonResult<Boolean>("活动信息获取失败。");
        }

        if (actFullDb.getStatus().intValue() != ActFull.STATUS_2) {
            return new HttpJsonResult<Boolean>("非提交审核状态的活动不能执行审核操作。");
        }

        String auditOpinion = request.getParameter("auditOpinion");
        if (status.intValue() == ActFull.STATUS_4 && StringUtil.isEmpty(auditOpinion, true)) {
            return new HttpJsonResult<Boolean>("请填写审核失败原因。");
        }

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);

        ActFull actFull = new ActFull();
        actFull.setId(id);
        actFull.setStatus(status);
        actFull.setAuditOpinion(auditOpinion);
        actFull.setAuditUserId(adminUser.getId());
        actFull.setAuditUserName(adminUser.getName());
        actFull.setAuditTime(new Date());
        actFull.setSellerId(actFullDb.getSellerId());
        ServiceResult<Boolean> updateResult = actFullService.updateActFullStatus(actFull);
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!updateResult.getSuccess()) {
            jsonResult.setMessage(updateResult.getMessage());
        }
        return jsonResult;
    }

}
