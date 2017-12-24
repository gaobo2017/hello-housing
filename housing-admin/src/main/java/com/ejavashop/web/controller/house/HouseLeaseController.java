package com.ejavashop.web.controller.house;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.house.HousingLease;
import com.ejavashop.entity.house.HousingResources;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.house.IHouseCostService;
import com.ejavashop.service.house.IHouseLeaseService;
import com.ejavashop.service.house.IHouseManageService;
import com.ejavashop.vo.house.HousingLeaseVO;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 出租管理controller
 *                       
 * @Filename: HouseLeaseController.java
 * @Version: 1.0
 * @Author: gao
 * @Email: 
 *
 */
@Controller
@RequestMapping(value = "/admin/rent/manage")
public class HouseLeaseController extends BaseController {

    @Resource(name = "houseLeaseService")
    private IHouseLeaseService  houseLeaseService;

    @Resource(name = "houseCostService")
    private IHouseCostService   houseCostService;

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
        return "admin/house/rent/rentlist";
    }

    /**
     * 房源查询列表
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<List<HousingLeaseVO>> list(HttpServletRequest request,
                                                                   Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<HousingLeaseVO>> serviceResult = houseLeaseService
            .getHousingLeaseList(queryMap, pager);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<HousingLeaseVO>> jsonResult = new HttpJsonResult<List<HousingLeaseVO>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    /**
     * 添加出租页面
     * @param dataMap
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(Map<String, Object> dataMap, Integer houseId) throws Exception {

        //获取 RoomCodeNo
        ServiceResult<HousingResources> serviceResult = houseManageService
            .getHousingResourcesById(houseId);
        HousingResources housingResources = serviceResult.getResult();

        HousingLease hcd = new HousingLease();

        hcd.setHouseId(houseId);
        hcd.setRoomCodeNo(housingResources.getRoomCodeNo());
        dataMap.put("housingLease", hcd);

        return "admin/house/rent/rentadd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(HousingLease housingLease, HttpServletRequest request,
                         Map<String, Object> dataMap) {
        Map<String, String> param = new HashMap<>();
        //获取操作人name和id 
        SystemAdmin systemAdmin = WebAdminSession.getAdminUser(request);
        housingLease.setOperationId(systemAdmin.getId());
        housingLease.setOperationName(systemAdmin.getName());

        ServiceResult<Integer> serviceResult = houseLeaseService
            .createHousingLeaseAndSummaryIncome(housingLease);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {

                dataMap.put("housingLease", housingLease);
                dataMap.put("message", serviceResult.getMessage());

                return "admin/house/rent/rentadd";
            }
        }
        return "redirect:/admin/rent/manage";

    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(Integer id, Map<String, Object> dataMap) {
        ServiceResult<HousingLease> serviceResult = houseLeaseService.getHousingLeaseById(id);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/house/rent/rentlist";
            }
        }

        HousingLease housingLease = serviceResult.getResult();

        dataMap.put("housingLease", housingLease);

        return "admin/house/rent/rentedit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(HousingLease housingLease, HttpServletRequest request,
                         Map<String, Object> dataMap) {
        Map<String, String> param = new HashMap<>();

        //获取操作人name和id 
        SystemAdmin systemAdmin = WebAdminSession.getAdminUser(request);
        housingLease.setOperationId(systemAdmin.getId());
        housingLease.setOperationName(systemAdmin.getName());

        ServiceResult<Integer> serviceResult = houseLeaseService.updateHousingLease(housingLease);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                //                dataMap.put("userName", userName);
                //                dataMap.put("sellerName", sellerName);
                dataMap.put("housingLease", housingLease);
                dataMap.put("message", serviceResult.getMessage());

                return "admin/house/rent/rentedit";
            }
        }
        return "redirect:/admin/rent/manage";
    }

    /**
     * 删除成本明细
     * @param dataMap
     * @param id
     * @return
     * @throws Exception
     */
    //        @RequestMapping(value = "delete", method = { RequestMethod.GET })
    //        public @ResponseBody HttpJsonResult<Boolean> delete(HttpServletResponse response, Integer id) {
    //    
    //            ServiceResult<Boolean> serviceResult = houseManageService.deleteHousingResources(id);
    //            HttpJsonResult<Boolean> jsonResult = null;
    //            if (serviceResult.getSuccess()) {
    //                jsonResult = new HttpJsonResult<Boolean>();
    //            } else {
    //                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
    //            }
    //    
    //            return jsonResult;
    //        }

    @RequestMapping(value = "delete", method = { RequestMethod.GET })
    public void del(HttpServletRequest request, HttpServletResponse response, Integer id) {
        response.setContentType("text/plain;charset=utf-8");
        String msg = "删除成功";
        PrintWriter pw = null;

        try {
            ServiceResult<Boolean> serviceResult = houseCostService.deleteHousingCostDetail(id);
            if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    throw new BusinessException(serviceResult.getMessage());
                }
            }
            pw = response.getWriter();
        } catch (IOException e) {
            log.error("[admin][HouseManageController] del exception", e);
            msg = e.getMessage();
        }
        pw.print(msg);
    }

}
