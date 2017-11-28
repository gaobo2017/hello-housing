package com.ejavashop.web.controller.promotion;

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
import com.ejavashop.core.HttpJsonResultForAjax;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.promotion.group.ActGroupType;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.promotion.IActGroupService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 团购类型相关处理
 *                       
 * @Filename: AdminActGroupTypeController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
@RequestMapping(value = "admin/promotion/grouptype")
public class AdminActGroupTypeController extends BaseController {

    @Resource
    private IActGroupService actGroupService;

    /**
     * 团购类型列表页
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/promotion/group/grouptypelist";
    }

    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ActGroupType>> list(HttpServletRequest request,
                                                                 HttpServletResponse response,
                                                                 Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<ActGroupType>> serviceResult = actGroupService.getActGroupTypes(
            queryMap, pager);
        pager = serviceResult.getPager();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<ActGroupType> list = serviceResult.getResult();

        HttpJsonResult<List<ActGroupType>> jsonResult = new HttpJsonResult<List<ActGroupType>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(Map<String, Object> dataMap) {
        return "admin/promotion/group/grouptypeadd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(ActGroupType actGroupType, HttpServletRequest request,
                         Map<String, Object> dataMap) {
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        actGroupType.setCreateId(user.getId());
        actGroupType.setCreateName(user.getName());

        actGroupType.setState(ActGroupType.ACTGROUPTYPE_STATE_0);

        ServiceResult<Integer> serviceResult = actGroupService.saveActGroupType(actGroupType);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("actGroupType", actGroupType);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/group/grouptypeadd";
            }
        }
        return "redirect:";
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(Integer id, Map<String, Object> dataMap) {
        ServiceResult<ActGroupType> serviceResult = actGroupService.getActGroupTypeById(id);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            }
        }
        dataMap.put("actGroupType", serviceResult.getResult());
        return "admin/promotion/group/grouptypeedit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(ActGroupType actGroupType, HttpServletRequest request,
                         Map<String, Object> dataMap) {
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        actGroupType.setUpdateId(user.getId());
        actGroupType.setUpdateName(user.getName());

        ServiceResult<Integer> serviceResult = actGroupService.updateActGroupType(actGroupType);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("actGroupType", actGroupType);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/group/grouptypeadd";
            }
        }
        return "redirect:";
    }

    /**
     * 删除团购分类
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> del(HttpServletRequest request,
                                                    @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResultForAjax<Object>();
        ServiceResult<Boolean> serviceResult = actGroupService.delActGroupType(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /**
     * 团购分类启用
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "auditYes", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> auditYes(HttpServletRequest request,
                                                         @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResultForAjax<Object>();
        ServiceResult<Boolean> serviceResult = actGroupService.auditYesActGroupType(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /**
     * 团购分类停用
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "auditNo", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> auditNo(HttpServletRequest request,
                                                        @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResultForAjax<Object>();
        ServiceResult<Boolean> serviceResult = actGroupService.auditNoActGroupType(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

}
