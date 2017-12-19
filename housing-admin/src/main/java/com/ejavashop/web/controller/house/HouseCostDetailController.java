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
import com.ejavashop.entity.house.HousingCostDetail;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.house.IHouseCostService;
import com.ejavashop.vo.house.HousingCostVO;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 成本明细管理controller
 *                       
 * @Filename: HouseCostDetailController.java
 * @Version: 1.0
 * @Author: gao
 * @Email: 
 *
 */
@Controller
@RequestMapping(value = "/admin/costdetail/manage")
public class HouseCostDetailController extends BaseController {

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
        return "admin/house/cost/costdetaillist";
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
            .getHousingCostDetailList(queryMap, pager);

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
     * 添加成本明細页面
     * @param dataMap
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(Map<String, Object> dataMap, Integer costId, Integer houseId,
                      String roomCodeNo) throws Exception {

        HousingCostDetail hcd = new HousingCostDetail();
        hcd.setCostId(costId);
        hcd.setHouseId(houseId);
        hcd.setRoomCodeNo(roomCodeNo);
        dataMap.put("housingCostDetail", hcd);

        return "admin/house/cost/costdetailadd";
    }
        @RequestMapping(value = "create", method = { RequestMethod.POST })
        public String create(HousingCostDetail housingCostDetail, HttpServletRequest request,
                             Map<String, Object> dataMap) {
            Map<String, String> param = new HashMap<>();
            //获取操作人name和id 
            SystemAdmin systemAdmin = WebAdminSession.getAdminUser(request);
            housingCostDetail.setOperationId(systemAdmin.getId());
            housingCostDetail.setOperationName(systemAdmin.getName());
    
            ServiceResult<Integer> serviceResult = houseCostService
                .createHousingCostDetailAndSummaryCost(housingCostDetail);
            if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    
                    dataMap.put("housingCostDetail", housingCostDetail);
                    dataMap.put("message", serviceResult.getMessage());
    
                    return "admin/house/cost/costdetailadd";
                }
            }
            return "redirect:/admin/costdetail/manage";
    
        }
        
        
        
        @RequestMapping(value = "edit", method = { RequestMethod.GET })
        public String edit(Integer id, Map<String, Object> dataMap) {
            ServiceResult<HousingCostDetail> serviceResult = houseCostService
                .getHousingCostDetailById(id);
    
            if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
                    dataMap.put("message", serviceResult.getMessage());
                    return "admin/house/cost/costdetaillist";
                }
            }
    
            HousingCostDetail housingCostDetail = serviceResult.getResult();
    
            dataMap.put("housingCostDetail", housingCostDetail);
    
           
    
            return "admin/house/cost/costdetailedit";
        }

        
        @RequestMapping(value = "update", method = { RequestMethod.POST })
        public String update(HousingCostDetail housingCostDetail, HttpServletRequest request,
                             Map<String, Object> dataMap) {
            Map<String, String> param = new HashMap<>();
    
            //获取操作人name和id 
            SystemAdmin systemAdmin = WebAdminSession.getAdminUser(request);
            housingCostDetail.setOperationId(systemAdmin.getId());
            housingCostDetail.setOperationName(systemAdmin.getName());
    
           
    
            ServiceResult<Integer> serviceResult = houseCostService
                .updateHousingCostDetail(housingCostDetail);
            if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    //                dataMap.put("userName", userName);
                    //                dataMap.put("sellerName", sellerName);
                    dataMap.put("housingCostDetail", housingCostDetail);
                    dataMap.put("message", serviceResult.getMessage());
    
                    return "admin/house/cost/costdetailedit";
                }
            }
            return "redirect:/admin/costdetail/manage";
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
