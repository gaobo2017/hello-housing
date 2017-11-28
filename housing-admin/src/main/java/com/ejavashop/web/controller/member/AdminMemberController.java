package com.ejavashop.web.controller.member;

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
import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.member.MemberAddress;
import com.ejavashop.entity.member.MemberBalanceLogs;
import com.ejavashop.entity.member.MemberGradeIntegralLogs;
import com.ejavashop.entity.member.MemberGradeUpLogs;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.member.IMemberAddressService;
import com.ejavashop.service.member.IMemberBalanceLogsService;
import com.ejavashop.service.member.IMemberService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 会员管理controller
 *
 * @Filename: AdminMemberController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/member/member")
public class AdminMemberController extends BaseController {

    @Resource(name = "memberService")
    private IMemberService            memberService;

    @Resource(name = "memberBalanceLogsService")
    private IMemberBalanceLogsService memberBalanceLogsService;

    @Resource(name = "memberAddressService")
    private IMemberAddressService     memberAddressService;

    /**
     * 会员管理页面初始化controller接口
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/member/member/memberlist";
    }

    /**
     * 会员管理页面查询按钮controller接口
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Member>> list(HttpServletRequest request,
                                                           HttpServletResponse response,
                                                           Map<String, Object> dataMap) {

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<Member>> serviceResult = memberService.getMembers(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<Member>> jsonResult = new HttpJsonResult<List<Member>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    /**
     * 会员管理页面经验值积分制操作controller接口
     * @param memberGradeIntegralLogs
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "valueopt", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> valueOpt(MemberGradeIntegralLogs memberGradeIntegralLogs,
                                                          HttpServletRequest request) {

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        //  参数校验
        if (memberGradeIntegralLogs.getMemberId() == null) {
            jsonResult.setMessage("会员ID不能为空，请重试！");
            return jsonResult;
        } else if (memberGradeIntegralLogs.getValue() == null) {
            jsonResult.setMessage("经验值或积分值不能为空，请重试！");
            return jsonResult;
        } else if (memberGradeIntegralLogs.getOptType() == null) {
            jsonResult.setMessage("动作类型不能为空，请重试！");
            return jsonResult;
        } else if (memberGradeIntegralLogs.getType() == null) {
            jsonResult.setMessage("操作类型不能为空，请重试！");
            return jsonResult;
        }

        ServiceResult<Boolean> serviceResult = memberService
            .updateMemberValue(memberGradeIntegralLogs);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult.setMessage(serviceResult.getMessage());
                return jsonResult;
            }
        }
        jsonResult.setData(true);
        return jsonResult;
    }

    /**
     * 会员管理页面经验值积分制操作controller接口
     * @param memberGradeIntegralLogs
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "balanceopt", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> balanceOpt(MemberBalanceLogs memberBalanceLogs,
                                                            HttpServletRequest request) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        //  参数校验
        if (memberBalanceLogs.getMemberId() == null) {
            jsonResult.setMessage("会员ID不能为空，请重试！");
            return jsonResult;
        } else if (memberBalanceLogs.getMoney() == null) {
            jsonResult.setMessage("变更金额不能为空，请重试！");
            return jsonResult;
        } else if (memberBalanceLogs.getState() == null) {
            jsonResult.setMessage("动作类型不能为空，请重试！");
            return jsonResult;
        }
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        memberBalanceLogs.setOptId(adminUser.getId());
        memberBalanceLogs.setOptName(adminUser.getName());

        ServiceResult<Boolean> serviceResult = memberService.updateMemberBalance(memberBalanceLogs);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult.setMessage(serviceResult.getMessage());
                return jsonResult;
            }
        }
        jsonResult.setData(true);
        return jsonResult;
    }

    /**
     * 会员管理页面升级日志按钮controller接口
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "uploglist", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<MemberGradeUpLogs>> upLogList(HttpServletRequest request,
                                                                           HttpServletResponse response,
                                                                           Map<String, Object> dataMap) {
        String memberIdStr = request.getParameter("memberId");
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<MemberGradeUpLogs>> serviceResult = memberService
            .getMemberGradeUpLogs(ConvertUtil.toInt(memberIdStr, 0), pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<MemberGradeUpLogs>> jsonResult = new HttpJsonResult<List<MemberGradeUpLogs>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    /**
     * 会员管理页面经验值日志和积分值日志按钮controller接口
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "grdIntloglist", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<MemberGradeIntegralLogs>> grdIntLogList(HttpServletRequest request,
                                                                                     HttpServletResponse response,
                                                                                     Map<String, Object> dataMap) {
        String memberIdStr = request.getParameter("memberId");
        // 类型：1、经验值；2、积分
        String typeStr = request.getParameter("type");
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<MemberGradeIntegralLogs>> serviceResult = memberService
            .getMemberGradeIntegralLogs(ConvertUtil.toInt(memberIdStr, 0),
                ConvertUtil.toInt(typeStr, 0), pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<MemberGradeIntegralLogs>> jsonResult = new HttpJsonResult<List<MemberGradeIntegralLogs>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    /**
     * 会员管理页面经验值日志和积分值日志按钮controller接口
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "balanceloglist", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<MemberBalanceLogs>> balanceLogList(HttpServletRequest request,
                                                                                HttpServletResponse response,
                                                                                Map<String, Object> dataMap) {
        String memberIdStr = request.getParameter("memberId");
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<MemberBalanceLogs>> serviceResult = memberBalanceLogsService
            .getMemberBalanceLogs(ConvertUtil.toInt(memberIdStr, 0), pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<MemberBalanceLogs>> jsonResult = new HttpJsonResult<List<MemberBalanceLogs>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    /**
     * 会员管理页面收货地址按钮controller接口
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "addresslist", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<MemberAddress>> addressList(HttpServletRequest request,
                                                                         HttpServletResponse response,
                                                                         Map<String, Object> dataMap) {
        String memberIdStr = request.getParameter("memberId");
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<MemberAddress>> serviceResult = memberAddressService
            .getMemberAddresses(ConvertUtil.toInt(memberIdStr, 0), pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<MemberAddress>> jsonResult = new HttpJsonResult<List<MemberAddress>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }
}
