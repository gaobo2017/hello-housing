package com.ejavashop.web.controller.promotion;

import java.util.ArrayList;
import java.util.Date;
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
import com.ejavashop.entity.promotion.flash.ActFlashSale;
import com.ejavashop.entity.promotion.flash.ActFlashSaleProduct;
import com.ejavashop.entity.promotion.flash.ActFlashSaleStage;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.promotion.IActFlashSaleProductService;
import com.ejavashop.service.promotion.IActFlashSaleService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.UploadUtil;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 限时抢购活动管理controller
 *                       
 * @Filename: AdminActFlashSaleController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/promotion/flash")
public class AdminActFlashSaleController extends BaseController {

    @Resource
    private IActFlashSaleService        actFlashSaleService;
    @Resource
    private IActFlashSaleProductService actFlashSaleProductService;

    /**
     * 限时抢购活动列表页
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/promotion/flash/actflashsalelist";
    }

    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ActFlashSale>> list(HttpServletRequest request,
                                                                 HttpServletResponse response,
                                                                 Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<ActFlashSale>> serviceResult = actFlashSaleService.getActFlashSales(
            queryMap, pager);
        pager = serviceResult.getPager();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<ActFlashSale> list = serviceResult.getResult();

        HttpJsonResult<List<ActFlashSale>> jsonResult = new HttpJsonResult<List<ActFlashSale>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request, Map<String, Object> dataMap) {
        return "admin/promotion/flash/actflashsaleadd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(ActFlashSale actFlashSale, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        Integer userId = adminUser.getId();
        actFlashSale.setCreateUserId(userId);
        actFlashSale.setCreateUserName(adminUser.getName());
        actFlashSale.setUpdateUserId(adminUser.getId());
        actFlashSale.setUpdateUserName(adminUser.getName());
        actFlashSale.setStatus(ActFlashSale.STATUS_1);

        // 上传图片
        String pcImageFile = UploadUtil.getInstance().advUploadFile2ImageServer("pcImageFile",
            request);
        if (pcImageFile != null && !"".equals(pcImageFile)) {
            actFlashSale.setPcImage(pcImageFile);
        }
        String mobileImageFile = UploadUtil.getInstance().advUploadFile2ImageServer(
            "mobileImageFile", request);
        if (mobileImageFile != null && !"".equals(mobileImageFile)) {
            actFlashSale.setMobileImage(mobileImageFile);
        }

        String startTimeStr = request.getParameter("startTime");
        String endTimeStr = request.getParameter("endTime");
        String[] timeintervals = request.getParameterValues("timeinterval");

        List<String> timeintervalList = new ArrayList<String>();
        timeintervalList.add(startTimeStr);
        if (timeintervals != null && timeintervals.length > 0) {
            for (String timeinterval : timeintervals) {
                timeintervalList.add(timeinterval);
            }
        }
        timeintervalList.add(endTimeStr);

        List<ActFlashSaleStage> stageList = new ArrayList<ActFlashSaleStage>();
        ActFlashSaleStage actFlashSaleStage = null;
        for (int i = 0; i < timeintervalList.size() - 1; i++) {
            int timeinterval1 = ConvertUtil.toInt(timeintervalList.get(i), 0);
            int timeinterval2 = ConvertUtil.toInt(timeintervalList.get(i + 1), 0);

            actFlashSaleStage = new ActFlashSaleStage();
            actFlashSaleStage.setStartTime(timeinterval1);
            actFlashSaleStage.setEndTime(timeinterval2);
            stageList.add(actFlashSaleStage);
        }

        actFlashSale.setStageList(stageList);

        ServiceResult<Boolean> serviceResult = actFlashSaleService.saveActFlashSale(actFlashSale);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("actFlashSale", actFlashSale);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/flash/actflashsaleadd";
            }
        }
        return "redirect:/admin/promotion/flash";
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(int actFlashSaleId, Map<String, Object> dataMap) {
        ServiceResult<ActFlashSale> serviceResult = actFlashSaleService
            .getActFlashSaleById(actFlashSaleId);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/flash/actflashsalelist";
            }
        }
        ActFlashSale actFlashSale = serviceResult.getResult();
        List<ActFlashSaleStage> actFlashSaleStages = actFlashSale.getStageList();
        if (actFlashSaleStages != null && actFlashSaleStages.size() > 0) {
            dataMap.put("startTime", actFlashSaleStages.get(0).getStartTime());
            dataMap.put("endTime", actFlashSaleStages.get(actFlashSaleStages.size() - 1)
                .getEndTime());
            actFlashSaleStages.remove(0);
        }

        dataMap.put("actFlashSale", actFlashSale);
        dataMap.put("stageList", actFlashSaleStages);
        return "admin/promotion/flash/actflashsaleedit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(ActFlashSale actFlashSale, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        actFlashSale.setUpdateUserId(adminUser.getId());
        actFlashSale.setUpdateUserName(adminUser.getName());

        // 上传图片
        String pcImageFile = UploadUtil.getInstance().advUploadFile2ImageServer("pcImageFile",
            request);
        if (pcImageFile != null && !"".equals(pcImageFile)) {
            actFlashSale.setPcImage(pcImageFile);
        }
        String mobileImageFile = UploadUtil.getInstance().advUploadFile2ImageServer(
            "mobileImageFile", request);
        if (mobileImageFile != null && !"".equals(mobileImageFile)) {
            actFlashSale.setMobileImage(mobileImageFile);
        }

        String startTimeStr = request.getParameter("startTime");
        String endTimeStr = request.getParameter("endTime");
        String[] timeintervals = request.getParameterValues("timeinterval");

        List<String> timeintervalList = new ArrayList<String>();
        timeintervalList.add(startTimeStr);
        if (timeintervals != null && timeintervals.length > 0) {
            for (String timeinterval : timeintervals) {
                timeintervalList.add(timeinterval);
            }
        }
        timeintervalList.add(endTimeStr);

        List<ActFlashSaleStage> stageList = new ArrayList<ActFlashSaleStage>();
        ActFlashSaleStage actFlashSaleStage = null;
        for (int i = 0; i < timeintervalList.size() - 1; i++) {
            int timeinterval1 = ConvertUtil.toInt(timeintervalList.get(i), 0);
            int timeinterval2 = ConvertUtil.toInt(timeintervalList.get(i + 1), 0);

            actFlashSaleStage = new ActFlashSaleStage();
            actFlashSaleStage.setStartTime(timeinterval1);
            actFlashSaleStage.setEndTime(timeinterval2);
            stageList.add(actFlashSaleStage);
        }

        actFlashSale.setStageList(stageList);

        ServiceResult<Boolean> serviceResult = actFlashSaleService.updateActFlashSale(actFlashSale);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("actFlashSale", actFlashSale);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/flash/actflashsaleedit";
            }
        }
        return "redirect:/admin/promotion/flash";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> delete(HttpServletRequest request,
                                                        @RequestParam("id") Integer id) {
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        ServiceResult<Boolean> serviceResult = actFlashSaleService.deleteActFlashSale(id,
            adminUser.getId(), adminUser.getName());
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "recruitstart", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> recruitStart(HttpServletRequest request,
                                                             @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();

        ServiceResult<ActFlashSale> getResult = actFlashSaleService.getActFlashSaleById(id);

        if (!getResult.getSuccess()) {
            jsonResult.setMessage(getResult.getMessage());
            return jsonResult;
        }
        ActFlashSale actFlashSaleDb = getResult.getResult();
        if (actFlashSaleDb == null) {
            jsonResult.setMessage("活动信息获取失败");
            return jsonResult;
        }
        if (!actFlashSaleDb.getStatus().equals(ActFlashSale.STATUS_1)) {
            jsonResult.setMessage("非新建状态的活动不能发起商品征集");
            return jsonResult;
        }

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        ActFlashSale actFlashSale = new ActFlashSale();
        actFlashSale.setId(id);
        actFlashSale.setStatus(ActFlashSale.STATUS_2);
        actFlashSale.setUpdateUserId(adminUser.getId());
        actFlashSale.setUpdateUserName(adminUser.getName());
        actFlashSale.setUpdateTime(new Date());
        ServiceResult<Boolean> serviceResult = actFlashSaleService
            .updateActFlashSaleStatus(actFlashSale);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "recruitend", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> recruitEnd(HttpServletRequest request,
                                                           @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();

        ServiceResult<ActFlashSale> getResult = actFlashSaleService.getActFlashSaleById(id);

        if (!getResult.getSuccess()) {
            jsonResult.setMessage(getResult.getMessage());
            return jsonResult;
        }
        ActFlashSale actFlashSaleDb = getResult.getResult();
        if (actFlashSaleDb == null) {
            jsonResult.setMessage("活动信息获取失败");
            return jsonResult;
        }
        if (!actFlashSaleDb.getStatus().equals(ActFlashSale.STATUS_2)) {
            jsonResult.setMessage("只能结束正在征集商品的活动");
            return jsonResult;
        }

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        ActFlashSale actFlashSale = new ActFlashSale();
        actFlashSale.setId(id);
        actFlashSale.setStatus(ActFlashSale.STATUS_3);
        actFlashSale.setUpdateUserId(adminUser.getId());
        actFlashSale.setUpdateUserName(adminUser.getName());
        actFlashSale.setUpdateTime(new Date());
        ServiceResult<Boolean> serviceResult = actFlashSaleService
            .updateActFlashSaleStatus(actFlashSale);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /**
     * 详情
     * @param actFlashSaleId
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "detail", method = { RequestMethod.GET })
    public String detail(int actFlashSaleId, HttpServletRequest request, Map<String, Object> dataMap) {
        ServiceResult<ActFlashSale> serviceResult = actFlashSaleService
            .getActFlashSaleByIdAndSellerId(actFlashSaleId, null);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/flash/actflashsalelist";
            }
        }
        ActFlashSale actFlashSale = serviceResult.getResult();

        dataMap.put("actFlashSale", actFlashSale);
        dataMap.put("stageList", actFlashSale.getStageList());
        return "admin/promotion/flash/actflashsaledetail";
    }

    /**
     * 作废
     * @param actFlashSaleId
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "abolish", method = { RequestMethod.GET })
    public String abolish(int actFlashSaleId, HttpServletRequest request,
                          Map<String, Object> dataMap) {
        ServiceResult<ActFlashSale> serviceResult = actFlashSaleService
            .getActFlashSaleByIdAndSellerId(actFlashSaleId, null);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/flash/actflashsalelist";
            }
        }
        ActFlashSale actFlashSale = serviceResult.getResult();

        dataMap.put("actFlashSale", actFlashSale);
        dataMap.put("stageList", actFlashSale.getStageList());
        return "admin/promotion/flash/actflashsaleabolish";
    }

    @RequestMapping(value = "doabolish", method = { RequestMethod.POST })
    public String doAbolish(ActFlashSale actFlashSale, HttpServletRequest request,
                            Map<String, Object> dataMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);

        ServiceResult<ActFlashSale> getResult = actFlashSaleService
            .getActFlashSaleById(actFlashSale.getId());

        if (!getResult.getSuccess()) {
            dataMap.put("actFlashSale", actFlashSale);
            dataMap.put("message", getResult.getMessage());
            return "admin/promotion/flash/actflashsaleabolish";
        }
        ActFlashSale actFlashSaleDb = getResult.getResult();
        if (actFlashSaleDb == null) {
            dataMap.put("actFlashSale", actFlashSale);
            dataMap.put("message", "活动信息获取失败");
            return "admin/promotion/flash/actflashsaleabolish";
        }
        if (!actFlashSaleDb.getStatus().equals(ActFlashSale.STATUS_3)) {
            dataMap.put("actFlashSale", actFlashSale);
            dataMap.put("message", "只能作废征集结束的活动");
            return "admin/promotion/flash/actflashsaleabolish";
        }

        ActFlashSale actFlashSaleNew = new ActFlashSale();
        actFlashSaleNew.setId(actFlashSale.getId());
        actFlashSaleNew.setStatus(ActFlashSale.STATUS_4);
        actFlashSaleNew.setAuditOpinion(actFlashSale.getAuditOpinion());
        actFlashSaleNew.setUpdateUserId(adminUser.getId());
        actFlashSaleNew.setUpdateUserName(adminUser.getName());
        actFlashSaleNew.setUpdateTime(new Date());

        ServiceResult<Boolean> serviceResult = actFlashSaleService
            .updateActFlashSaleStatus(actFlashSaleNew);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("actFlashSale", actFlashSale);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/flash/actflashsaleabolish";
            }
        }
        return "redirect:/admin/promotion/flash";
    }

    /**
     * 审核商品
     * @param actFlashSaleId
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "audit", method = { RequestMethod.GET })
    public String audit(int actFlashSaleId, HttpServletRequest request, Map<String, Object> dataMap) {
        ServiceResult<ActFlashSale> serviceResult = actFlashSaleService
            .getActFlashSaleByIdAndSellerId(actFlashSaleId, null);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/flash/actflashsalelist";
            }
        }
        ActFlashSale actFlashSale = serviceResult.getResult();

        dataMap.put("actFlashSale", actFlashSale);
        dataMap.put("stageList", actFlashSale.getStageList());

        return "admin/promotion/flash/actflashsaleauditWin";
    }

    /**
     * 审核商品
     * @param actFlashSaleId
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "toProductSort", method = { RequestMethod.GET })
    public String toProductSort(int actFlashSaleId, HttpServletRequest request,
                                Map<String, Object> dataMap) {
        ServiceResult<ActFlashSale> serviceResult = actFlashSaleService
            .getActFlashSaleByIdAndSellerId(actFlashSaleId, null);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/flash/actflashsalelist";
            }
        }
        ActFlashSale actFlashSale = serviceResult.getResult();

        dataMap.put("actFlashSale", actFlashSale);

        return "admin/promotion/flash/actflashsaleauditSortWin";
    }

    @RequestMapping(value = "doaudit", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> doAudit(HttpServletRequest request,
                                                         Map<String, Object> dataMap) {

        String actFlashSaleProductIdStr = request.getParameter("actFlashSaleProductId");
        String auditOpinionStr = request.getParameter("auditOpinion");
        String statusStr = request.getParameter("status");
        String actFlashSaleIdStr = request.getParameter("actFlashSaleId");

        if (StringUtil.isEmpty(actFlashSaleProductIdStr, true)) {
            return new HttpJsonResult<Boolean>("请选择操作的活动商品");
        }
        Integer actFlashSaleProductId = ConvertUtil.toInt(actFlashSaleProductIdStr, 0);
        if (actFlashSaleProductId.equals(0)) {
            return new HttpJsonResult<Boolean>("请选择操作的活动商品");
        }

        if (StringUtil.isEmpty(statusStr, true)) {
            return new HttpJsonResult<Boolean>("审核状态不能为空");
        }
        Integer status = ConvertUtil.toInt(statusStr, 0);
        if (status.intValue() != 2 && status.intValue() != 3) {
            return new HttpJsonResult<Boolean>("审核状态值错误");
        }

        //        if (status.intValue() == 3 && StringUtil.isEmpty(auditOpinionStr, true)) {
        //            return new HttpJsonResult<Boolean>("审核失败时必须填写审核失败的原因");
        //        }

        if (StringUtil.isEmpty(actFlashSaleIdStr, true)) {
            return new HttpJsonResult<Boolean>("活动ID不能为空");
        }
        Integer actFlashSaleId = ConvertUtil.toInt(actFlashSaleIdStr, 0);
        if (actFlashSaleId <= 0) {
            return new HttpJsonResult<Boolean>("活动ID不能为空");
        }

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);

        ServiceResult<ActFlashSale> getResult = actFlashSaleService
            .getActFlashSaleById(actFlashSaleId);

        if (!getResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(getResult.getMessage());
        }
        ActFlashSale actFlashSaleDb = getResult.getResult();
        if (actFlashSaleDb == null) {
            return new HttpJsonResult<Boolean>("活动信息获取失败");
        }
        if (!actFlashSaleDb.getStatus().equals(ActFlashSale.STATUS_3)) {
            return new HttpJsonResult<Boolean>("只能审核征集结束的活动");
        }

        ActFlashSaleProduct actFlashSaleProduct = new ActFlashSaleProduct();
        actFlashSaleProduct.setId(actFlashSaleProductId);
        actFlashSaleProduct.setStatus(status);
        actFlashSaleProduct.setAuditOpinion(auditOpinionStr);
        actFlashSaleProduct.setAuditUserId(adminUser.getId());
        actFlashSaleProduct.setAuditUserName(adminUser.getName());
        actFlashSaleProduct.setAuditTime(new Date());

        ServiceResult<Boolean> serviceResult = actFlashSaleProductService
            .updateActFlashSaleProductStatus(actFlashSaleProduct);
        if (!serviceResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(serviceResult.getMessage());
        }
        return new HttpJsonResult<Boolean>();
    }

    @RequestMapping(value = "sort", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> sort(HttpServletRequest request,
                                                      Map<String, Object> dataMap, Integer id,
                                                      String sort) {
        HttpJsonResult<Boolean> jsonresult = new HttpJsonResult<Boolean>();
        ServiceResult<Boolean> serviceResult = null;
        try {
            Integer sortNum = Integer.valueOf(sort);
            ServiceResult<ActFlashSaleProduct> proresult = actFlashSaleProductService
                .getActFlashSaleProductById(id);
            ActFlashSaleProduct pro = proresult.getResult();
            pro.setSort(sortNum);
            serviceResult = actFlashSaleProductService.updateActFlashSaleProduct(pro);
            if (serviceResult.getSuccess() == false) {
                jsonresult.setMessage(serviceResult.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new HttpJsonResult<Boolean>("无效排序号,修改失败");
        }
        return jsonresult;
    }

    @RequestMapping(value = "up", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> up(HttpServletRequest request,
                                                   @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();

        ServiceResult<ActFlashSale> getResult = actFlashSaleService.getActFlashSaleById(id);

        if (!getResult.getSuccess()) {
            jsonResult.setMessage(getResult.getMessage());
            return jsonResult;
        }
        ActFlashSale actFlashSaleDb = getResult.getResult();
        if (actFlashSaleDb == null) {
            jsonResult.setMessage("活动信息获取失败");
            return jsonResult;
        }
        if (!actFlashSaleDb.getStatus().equals(ActFlashSale.STATUS_3)) {
            jsonResult.setMessage("只能上架已结束商品征集的活动");
            return jsonResult;
        }

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        ActFlashSale actFlashSale = new ActFlashSale();
        actFlashSale.setId(id);
        actFlashSale.setStatus(ActFlashSale.STATUS_5);
        actFlashSale.setUpdateUserId(adminUser.getId());
        actFlashSale.setUpdateUserName(adminUser.getName());
        actFlashSale.setUpdateTime(new Date());
        ServiceResult<Boolean> serviceResult = actFlashSaleService
            .updateActFlashSaleStatus(actFlashSale);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "down", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> down(HttpServletRequest request,
                                                     @RequestParam("id") Integer id) {

        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();

        ServiceResult<ActFlashSale> getResult = actFlashSaleService.getActFlashSaleById(id);

        if (!getResult.getSuccess()) {
            jsonResult.setMessage(getResult.getMessage());
            return jsonResult;
        }
        ActFlashSale actFlashSaleDb = getResult.getResult();
        if (actFlashSaleDb == null) {
            jsonResult.setMessage("活动信息获取失败");
            return jsonResult;
        }
        if (!actFlashSaleDb.getStatus().equals(ActFlashSale.STATUS_5)) {
            jsonResult.setMessage("只能对上架状态的活动进行下架");
            return jsonResult;
        }

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);

        ActFlashSale actFlashSale = new ActFlashSale();
        actFlashSale.setId(id);
        actFlashSale.setStatus(ActFlashSale.STATUS_6);
        actFlashSale.setUpdateUserId(adminUser.getId());
        actFlashSale.setUpdateUserName(adminUser.getName());
        actFlashSale.setUpdateTime(new Date());
        ServiceResult<Boolean> serviceResult = actFlashSaleService
            .updateActFlashSaleStatus(actFlashSale);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    public static void main(String[] args) {
        List<String> ss = new ArrayList<String>();
        ss.add("11");
        ss.add("12");
        ss.add("13");

        for (int i = 0; i < ss.size() - 1; i++) {
            String s1 = ss.get(i);
            String s2 = ss.get(i + 1);
            System.out.println(s1 + "----------" + s2);
        }
    }
}
