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
import com.ejavashop.entity.shopm.pcindex.PcIndexFloorClass;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.pcindex.IPcIndexFloorClassService;
import com.ejavashop.service.pcindex.IPcIndexFloorService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;

/**
 * PC端首页楼层分类管理controller
 *                       
 * @Filename: PcIndexFloorClassController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/pcindex/floorclass")
public class PcIndexFloorClassController extends BaseController {

    @Resource
    private IPcIndexFloorClassService pcIndexFloorClassService;
    @Resource
    private IPcIndexFloorService      pcIndexFloorService;

    /**
     * PC端首页楼层分类列表页
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
        return "admin/pcindex/floorclass/pcindexfloorclasslist";
    }

    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<PcIndexFloorClass>> list(HttpServletRequest request,
                                                                      HttpServletResponse response,
                                                                      Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<PcIndexFloorClass>> serviceResult = pcIndexFloorClassService
            .getPcIndexFloorClasss(queryMap, pager);
        pager = serviceResult.getPager();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<PcIndexFloorClass> list = serviceResult.getResult();

        HttpJsonResult<List<PcIndexFloorClass>> jsonResult = new HttpJsonResult<List<PcIndexFloorClass>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    /**
     * 根据楼层ID取楼层分类
     * @param request
     * @param response
     * @param dataMap
     * @param floorId
     * @return
     */
    @RequestMapping(value = "listbyfloor", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<PcIndexFloorClass>> listByFloor(HttpServletRequest request,
                                                                             HttpServletResponse response,
                                                                             Map<String, Object> dataMap,
                                                                             Integer floorId) {
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("q_floorId", floorId.toString());

        ServiceResult<List<PcIndexFloorClass>> serviceResult = pcIndexFloorClassService
            .getPcIndexFloorClasss(queryMap, null);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<PcIndexFloorClass> list = serviceResult.getResult();

        HttpJsonResult<List<PcIndexFloorClass>> jsonResult = new HttpJsonResult<List<PcIndexFloorClass>>();
        jsonResult.setRows(list);
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request, Map<String, Object> dataMap) {

        Map<String, String> queryMap = new HashMap<String, String>();
        ServiceResult<List<PcIndexFloor>> serviceResult = pcIndexFloorService
            .getPcIndexFloors(queryMap, null);
        dataMap.put("floors", serviceResult.getResult());

        return "admin/pcindex/floorclass/pcindexfloorclassadd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(PcIndexFloorClass pcIndexFloorClass, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        Integer userId = adminUser.getId();
        pcIndexFloorClass.setCreateUserId(userId);
        pcIndexFloorClass.setCreateUserName(adminUser.getName());
        pcIndexFloorClass.setUpdateUserId(adminUser.getId());
        pcIndexFloorClass.setUpdateUserName(adminUser.getName());

        pcIndexFloorClass.setStatus(PcIndexFloorClass.STATUS_2);

        ServiceResult<Boolean> serviceResult = pcIndexFloorClassService
            .savePcIndexFloorClass(pcIndexFloorClass);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("pcIndexFloorClass", pcIndexFloorClass);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/pcindex/floorclass/pcindexfloorclassadd";
            }
        }
        return "redirect:/admin/pcindex/floorclass";
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(int pcIndexFloorClassId, Map<String, Object> dataMap) {
        ServiceResult<PcIndexFloorClass> serviceResult = pcIndexFloorClassService
            .getPcIndexFloorClassById(pcIndexFloorClassId);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "admin/pcindex/floorclass/pcindexfloorclasslist";
            }
        }
        PcIndexFloorClass pcIndexFloorClass = serviceResult.getResult();

        Map<String, String> queryMap = new HashMap<String, String>();
        ServiceResult<List<PcIndexFloor>> floorResult = pcIndexFloorService
            .getPcIndexFloors(queryMap, null);
        dataMap.put("floors", floorResult.getResult());

        dataMap.put("pcIndexFloorClass", pcIndexFloorClass);
        return "admin/pcindex/floorclass/pcindexfloorclassedit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(PcIndexFloorClass pcIndexFloorClass, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        pcIndexFloorClass.setUpdateUserId(adminUser.getId());
        pcIndexFloorClass.setUpdateUserName(adminUser.getName());

        ServiceResult<Boolean> serviceResult = pcIndexFloorClassService
            .updatePcIndexFloorClass(pcIndexFloorClass);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("pcIndexFloorClass", pcIndexFloorClass);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/pcindex/floorclass/pcindexfloorclassedit";
            }
        }
        return "redirect:/admin/pcindex/floorclass";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> delete(HttpServletRequest request,
                                                        @RequestParam("id") Integer id) {

        ServiceResult<PcIndexFloorClass> pcIndexFloorClassResult = pcIndexFloorClassService
            .getPcIndexFloorClassById(id);
        if (!pcIndexFloorClassResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(pcIndexFloorClassResult.getMessage());
        }
        if (pcIndexFloorClassResult.getResult() == null) {
            return new HttpJsonResult<Boolean>("楼层分类信息获取失败");
        }
        if (pcIndexFloorClassResult.getResult().getStatus().equals(PcIndexFloorClass.STATUS_1)) {
            return new HttpJsonResult<Boolean>("正在使用的楼层分类不能删除");
        }

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        ServiceResult<Boolean> serviceResult = pcIndexFloorClassService.deletePcIndexFloorClass(id);
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
        PcIndexFloorClass pcIndexFloorClass = new PcIndexFloorClass();
        pcIndexFloorClass.setId(id);
        pcIndexFloorClass.setStatus(PcIndexFloorClass.STATUS_1);
        pcIndexFloorClass.setUpdateUserId(adminUser.getId());
        pcIndexFloorClass.setUpdateUserName(adminUser.getName());
        ServiceResult<Boolean> serviceResult = pcIndexFloorClassService
            .updatePcIndexFloorClass(pcIndexFloorClass);
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
        PcIndexFloorClass pcIndexFloorClass = new PcIndexFloorClass();
        pcIndexFloorClass.setId(id);
        pcIndexFloorClass.setStatus(PcIndexFloorClass.STATUS_2);
        pcIndexFloorClass.setUpdateUserId(adminUser.getId());
        pcIndexFloorClass.setUpdateUserName(adminUser.getName());
        ServiceResult<Boolean> serviceResult = pcIndexFloorClassService
            .updatePcIndexFloorClass(pcIndexFloorClass);
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

        PcIndexFloorClass pcIndexFloorClass = new PcIndexFloorClass();
        pcIndexFloorClass.setId(id);
        pcIndexFloorClass.setStatus(PcIndexFloorClass.STATUS_3);
        pcIndexFloorClass.setUpdateUserId(adminUser.getId());
        pcIndexFloorClass.setUpdateUserName(adminUser.getName());
        ServiceResult<Boolean> serviceResult = pcIndexFloorClassService
            .updatePcIndexFloorClass(pcIndexFloorClass);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

}
