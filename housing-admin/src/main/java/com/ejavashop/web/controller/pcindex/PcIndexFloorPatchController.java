package com.ejavashop.web.controller.pcindex;

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
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.shopm.pcindex.PcIndexFloor;
import com.ejavashop.entity.shopm.pcindex.PcIndexFloorPatch;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.pcindex.IPcIndexFloorPatchService;
import com.ejavashop.service.pcindex.IPcIndexFloorService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;

/**
 * PC端首页楼层碎屑管理controller
 *                       
 * @Filename: PcIndexFloorPatchController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/pcindex/floorpatch")
public class PcIndexFloorPatchController extends BaseController {

    @Resource
    private IPcIndexFloorPatchService pcIndexFloorPatchService;
    @Resource
    private IPcIndexFloorService      pcIndexFloorService;

    /**
     * PC端首页楼层碎屑列表页
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) {

        Map<String, String> queryMap = new HashMap<String, String>();
        ServiceResult<List<PcIndexFloor>> serviceResult = pcIndexFloorService
            .getPcIndexFloors(queryMap, null);
        dataMap.put("floors", serviceResult.getResult());

        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/pcindex/floorpatch/pcindexfloorpatchlist";
    }

    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<PcIndexFloorPatch>> list(HttpServletRequest request,
                                                                      HttpServletResponse response,
                                                                      Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<PcIndexFloorPatch>> serviceResult = pcIndexFloorPatchService
            .getPcIndexFloorPatchs(queryMap, pager);
        pager = serviceResult.getPager();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<PcIndexFloorPatch> list = serviceResult.getResult();

        HttpJsonResult<List<PcIndexFloorPatch>> jsonResult = new HttpJsonResult<List<PcIndexFloorPatch>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request, Map<String, Object> dataMap) {

        Map<String, String> queryMap = new HashMap<String, String>();
        ServiceResult<List<PcIndexFloor>> serviceResult = pcIndexFloorService
            .getPcIndexFloors(queryMap, null);
        dataMap.put("floors", serviceResult.getResult());

        return "admin/pcindex/floorpatch/pcindexfloorpatchadd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(PcIndexFloorPatch pcIndexFloorPatch, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        Integer userId = adminUser.getId();
        pcIndexFloorPatch.setCreateUserId(userId);
        pcIndexFloorPatch.setCreateUserName(adminUser.getName());
        pcIndexFloorPatch.setUpdateUserId(adminUser.getId());
        pcIndexFloorPatch.setUpdateUserName(adminUser.getName());

        pcIndexFloorPatch.setStatus(PcIndexFloorPatch.STATUS_2);

        ServiceResult<Boolean> serviceResult = pcIndexFloorPatchService
            .savePcIndexFloorPatch(pcIndexFloorPatch);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("pcIndexFloorPatch", pcIndexFloorPatch);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/pcindex/floorpatch/pcindexfloorpatchadd";
            }
        }
        return "redirect:/admin/pcindex/floorpatch";
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(int pcIndexFloorPatchId, Map<String, Object> dataMap) {
        ServiceResult<PcIndexFloorPatch> serviceResult = pcIndexFloorPatchService
            .getPcIndexFloorPatchById(pcIndexFloorPatchId);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "admin/pcindex/floorpatch/pcindexfloorpatchlist";
            }
        }
        PcIndexFloorPatch pcIndexFloorPatch = serviceResult.getResult();

        Map<String, String> queryMap = new HashMap<String, String>();
        ServiceResult<List<PcIndexFloor>> floorResult = pcIndexFloorService
            .getPcIndexFloors(queryMap, null);
        dataMap.put("floors", floorResult.getResult());

        dataMap.put("pcIndexFloorPatch", pcIndexFloorPatch);
        return "admin/pcindex/floorpatch/pcindexfloorpatchedit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(PcIndexFloorPatch pcIndexFloorPatch, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        pcIndexFloorPatch.setUpdateUserId(adminUser.getId());
        pcIndexFloorPatch.setUpdateUserName(adminUser.getName());

        ServiceResult<Boolean> serviceResult = pcIndexFloorPatchService
            .updatePcIndexFloorPatch(pcIndexFloorPatch);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("pcIndexFloorPatch", pcIndexFloorPatch);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/pcindex/floorpatch/pcindexfloorpatchedit";
            }
        }
        return "redirect:/admin/pcindex/floorpatch";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> delete(HttpServletRequest request,
                                                        @RequestParam("id") Integer id) {

        ServiceResult<PcIndexFloorPatch> pcIndexFloorPatchResult = pcIndexFloorPatchService
            .getPcIndexFloorPatchById(id);
        if (!pcIndexFloorPatchResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(pcIndexFloorPatchResult.getMessage());
        }
        if (pcIndexFloorPatchResult.getResult() == null) {
            return new HttpJsonResult<Boolean>("楼层碎屑信息获取失败");
        }
        if (pcIndexFloorPatchResult.getResult().getStatus().equals(PcIndexFloorPatch.STATUS_1)) {
            return new HttpJsonResult<Boolean>("正在使用的楼层碎屑不能删除");
        }

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        ServiceResult<Boolean> serviceResult = pcIndexFloorPatchService.deletePcIndexFloorPatch(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "up", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> up(HttpServletRequest request,
                                                   @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        PcIndexFloorPatch pcIndexFloorPatch = new PcIndexFloorPatch();
        pcIndexFloorPatch.setId(id);
        pcIndexFloorPatch.setStatus(PcIndexFloorPatch.STATUS_1);
        pcIndexFloorPatch.setUpdateUserId(adminUser.getId());
        pcIndexFloorPatch.setUpdateUserName(adminUser.getName());
        ServiceResult<Boolean> serviceResult = pcIndexFloorPatchService
            .updatePcIndexFloorPatch(pcIndexFloorPatch);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "pre", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> pre(HttpServletRequest request,
                                                    @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        PcIndexFloorPatch pcIndexFloorPatch = new PcIndexFloorPatch();
        pcIndexFloorPatch.setId(id);
        pcIndexFloorPatch.setStatus(PcIndexFloorPatch.STATUS_2);
        pcIndexFloorPatch.setUpdateUserId(adminUser.getId());
        pcIndexFloorPatch.setUpdateUserName(adminUser.getName());
        ServiceResult<Boolean> serviceResult = pcIndexFloorPatchService
            .updatePcIndexFloorPatch(pcIndexFloorPatch);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "down", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> down(HttpServletRequest request,
                                                     @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);

        PcIndexFloorPatch pcIndexFloorPatch = new PcIndexFloorPatch();
        pcIndexFloorPatch.setId(id);
        pcIndexFloorPatch.setStatus(PcIndexFloorPatch.STATUS_3);
        pcIndexFloorPatch.setUpdateUserId(adminUser.getId());
        pcIndexFloorPatch.setUpdateUserName(adminUser.getName());
        ServiceResult<Boolean> serviceResult = pcIndexFloorPatchService
            .updatePcIndexFloorPatch(pcIndexFloorPatch);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

}
