package com.ejavashop.web.controller.pcindex;

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
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.pcindex.IPcIndexFloorService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.UploadUtil;
import com.ejavashop.web.util.WebAdminSession;

/**
 * PC端首页楼层管理controller
 *                       
 * @Filename: PcIndexFloorController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/pcindex/floor")
public class PcIndexFloorController extends BaseController {

    @Resource
    private IPcIndexFloorService pcIndexFloorService;

    /**
     * PC端首页楼层列表页
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/pcindex/floor/pcindexfloorlist";
    }

    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<PcIndexFloor>> list(HttpServletRequest request,
                                                                 HttpServletResponse response,
                                                                 Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<PcIndexFloor>> serviceResult = pcIndexFloorService
            .getPcIndexFloors(queryMap, pager);
        pager = serviceResult.getPager();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<PcIndexFloor> list = serviceResult.getResult();

        HttpJsonResult<List<PcIndexFloor>> jsonResult = new HttpJsonResult<List<PcIndexFloor>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request, Map<String, Object> dataMap) {
        return "admin/pcindex/floor/pcindexflooradd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(PcIndexFloor pcIndexFloor, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        Integer userId = adminUser.getId();
        pcIndexFloor.setCreateUserId(userId);
        pcIndexFloor.setCreateUserName(adminUser.getName());
        pcIndexFloor.setUpdateUserId(adminUser.getId());
        pcIndexFloor.setUpdateUserName(adminUser.getName());

        pcIndexFloor.setStatus(PcIndexFloor.STATUS_2);

        // 上传图片
        String masterImage = UploadUtil.getInstance().advUploadFile2ImageServer("masterImageFile",
            request);
        if (masterImage != null && !"".equals(masterImage)) {
            pcIndexFloor.setMasterImage(masterImage);
        }
        String advImage = UploadUtil.getInstance().advUploadFile2ImageServer("advImageFile",
            request);
        if (advImage != null && !"".equals(advImage)) {
            pcIndexFloor.setAdvImage(advImage);
        }

        ServiceResult<Boolean> serviceResult = pcIndexFloorService.savePcIndexFloor(pcIndexFloor);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("pcIndexFloor", pcIndexFloor);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/pcindex/floor/pcindexflooradd";
            }
        }
        return "redirect:/admin/pcindex/floor";
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(int pcIndexFloorId, Map<String, Object> dataMap) {
        ServiceResult<PcIndexFloor> serviceResult = pcIndexFloorService
            .getPcIndexFloorById(pcIndexFloorId);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "admin/pcindex/floor/pcindexfloorlist";
            }
        }
        PcIndexFloor pcIndexFloor = serviceResult.getResult();

        dataMap.put("pcIndexFloor", pcIndexFloor);
        return "admin/pcindex/floor/pcindexflooredit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(PcIndexFloor pcIndexFloor, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        pcIndexFloor.setUpdateUserId(adminUser.getId());
        pcIndexFloor.setUpdateUserName(adminUser.getName());

        // 上传图片
        String masterImage = UploadUtil.getInstance().advUploadFile2ImageServer("masterImageFile",
            request);
        if (masterImage != null && !"".equals(masterImage)) {
            pcIndexFloor.setMasterImage(masterImage);
        }
        String advImage = UploadUtil.getInstance().advUploadFile2ImageServer("advImageFile",
            request);
        if (advImage != null && !"".equals(advImage)) {
            pcIndexFloor.setAdvImage(advImage);
        }

        ServiceResult<Boolean> serviceResult = pcIndexFloorService.updatePcIndexFloor(pcIndexFloor);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("pcIndexFloor", pcIndexFloor);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/pcindex/floor/pcindexflooredit";
            }
        }
        return "redirect:/admin/pcindex/floor";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> delete(HttpServletRequest request,
                                                        @RequestParam("id") Integer id) {

        ServiceResult<PcIndexFloor> pcIndexFloorResult = pcIndexFloorService
            .getPcIndexFloorById(id);
        if (!pcIndexFloorResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(pcIndexFloorResult.getMessage());
        }
        if (pcIndexFloorResult.getResult() == null) {
            return new HttpJsonResult<Boolean>("楼层信息获取失败");
        }
        if (pcIndexFloorResult.getResult().getStatus().equals(PcIndexFloor.STATUS_1)) {
            return new HttpJsonResult<Boolean>("正在使用的楼层不能删除");
        }

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        ServiceResult<Boolean> serviceResult = pcIndexFloorService.deletePcIndexFloor(id);
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
        PcIndexFloor pcIndexFloor = new PcIndexFloor();
        pcIndexFloor.setId(id);
        pcIndexFloor.setStatus(PcIndexFloor.STATUS_1);
        pcIndexFloor.setUpdateUserId(adminUser.getId());
        pcIndexFloor.setUpdateUserName(adminUser.getName());
        ServiceResult<Boolean> serviceResult = pcIndexFloorService.updatePcIndexFloor(pcIndexFloor);
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
        PcIndexFloor pcIndexFloor = new PcIndexFloor();
        pcIndexFloor.setId(id);
        pcIndexFloor.setStatus(PcIndexFloor.STATUS_2);
        pcIndexFloor.setUpdateUserId(adminUser.getId());
        pcIndexFloor.setUpdateUserName(adminUser.getName());
        ServiceResult<Boolean> serviceResult = pcIndexFloorService.updatePcIndexFloor(pcIndexFloor);
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

        PcIndexFloor pcIndexFloor = new PcIndexFloor();
        pcIndexFloor.setId(id);
        pcIndexFloor.setStatus(PcIndexFloor.STATUS_3);
        pcIndexFloor.setUpdateUserId(adminUser.getId());
        pcIndexFloor.setUpdateUserName(adminUser.getName());
        ServiceResult<Boolean> serviceResult = pcIndexFloorService.updatePcIndexFloor(pcIndexFloor);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

}
