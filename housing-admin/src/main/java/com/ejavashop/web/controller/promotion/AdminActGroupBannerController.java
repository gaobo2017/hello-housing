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
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.promotion.group.ActGroupBanner;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.promotion.IActGroupBannerService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.UploadUtil;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 团购首页轮播图管理
 *                       
 * @Filename: AdminActGroupBannerController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
@RequestMapping(value = "admin/actgroupbanner/banner")
public class AdminActGroupBannerController extends BaseController {

    @Resource
    private IActGroupBannerService actGroupBannerService;

    /**
     * PC端首页轮播图列表页
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/promotion/group/groupbannerlist";
    }

    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ActGroupBanner>> list(HttpServletRequest request,
                                                                   HttpServletResponse response,
                                                                   Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<ActGroupBanner>> serviceResult = actGroupBannerService
            .getActGroupBanners(queryMap, pager);
        pager = serviceResult.getPager();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<ActGroupBanner> list = serviceResult.getResult();

        HttpJsonResult<List<ActGroupBanner>> jsonResult = new HttpJsonResult<List<ActGroupBanner>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request, Map<String, Object> dataMap) {
        return "admin/promotion/group/groupbanneradd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(ActGroupBanner actGroupBanner, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        Integer userId = adminUser.getId();
        actGroupBanner.setCreateUserId(userId);
        actGroupBanner.setCreateUserName(adminUser.getName());
        actGroupBanner.setUpdateUserId(adminUser.getId());
        actGroupBanner.setUpdateUserName(adminUser.getName());

        actGroupBanner.setState(ConstantsEJS.YES_NO_0);

        // 上传图片
        String image = UploadUtil.getInstance().advUploadFile2ImageServer("imageFile", request);
        if (image != null && !"".equals(image)) {
            actGroupBanner.setImage(image);
        }

        ServiceResult<Boolean> serviceResult = actGroupBannerService
            .saveActGroupBanner(actGroupBanner);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("actGroupBanner", actGroupBanner);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/group/groupbanneradd";
            }
        }
        return "redirect:/admin/actgroupbanner/banner";
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(int id, Map<String, Object> dataMap) {
        ServiceResult<ActGroupBanner> serviceResult = actGroupBannerService
            .getActGroupBannerById(id);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/group/groupbannerlist";
            }
        }
        ActGroupBanner actGroupBanner = serviceResult.getResult();

        dataMap.put("actGroupBanner", actGroupBanner);
        return "admin/promotion/group/groupbanneredit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(ActGroupBanner actGroupBanner, HttpServletRequest request,
                         Map<String, Object> dataMap) {
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        actGroupBanner.setUpdateUserId(adminUser.getId());
        actGroupBanner.setUpdateUserName(adminUser.getName());

        // 上传图片
        String image = UploadUtil.getInstance().advUploadFile2ImageServer("imageFile", request);
        if (image != null && !"".equals(image)) {
            actGroupBanner.setImage(image);
        }

        ServiceResult<Boolean> serviceResult = actGroupBannerService
            .updateActGroupBanner(actGroupBanner);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("actGroupBanner", actGroupBanner);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/group/groupbanneredit";
            }
        }
        return "redirect:/admin/actgroupbanner/banner";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> delete(HttpServletRequest request,
                                                        @RequestParam("id") Integer id) {

        ServiceResult<ActGroupBanner> actGroupBannerResult = actGroupBannerService
            .getActGroupBannerById(id);
        if (!actGroupBannerResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(actGroupBannerResult.getMessage());
        }
        if (actGroupBannerResult.getResult() == null) {
            return new HttpJsonResult<Boolean>("轮播图信息获取失败");
        }
        if (actGroupBannerResult.getResult().getState().equals(ConstantsEJS.YES_NO_1)) {
            return new HttpJsonResult<Boolean>("正在使用的轮播图不能删除");
        }

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        ServiceResult<Boolean> serviceResult = actGroupBannerService.deleteActGroupBanner(id);
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
        ActGroupBanner actGroupBanner = new ActGroupBanner();
        actGroupBanner.setId(id);
        actGroupBanner.setState(ConstantsEJS.YES_NO_1);
        actGroupBanner.setUpdateUserId(adminUser.getId());
        actGroupBanner.setUpdateUserName(adminUser.getName());
        ServiceResult<Boolean> serviceResult = actGroupBannerService
            .updateActGroupBanner(actGroupBanner);
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

        ActGroupBanner actGroupBanner = new ActGroupBanner();
        actGroupBanner.setId(id);
        actGroupBanner.setState(ConstantsEJS.YES_NO_0);
        actGroupBanner.setUpdateUserId(adminUser.getId());
        actGroupBanner.setUpdateUserName(adminUser.getName());
        ServiceResult<Boolean> serviceResult = actGroupBannerService
            .updateActGroupBanner(actGroupBanner);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

}
