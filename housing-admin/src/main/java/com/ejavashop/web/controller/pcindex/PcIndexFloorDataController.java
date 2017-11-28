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
import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.shopm.pcindex.PcIndexFloorClass;
import com.ejavashop.entity.shopm.pcindex.PcIndexFloorData;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.pcindex.IPcIndexFloorClassService;
import com.ejavashop.service.pcindex.IPcIndexFloorDataService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.UploadUtil;
import com.ejavashop.web.util.WebAdminSession;

/**
 * PC端首页楼层分类数据管理controller
 *                       
 * @Filename: PcIndexFloorDataController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/pcindex/floordata")
public class PcIndexFloorDataController extends BaseController {

    @Resource
    private IPcIndexFloorDataService  pcIndexFloorDataService;
    @Resource
    private IPcIndexFloorClassService pcIndexFloorClassService;

    /**
     * PC端首页楼层分类数据列表页
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) {

        Map<String, String> queryMap = new HashMap<String, String>();
        ServiceResult<List<PcIndexFloorClass>> serviceResult = pcIndexFloorClassService
            .getPcIndexFloorClasss(queryMap, null);
        dataMap.put("floorClasss", serviceResult.getResult());

        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/pcindex/floordata/pcindexfloordatalist";
    }

    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<PcIndexFloorData>> list(HttpServletRequest request,
                                                                     HttpServletResponse response,
                                                                     Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<PcIndexFloorData>> serviceResult = pcIndexFloorDataService
            .getPcIndexFloorDatas(queryMap, pager);
        pager = serviceResult.getPager();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<PcIndexFloorData> list = serviceResult.getResult();

        HttpJsonResult<List<PcIndexFloorData>> jsonResult = new HttpJsonResult<List<PcIndexFloorData>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    /**
     * 根据楼层分类ID取楼层分类数据
     * @param request
     * @param response
     * @param dataMap
     * @param floorId
     * @return
     */
    @RequestMapping(value = "listbyfloorclass", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<PcIndexFloorData>> listByFloorClass(HttpServletRequest request,
                                                                                 HttpServletResponse response,
                                                                                 Map<String, Object> dataMap,
                                                                                 Integer floorClassId) {
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("q_floorClassId", floorClassId.toString());

        ServiceResult<List<PcIndexFloorData>> serviceResult = pcIndexFloorDataService
            .getPcIndexFloorDatas(queryMap, null);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<PcIndexFloorData> list = serviceResult.getResult();

        HttpJsonResult<List<PcIndexFloorData>> jsonResult = new HttpJsonResult<List<PcIndexFloorData>>();
        jsonResult.setRows(list);
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request, Map<String, Object> dataMap) {

        Map<String, String> queryMap = new HashMap<String, String>();
        ServiceResult<List<PcIndexFloorClass>> serviceResult = pcIndexFloorClassService
            .getPcIndexFloorClasss(queryMap, null);
        dataMap.put("floorClasss", serviceResult.getResult());

        String floorClassId = request.getParameter("floorClassId");
        if (!StringUtil.isEmpty(floorClassId, true)) {
            dataMap.put("floorClassId", ConvertUtil.toInt(floorClassId, 0));
            dataMap.put("fromClass", "1");
        }

        return "admin/pcindex/floordata/pcindexfloordataadd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(PcIndexFloorData pcIndexFloorData, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        String fromClass = request.getParameter("fromClass");

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        Integer userId = adminUser.getId();
        pcIndexFloorData.setCreateUserId(userId);
        pcIndexFloorData.setCreateUserName(adminUser.getName());
        pcIndexFloorData.setUpdateUserId(adminUser.getId());
        pcIndexFloorData.setUpdateUserName(adminUser.getName());

        if (pcIndexFloorData.getDataType() == PcIndexFloorData.DATA_TYPE_1) {
            pcIndexFloorData.setImage(null);
            pcIndexFloorData.setLinkUrl(null);
        } else if (pcIndexFloorData.getDataType() == PcIndexFloorData.DATA_TYPE_2) {
            // 上传图片
            String image = UploadUtil.getInstance().advUploadFile2ImageServer("imageFile", request);
            if (image != null && !"".equals(image)) {
                pcIndexFloorData.setImage(image);
            }
            pcIndexFloorData.setRefId(0);
        }

        ServiceResult<Boolean> serviceResult = pcIndexFloorDataService
            .savePcIndexFloorData(pcIndexFloorData);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("pcIndexFloorData", pcIndexFloorData);
                dataMap.put("message", serviceResult.getMessage());
                if (fromClass != null && fromClass.equals("1")) {
                    dataMap.put("fromClass", "1");
                }
                return "admin/pcindex/floordata/pcindexfloordataadd";
            }
        }
        // 如果是从楼层分类页跳转回到楼层分类列表
        if (fromClass != null && fromClass.equals("1")) {
            return "redirect:/admin/pcindex/floorclass";
        }
        return "redirect:/admin/pcindex/floordata";
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(int pcIndexFloorDataId, Map<String, Object> dataMap) {
        ServiceResult<PcIndexFloorData> serviceResult = pcIndexFloorDataService
            .getPcIndexFloorDataById(pcIndexFloorDataId);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "admin/pcindex/floordata/pcindexfloordatalist";
            }
        }
        PcIndexFloorData pcIndexFloorData = serviceResult.getResult();

        Map<String, String> queryMap = new HashMap<String, String>();
        ServiceResult<List<PcIndexFloorClass>> classResult = pcIndexFloorClassService
            .getPcIndexFloorClasss(queryMap, null);
        dataMap.put("floorClasss", classResult.getResult());

        dataMap.put("pcIndexFloorData", pcIndexFloorData);
        return "admin/pcindex/floordata/pcindexfloordataedit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(PcIndexFloorData pcIndexFloorData, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        pcIndexFloorData.setUpdateUserId(adminUser.getId());
        pcIndexFloorData.setUpdateUserName(adminUser.getName());

        if (pcIndexFloorData.getDataType() == PcIndexFloorData.DATA_TYPE_1) {
            pcIndexFloorData.setImage(null);
            pcIndexFloorData.setLinkUrl(null);
        } else if (pcIndexFloorData.getDataType() == PcIndexFloorData.DATA_TYPE_2) {
            // 上传图片
            String image = UploadUtil.getInstance().advUploadFile2ImageServer("imageFile", request);
            if (image != null && !"".equals(image)) {
                pcIndexFloorData.setImage(image);
            }
            pcIndexFloorData.setRefId(0);
        }

        ServiceResult<Boolean> serviceResult = pcIndexFloorDataService
            .updatePcIndexFloorData(pcIndexFloorData);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("pcIndexFloorData", pcIndexFloorData);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/pcindex/floordata/pcindexfloordataedit";
            }
        }
        return "redirect:/admin/pcindex/floordata";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> delete(HttpServletRequest request,
                                                        @RequestParam("id") Integer id) {

        ServiceResult<PcIndexFloorData> pcIndexFloorDataResult = pcIndexFloorDataService
            .getPcIndexFloorDataById(id);
        if (!pcIndexFloorDataResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(pcIndexFloorDataResult.getMessage());
        }
        if (pcIndexFloorDataResult.getResult() == null) {
            return new HttpJsonResult<Boolean>("楼层分类数据信息获取失败");
        }

        //        PcIndexFloorData pcIndexFloorData = pcIndexFloorDataResult.getResult();
        //        ServiceResult<PcIndexFloorClass> classResult = pcIndexFloorClassService.getPcIndexFloorClassById(pcIndexFloorData.getFloorClassId());
        //        if (!classResult.getSuccess()) {
        //            return new HttpJsonResult<Boolean>(classResult.getMessage());
        //        }
        //        if (classResult.getResult() == null) {
        //            return new HttpJsonResult<Boolean>("楼层分类信息获取失败");
        //        }
        //        if (classResult.getResult().getStatus().equals(PcIndexFloorClass.STATUS_1)) {
        //            return new HttpJsonResult<Boolean>("数据所属的楼层分类正在使用，不能删除");
        //        }

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        ServiceResult<Boolean> serviceResult = pcIndexFloorDataService.deletePcIndexFloorData(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

}
