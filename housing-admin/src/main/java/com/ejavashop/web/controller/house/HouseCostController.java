package com.ejavashop.web.controller.house;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.ejavashop.service.house.IHouseCostService;
import com.ejavashop.vo.house.HousingCostVO;
import com.ejavashop.web.controller.BaseController;

/**
 * 房源成本管理controller
 *                       
 * @Filename: HouseCostController.java
 * @Version: 1.0
 * @Author: gao
 * @Email: 
 *
 */
@Controller
@RequestMapping(value = "/admin/cost/manage")
public class HouseCostController extends BaseController {

    @Resource(name = "houseCostService")
    private IHouseCostService houseCostService;

    /**
     * 默认页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/house/cost/costlist";
    }

    /**
     * 房源查询列表
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<List<HousingCostVO>> list(HttpServletRequest request,
                                                                  Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<HousingCostVO>> serviceResult = houseCostService
            .getHousingCostList(queryMap, pager);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<HousingCostVO>> jsonResult = new HttpJsonResult<List<HousingCostVO>>();
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

        return "admin/house/cost/costadd";
    }

    //    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    //    public String edit(Integer id, Map<String, Object> dataMap) {
    //        ServiceResult<HousingResources> serviceResult = houseManageService
    //            .getHousingResourcesById(id);
    //
    //        if (!serviceResult.getSuccess()) {
    //            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
    //                throw new RuntimeException(serviceResult.getMessage());
    //            } else {
    //                dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
    //                dataMap.put("message", serviceResult.getMessage());
    //                return "admin/house/cost/costlist";
    //            }
    //        }
    //
    //        HousingResources housingResources = serviceResult.getResult();
    //
    //        dataMap.put("housingResources", housingResources);
    //
    //        //        ServiceResult<Seller> sellerResult = sellerService
    //        //            .getSellerByMemberId(sellerApply.getUserId());
    //        //        if (!sellerResult.getSuccess()) {
    //        //            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(sellerResult.getCode())) {
    //        //                throw new RuntimeException(sellerResult.getMessage());
    //        //            } else {
    //        //                dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
    //        //                dataMap.put("message", sellerResult.getMessage());
    //        //                return "admin/house/house/houselist";
    //        //            }
    //        //        }
    //        //        Seller seller = sellerResult.getResult();
    //        //        if (seller != null) {
    //        //            dataMap.put("userName", seller.getName());
    //        //            dataMap.put("sellerName", seller.getSellerName());
    //        //        }
    //        //
    //        //        ServiceResult<List<Regions>> provinceResult = regionsService.getRegionsByParentId(0);
    //        //        dataMap.put("provinceList", provinceResult.getResult());
    //        //
    //        //        ServiceResult<List<Regions>> companyCityResult = regionsService
    //        //            .getRegionsByParentId(ConvertUtil.toInt(sellerApply.getCompanyProvince(), null));
    //        //        dataMap.put("companyCityList", companyCityResult.getResult());
    //        //
    //        //        ServiceResult<List<Regions>> bankCityResult = regionsService
    //        //            .getRegionsByParentId(ConvertUtil.toInt(sellerApply.getBankProvince(), null));
    //        //        dataMap.put("bankCityList", bankCityResult.getResult());
    //
    //        return "admin/house/house/houseedit";
    //    }

    //    @RequestMapping(value = "update", method = { RequestMethod.POST })
    //    public String update(HousingResources housingResources, HttpServletRequest request,
    //                         Map<String, Object> dataMap) {
    //        Map<String, String> param = new HashMap<>();
    //
    //        //获取操作人name和id 
    //        SystemAdmin systemAdmin = WebAdminSession.getAdminUser(request);
    //        housingResources.setOperationId(systemAdmin.getId());
    //        housingResources.setOperationName(systemAdmin.getName());
    //
    //        //        //营业执照扫描件
    //        //        String bli = UploadUtil.getInstance().uploadFile2ImageServer("up_bussinessLicenseImage",
    //        //            request, param);
    //        //        if (!StringUtil.isEmpty(bli)) {
    //        //            sellerApply.setBussinessLicenseImage(bli);
    //        //        }
    //
    //        //        if (StringUtil.isEmpty(sellerName, true)) {
    //        //            dataMap.put("userName", userName);
    //        //            dataMap.put("sellerName", sellerName);
    //        //            dataMap.put("sellerApply", sellerApply);
    //        //            dataMap.put("message", "商家店铺名称不能为空！");
    //        //            ServiceResult<List<Regions>> provinceResult = regionsService.getRegionsByParentId(0);
    //        //            dataMap.put("provinceList", provinceResult.getResult());
    //        //            ServiceResult<List<Regions>> companyCityResult = regionsService
    //        //                .getRegionsByParentId(ConvertUtil.toInt(sellerApply.getCompanyProvince(), null));
    //        //            dataMap.put("companyCityList", companyCityResult.getResult());
    //        //            ServiceResult<List<Regions>> bankCityResult = regionsService
    //        //                .getRegionsByParentId(ConvertUtil.toInt(sellerApply.getBankProvince(), null));
    //        //            dataMap.put("bankCityList", bankCityResult.getResult());
    //        //            return "admin/house/house/houseedit";
    //        //        }
    //
    //        ServiceResult<Integer> serviceResult = houseManageService
    //            .updateHousingResources(housingResources);
    //        if (!serviceResult.getSuccess()) {
    //            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
    //                throw new RuntimeException(serviceResult.getMessage());
    //            } else {
    //                //                dataMap.put("userName", userName);
    //                //                dataMap.put("sellerName", sellerName);
    //                dataMap.put("housingResources", housingResources);
    //                dataMap.put("message", serviceResult.getMessage());
    //
    //                return "admin/house/house/houseedit";
    //            }
    //        }
    //        return "redirect:/admin/house/manage";
    //    }

    //    @RequestMapping(value = "create", method = { RequestMethod.POST })
    //    public String create(HousingResources housingResources, HttpServletRequest request,
    //                         Map<String, Object> dataMap) {
    //        Map<String, String> param = new HashMap<>();
    //        //获取操作人name和id 
    //        SystemAdmin systemAdmin = WebAdminSession.getAdminUser(request);
    //        housingResources.setOperationId(systemAdmin.getId());
    //        housingResources.setOperationName(systemAdmin.getName());
    //
    //        ServiceResult<Integer> serviceResult = houseManageService
    //            .createHousingResources(housingResources);
    //        if (!serviceResult.getSuccess()) {
    //            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
    //                throw new RuntimeException(serviceResult.getMessage());
    //            } else {
    //
    //                dataMap.put("housingResources", housingResources);
    //                dataMap.put("message", serviceResult.getMessage());
    //
    //                return "admin/house/house/houseadd";
    //            }
    //        }
    //        return "redirect:/admin/house/manage";
    //
    //    }

    /**
     * 删除房源
     * @param dataMap
     * @param id
     * @return
     * @throws Exception
     */
    //    @RequestMapping(value = "delete", method = { RequestMethod.GET })
    //    public @ResponseBody HttpJsonResult<Boolean> delete(HttpServletResponse response, Integer id) {
    //
    //        ServiceResult<Boolean> serviceResult = houseManageService.deleteHousingResources(id);
    //        HttpJsonResult<Boolean> jsonResult = null;
    //        if (serviceResult.getSuccess()) {
    //            jsonResult = new HttpJsonResult<Boolean>();
    //        } else {
    //            jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
    //        }
    //
    //        return jsonResult;
    //    }

    //    @RequestMapping(value = "delete", method = { RequestMethod.GET })
    //    public void del(HttpServletRequest request, HttpServletResponse response, Integer id) {
    //        response.setContentType("text/plain;charset=utf-8");
    //        String msg = "删除成功";
    //        PrintWriter pw = null;
    //
    //        try {
    //            ServiceResult<Boolean> serviceResult = houseManageService.deleteHousingResources(id);
    //            if (!serviceResult.getSuccess()) {
    //                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
    //                    throw new RuntimeException(serviceResult.getMessage());
    //                } else {
    //                    throw new BusinessException(serviceResult.getMessage());
    //                }
    //            }
    //            pw = response.getWriter();
    //        } catch (IOException e) {
    //            log.error("[admin][HouseManageController] del exception", e);
    //            msg = e.getMessage();
    //        }
    //        pw.print(msg);
    //    }

}
