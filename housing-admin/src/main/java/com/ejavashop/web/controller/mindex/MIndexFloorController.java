package com.ejavashop.web.controller.mindex;

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
import com.ejavashop.entity.shopm.MIndexFloor;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.mindex.IMIndexService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 移动端首页楼层管理controller
 *                       
 * @Filename: MIndexFloorController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/mindex/floor")
public class MIndexFloorController extends BaseController {

    @Resource
    private IMIndexService mIndexService;

    /**
     * 移动端首页楼层列表页
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/mindex/floor/floorlist";
    }

    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<MIndexFloor>> list(HttpServletRequest request,
                                                                HttpServletResponse response,
                                                                Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<MIndexFloor>> serviceResult = mIndexService.getMIndexFloors(queryMap,
            pager);
        pager = serviceResult.getPager();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<MIndexFloor> list = serviceResult.getResult();

        HttpJsonResult<List<MIndexFloor>> jsonResult = new HttpJsonResult<List<MIndexFloor>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request, Map<String, Object> dataMap) {
        return "admin/mindex/floor/flooradd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(MIndexFloor mIndexFloor, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        Integer userId = adminUser.getId();
        mIndexFloor.setCreateUserId(userId);
        mIndexFloor.setCreateUserName(adminUser.getName());
        mIndexFloor.setUpdateUserId(adminUser.getId());
        mIndexFloor.setUpdateUserName(adminUser.getName());

        mIndexFloor.setStatus(MIndexFloor.STATUS_0);

        ServiceResult<Boolean> serviceResult = mIndexService.saveMIndexFloor(mIndexFloor);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("mIndexFloor", mIndexFloor);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/mindex/floor/flooradd";
            }
        }
        return "redirect:/admin/mindex/floor";
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(int mIndexFloorId, Map<String, Object> dataMap) {
        ServiceResult<MIndexFloor> serviceResult = mIndexService.getMIndexFloorById(mIndexFloorId);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "admin/mindex/floor/floorlist";
            }
        }
        MIndexFloor mIndexFloor = serviceResult.getResult();

        dataMap.put("mIndexFloor", mIndexFloor);
        return "admin/mindex/floor/flooredit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(MIndexFloor mIndexFloor, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        mIndexFloor.setUpdateUserId(adminUser.getId());
        mIndexFloor.setUpdateUserName(adminUser.getName());

        ServiceResult<Boolean> serviceResult = mIndexService.updateMIndexFloor(mIndexFloor);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("mIndexFloor", mIndexFloor);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/mindex/floor/flooredit";
            }
        }
        return "redirect:/admin/mindex/floor";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> delete(HttpServletRequest request,
                                                        @RequestParam("id") Integer id) {

        ServiceResult<MIndexFloor> mIndexFloorResult = mIndexService.getMIndexFloorById(id);
        if (!mIndexFloorResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(mIndexFloorResult.getMessage());
        }
        if (mIndexFloorResult.getResult() == null) {
            return new HttpJsonResult<Boolean>("楼层信息获取失败");
        }
        if (mIndexFloorResult.getResult().getStatus().equals(MIndexFloor.STATUS_1)) {
            return new HttpJsonResult<Boolean>("正在使用的楼层不能删除");
        }

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        ServiceResult<Boolean> serviceResult = mIndexService.deleteMIndexFloor(id);
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
        MIndexFloor mIndexFloor = new MIndexFloor();
        mIndexFloor.setId(id);
        mIndexFloor.setStatus(MIndexFloor.STATUS_1);
        mIndexFloor.setUpdateUserId(adminUser.getId());
        mIndexFloor.setUpdateUserName(adminUser.getName());
        ServiceResult<Boolean> serviceResult = mIndexService.updateMIndexFloor(mIndexFloor);
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

        MIndexFloor mIndexFloor = new MIndexFloor();
        mIndexFloor.setId(id);
        mIndexFloor.setStatus(MIndexFloor.STATUS_0);
        mIndexFloor.setUpdateUserId(adminUser.getId());
        mIndexFloor.setUpdateUserName(adminUser.getName());
        ServiceResult<Boolean> serviceResult = mIndexService.updateMIndexFloor(mIndexFloor);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }
}
