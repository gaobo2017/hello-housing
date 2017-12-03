package com.ejavashop.web.controller.house;

import java.util.HashMap;
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
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.house.HousingResources;
import com.ejavashop.entity.seller.SellerApply;
import com.ejavashop.entity.system.Regions;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.house.IHouseManageService;
import com.ejavashop.service.seller.ISellerApplyService;
import com.ejavashop.service.seller.ISellerService;
import com.ejavashop.service.system.IRegionsService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.UploadUtil;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 房源管理controller
 *                       
 * @Filename: HouseManageController.java
 * @Version: 1.0
 * @Author: gao
 * @Email: 
 *
 */
@Controller
@RequestMapping(value = "/admin/house/manage")
public class HouseManageController extends BaseController {

    @Resource(name = "sellerApplyService")
    private ISellerApplyService sellerApplyService;

    @Resource(name = "sellerService")
    private ISellerService      sellerService;

    @Resource
    private IRegionsService     regionsService;

    @Resource(name = "houseManageService")
    private IHouseManageService houseManageService;

    /**
     * 默认页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/house/house/houselist";
    }

    /**
     * 房源查询列表
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<HousingResources>> list(HttpServletRequest request,
                                                                     Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<HousingResources>> serviceResult = houseManageService
            .getHousingResourcesList(queryMap, pager);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<HousingResources>> jsonResult = new HttpJsonResult<List<HousingResources>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    /**
     * 添加房源页面
     * @param dataMap
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(Map<String, Object> dataMap) throws Exception {

        //        ServiceResult<List<HousingResources>> provinceResult = regionsService.getRegionsByParentId(0);
        //        dataMap.put("provinceList", provinceResult.getResult());

        return "admin/house/house/houseadd";
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(Integer id, Map<String, Object> dataMap) {
        ServiceResult<HousingResources> serviceResult = houseManageService
            .getHousingResourcesById(id);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/house/house/houselist";
            }
        }

        HousingResources housingResources = serviceResult.getResult();

        dataMap.put("housingResources", housingResources);

        //        ServiceResult<Seller> sellerResult = sellerService
        //            .getSellerByMemberId(sellerApply.getUserId());
        //        if (!sellerResult.getSuccess()) {
        //            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(sellerResult.getCode())) {
        //                throw new RuntimeException(sellerResult.getMessage());
        //            } else {
        //                dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        //                dataMap.put("message", sellerResult.getMessage());
        //                return "admin/house/house/houselist";
        //            }
        //        }
        //        Seller seller = sellerResult.getResult();
        //        if (seller != null) {
        //            dataMap.put("userName", seller.getName());
        //            dataMap.put("sellerName", seller.getSellerName());
        //        }
        //
        //        ServiceResult<List<Regions>> provinceResult = regionsService.getRegionsByParentId(0);
        //        dataMap.put("provinceList", provinceResult.getResult());
        //
        //        ServiceResult<List<Regions>> companyCityResult = regionsService
        //            .getRegionsByParentId(ConvertUtil.toInt(sellerApply.getCompanyProvince(), null));
        //        dataMap.put("companyCityList", companyCityResult.getResult());
        //
        //        ServiceResult<List<Regions>> bankCityResult = regionsService
        //            .getRegionsByParentId(ConvertUtil.toInt(sellerApply.getBankProvince(), null));
        //        dataMap.put("bankCityList", bankCityResult.getResult());

        return "admin/house/house/houseedit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(HousingResources housingResources, HttpServletRequest request,
                         Map<String, Object> dataMap) {
        Map<String, String> param = new HashMap<>();

        //获取操作人name和id 
        SystemAdmin systemAdmin = WebAdminSession.getAdminUser(request);
        housingResources.setOperationId(systemAdmin.getId());
        housingResources.setOperationName(systemAdmin.getName());

        //        //营业执照扫描件
        //        String bli = UploadUtil.getInstance().uploadFile2ImageServer("up_bussinessLicenseImage",
        //            request, param);
        //        if (!StringUtil.isEmpty(bli)) {
        //            sellerApply.setBussinessLicenseImage(bli);
        //        }

        //        if (StringUtil.isEmpty(sellerName, true)) {
        //            dataMap.put("userName", userName);
        //            dataMap.put("sellerName", sellerName);
        //            dataMap.put("sellerApply", sellerApply);
        //            dataMap.put("message", "商家店铺名称不能为空！");
        //            ServiceResult<List<Regions>> provinceResult = regionsService.getRegionsByParentId(0);
        //            dataMap.put("provinceList", provinceResult.getResult());
        //            ServiceResult<List<Regions>> companyCityResult = regionsService
        //                .getRegionsByParentId(ConvertUtil.toInt(sellerApply.getCompanyProvince(), null));
        //            dataMap.put("companyCityList", companyCityResult.getResult());
        //            ServiceResult<List<Regions>> bankCityResult = regionsService
        //                .getRegionsByParentId(ConvertUtil.toInt(sellerApply.getBankProvince(), null));
        //            dataMap.put("bankCityList", bankCityResult.getResult());
        //            return "admin/house/house/houseedit";
        //        }

        ServiceResult<Integer> serviceResult = houseManageService
            .updateHousingResources(housingResources);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                //                dataMap.put("userName", userName);
                //                dataMap.put("sellerName", sellerName);
                dataMap.put("housingResources", housingResources);
                dataMap.put("message", serviceResult.getMessage());

                return "admin/house/house/houseedit";
            }
        }
        return "redirect:/admin/house/manage";
    }

    /**
     * 删除商家申请
     * @param dataMap
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "delete", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> delete(HttpServletResponse response,
                                                        Map<String, Object> dataMap, Integer id,
                                                        Integer userId) throws Exception {

        ServiceResult<Boolean> serviceResult = sellerApplyService.deleteSellerApply(id, userId);
        HttpJsonResult<Boolean> jsonResult = null;
        if (serviceResult.getSuccess()) {
            jsonResult = new HttpJsonResult<Boolean>();
        } else {
            jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
        }

        return jsonResult;
    }

    /**
     * 跳往审核页面
     * @param dataMap
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "audit.html", method = { RequestMethod.GET })
    public String audit(Map<String, Object> dataMap, Integer id) throws Exception {
        ServiceResult<SellerApply> sr = sellerApplyService.getSellerApplyById(id);
        dataMap.put("app", sr.getResult());
        dataMap.put("stringUtil", new StringUtil());
        return "admin/seller/audit/sellerapplyaudit";
    }

    /**
     * 审核通过
     * @param request
     * @param response
     * @param dataMap
     * @param id
     */
    @RequestMapping(value = "pass", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> pass(HttpServletRequest request,
                                                      HttpServletResponse response,
                                                      Map<String, Object> dataMap, Integer id) {

        ServiceResult<Boolean> serviceResult = sellerApplyService.auditSellerApply(id,
            SellerApply.STATE_2_DONE, WebAdminSession.getAdminUser(request).getId());
        HttpJsonResult<Boolean> jsonResult = null;
        if (serviceResult.getSuccess()) {
            jsonResult = new HttpJsonResult<Boolean>();
        } else {
            jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /**
     * 申请被驳回
     * @param request
     * @param response
     * @param dataMap
     * @param id
     */
    @RequestMapping(value = "reject", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> reject(HttpServletRequest request,
                                                        HttpServletResponse response,
                                                        Map<String, Object> dataMap, Integer id) {

        ServiceResult<Boolean> serviceResult = sellerApplyService.auditSellerApply(id,
            SellerApply.STATE_4_FAIL, WebAdminSession.getAdminUser(request).getId());
        HttpJsonResult<Boolean> jsonResult = null;
        if (serviceResult.getSuccess()) {
            jsonResult = new HttpJsonResult<Boolean>();
        } else {
            jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(SellerApply sellerApply, HttpServletRequest request,
                         Map<String, Object> dataMap) {
        Map<String, String> param = new HashMap<>();
        param.put("admin", "sellerapply");

        //营业执照扫描件
        String bli = UploadUtil.getInstance().uploadFile2ImageServer("up_bussinessLicenseImage",
            request, param);
        if (!StringUtil.isEmpty(bli)) {
            sellerApply.setBussinessLicenseImage(bli);
        }

        //身份证正面
        String pcu = UploadUtil.getInstance().uploadFile2ImageServer("up_personCardUp", request,
            param);
        if (!StringUtil.isEmpty(pcu)) {
            sellerApply.setPersonCardUp(pcu);
        }

        //身份证反面
        String pdw = UploadUtil.getInstance().uploadFile2ImageServer("up_personCardDown", request,
            param);
        if (!StringUtil.isEmpty(pdw)) {
            sellerApply.setPersonCardDown(pdw);
        }

        sellerApply.setState(SellerApply.STATE_1_SEND);
        sellerApply.setType(2);

        String userName = request.getParameter("userName");
        String sellerName = request.getParameter("sellerName");

        if (StringUtil.isEmpty(userName, true)) {
            dataMap.put("userName", userName);
            dataMap.put("sellerName", sellerName);
            dataMap.put("sellerApply", sellerApply);
            dataMap.put("message", "商家账号不能为空！");
            ServiceResult<List<Regions>> provinceResult = regionsService.getRegionsByParentId(0);
            dataMap.put("provinceList", provinceResult.getResult());
            return "admin/seller/audit/sellerapplyadd";
        }
        if (StringUtil.isEmpty(sellerName, true)) {
            dataMap.put("userName", userName);
            dataMap.put("sellerName", sellerName);
            dataMap.put("sellerApply", sellerApply);
            dataMap.put("message", "商家店铺名称不能为空！");
            ServiceResult<List<Regions>> provinceResult = regionsService.getRegionsByParentId(0);
            dataMap.put("provinceList", provinceResult.getResult());
            return "admin/seller/audit/sellerapplyadd";
        }

        String ip = WebUtil.getIpAddr(request);

        ServiceResult<Boolean> serviceResult = sellerApplyService
            .saveSellerApplyForAdmin(sellerApply, userName, sellerName, ip);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("userName", userName);
                dataMap.put("sellerName", sellerName);
                dataMap.put("sellerApply", sellerApply);
                dataMap.put("message", serviceResult.getMessage());
                ServiceResult<List<Regions>> provinceResult = regionsService
                    .getRegionsByParentId(0);
                dataMap.put("provinceList", provinceResult.getResult());
                return "admin/seller/audit/sellerapplyadd";
            }
        }
        return "redirect:/admin/seller/audit";
    }

}
