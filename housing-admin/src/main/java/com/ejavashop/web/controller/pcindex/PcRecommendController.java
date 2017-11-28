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
import com.ejavashop.entity.shopm.pcindex.PcRecommend;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.pcindex.IPcRecommendService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;

/**
 * PC端推荐商品管理controller
 *
 * @Filename: PcRecommendController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/pcindex/recommend")
public class PcRecommendController extends BaseController {

    @Resource
    private IPcRecommendService pcRecommendService;

    /**
     * PC端推荐商品列表页
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/pcindex/recommend/pcrecommendlist";
    }

    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<PcRecommend>> list(HttpServletRequest request,
                                                                HttpServletResponse response,
                                                                Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<PcRecommend>> serviceResult = pcRecommendService
            .getPcRecommends(queryMap, pager);
        pager = serviceResult.getPager();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<PcRecommend> list = serviceResult.getResult();

        HttpJsonResult<List<PcRecommend>> jsonResult = new HttpJsonResult<List<PcRecommend>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request, Map<String, Object> dataMap) {
        return "admin/pcindex/recommend/pcrecommendadd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(PcRecommend pcRecommend, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        Integer userId = adminUser.getId();
        pcRecommend.setCreateUserId(userId);
        pcRecommend.setCreateUserName(adminUser.getName());
        pcRecommend.setUpdateUserId(adminUser.getId());
        pcRecommend.setUpdateUserName(adminUser.getName());

        pcRecommend.setStatus(PcRecommend.STATUS_2);

        ServiceResult<Boolean> serviceResult = pcRecommendService.savePcRecommend(pcRecommend);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("pcRecommend", pcRecommend);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/pcindex/recommend/pcrecommendadd";
            }
        }
        return "redirect:/admin/pcindex/recommend";
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(int pcRecommendId, Map<String, Object> dataMap) {
        ServiceResult<PcRecommend> serviceResult = pcRecommendService
            .getPcRecommendById(pcRecommendId);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "admin/pcindex/recommend/pcrecommendlist";
            }
        }
        PcRecommend pcRecommend = serviceResult.getResult();

        dataMap.put("pcRecommend", pcRecommend);
        return "admin/pcindex/recommend/pcrecommendedit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(PcRecommend pcRecommend, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        pcRecommend.setUpdateUserId(adminUser.getId());
        pcRecommend.setUpdateUserName(adminUser.getName());

        ServiceResult<Boolean> serviceResult = pcRecommendService.updatePcRecommend(pcRecommend);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("pcRecommend", pcRecommend);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/pcindex/recommend/pcrecommendedit";
            }
        }
        return "redirect:/admin/pcindex/recommend";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> delete(HttpServletRequest request,
                                                        @RequestParam("id") Integer id) {

        ServiceResult<PcRecommend> pcRecommendResult = pcRecommendService.getPcRecommendById(id);
        if (!pcRecommendResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(pcRecommendResult.getMessage());
        }
        if (pcRecommendResult.getResult() == null) {
            return new HttpJsonResult<Boolean>("推荐商品信息获取失败");
        }
        if (pcRecommendResult.getResult().getStatus().equals(PcRecommend.STATUS_1)) {
            return new HttpJsonResult<Boolean>("正在使用的推荐商品不能删除");
        }

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        ServiceResult<Boolean> serviceResult = pcRecommendService.deletePcRecommend(id);
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
        PcRecommend pcRecommend = new PcRecommend();
        pcRecommend.setId(id);
        pcRecommend.setStatus(PcRecommend.STATUS_1);
        pcRecommend.setUpdateUserId(adminUser.getId());
        pcRecommend.setUpdateUserName(adminUser.getName());
        ServiceResult<Boolean> serviceResult = pcRecommendService.updatePcRecommend(pcRecommend);
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
        PcRecommend pcRecommend = new PcRecommend();
        pcRecommend.setId(id);
        pcRecommend.setStatus(PcRecommend.STATUS_2);
        pcRecommend.setUpdateUserId(adminUser.getId());
        pcRecommend.setUpdateUserName(adminUser.getName());
        ServiceResult<Boolean> serviceResult = pcRecommendService.updatePcRecommend(pcRecommend);
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

        PcRecommend pcRecommend = new PcRecommend();
        pcRecommend.setId(id);
        pcRecommend.setStatus(PcRecommend.STATUS_3);
        pcRecommend.setUpdateUserId(adminUser.getId());
        pcRecommend.setUpdateUserName(adminUser.getName());
        ServiceResult<Boolean> serviceResult = pcRecommendService.updatePcRecommend(pcRecommend);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

}
