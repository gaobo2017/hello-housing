package com.ejavashop.web.controller.system;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.Md5;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.system.ISystemAdminService;
import com.ejavashop.service.system.ISystemRolesService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;

/**
 * admin管理controller
 *
 * @Filename: AdminUserController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/system/adminuser")
@Scope("prototype")
public class AdminUserController extends BaseController {
    Logger                      log = Logger.getLogger(this.getClass());
    @Resource(name = "systemAdminService")
    private ISystemAdminService systemAdminService;
    @Resource
    private ISystemRolesService rolesService;

    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String adminUser(HttpServletRequest request,
                            Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        dataMap.put("queryMap", queryMap);
        return "admin/system/adminuser/list";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<SystemAdmin>> list(HttpServletRequest request,
                                                                Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<SystemAdmin>> serviceResult = systemAdminService.page(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<SystemAdmin>> jsonResult = new HttpJsonResult<List<SystemAdmin>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    @RequestMapping(value = "/add")
    public String add(HttpServletRequest request, Map<String, Object> dataMap) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "1");
        dataMap.put("roles", rolesService.page(map, null).getResult());
        return "admin/system/adminuser/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(HttpServletRequest request, Map<String, Object> dataMap, Integer id) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "1");

        dataMap.put("roles", rolesService.page(map, null).getResult());
        dataMap.put("admin", systemAdminService.getSystemAdminById(id).getResult());

        return "admin/system/adminuser/add";
    }

    /**
     * 冻结管理员账号
     * @param request
     * @param response
     * @param id
     */
    @RequestMapping(value = "/freeze")
    public void freeze(HttpServletRequest request, HttpServletResponse response, Integer id) {
        response.setContentType("text/plain;charset=utf-8");
        PrintWriter pw = null;
        String msg = "";
        try {
            SystemAdmin admin = systemAdminService.getSystemAdminById(id).getResult();
            admin.setStatus(ConstantsEJS.SYSTEM_ADMIN_STATUS_FREEZE);
            systemAdminService.updateSystemAdmin(admin);
            pw = response.getWriter();
            msg = "账号[" + admin.getName() + "]已被冻结";
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        pw.print(msg);
    }

    @RequestMapping(value = "/del")
    public void del(HttpServletRequest request, HttpServletResponse response, Integer id) {
        response.setContentType("text/plain;charset=utf-8");
        PrintWriter pw = null;
        String msg = "";
        try {
            SystemAdmin admin = systemAdminService.getSystemAdminById(id).getResult();
            admin.setStatus(ConstantsEJS.SYSTEM_ADMIN_STATUS_DEL);
            systemAdminService.updateSystemAdmin(admin);
            pw = response.getWriter();
            msg = "已删除此账号,此账号将无法继续登录管理系统";
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        pw.print(msg);
    }

    /**
     * 保存
     * @param request
     * @param response
     * @param admin
     * @param dataMap
     */
    @RequestMapping(value = "/save")
    public void save(HttpServletRequest request, HttpServletResponse response, SystemAdmin admin,
                     Map<String, Object> dataMap) {
        response.setContentType("text/plain;charset=utf-8");
        ServiceResult<Integer> serviceResult = null;
        String msg = "";
        PrintWriter pw = null;

        try {
            if (admin.getId() == null || admin.getId().intValue() == 0) {
                admin.setCreateTime(new Date());
                admin.setCreateUser(WebAdminSession.getAdminUser(request).getId());
                //初始密码
                admin.setPassword(Md5.getMd5String("123456"));
                serviceResult = systemAdminService.saveSystemAdmin(admin);
            } else {
                if (null != admin.getPassword() && !"".equals(admin.getPassword()))
                    admin.setPassword(Md5.getMd5String(admin.getPassword()));
                serviceResult = systemAdminService.updateSystemAdmin(admin);
            }
            if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    throw new BusinessException(serviceResult.getMessage());
                }
            }
            pw = response.getWriter();
            msg = serviceResult.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        pw.print(msg);
    }

    /**
     * 修改密码界面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "editpwd", method = { RequestMethod.GET })
    public String updatePassword(Map<String, Object> dataMap) throws Exception {
        return "admin/system/adminuser/editpwd";
    }

    /**
     * 修改密码
     * @param config
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "editpwd/update", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> configUpdate(HttpServletRequest request,
                                                              Map<String, Object> dataMap) {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String newPasswordCfm = request.getParameter("newPasswordCfm");
        if (StringUtil.isEmpty(oldPassword, true) || StringUtil.isEmpty(newPassword, true)
            || StringUtil.isEmpty(newPasswordCfm, true)) {
            return new HttpJsonResult<Boolean>("密码不能为空，请重新输入！");
        }
        if (!newPassword.equals(newPasswordCfm)) {
            return new HttpJsonResult<Boolean>("新密码与确认密码不一致，请重新输入！");
        }
        if (oldPassword.equals(newPassword)) {
            return new HttpJsonResult<Boolean>("新密码与旧密码不能相同，请重新输入！");
        }

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);

        ServiceResult<SystemAdmin> systemAdminRlt = systemAdminService
            .getSystemAdminById(adminUser.getId());
        if (!systemAdminRlt.getSuccess()) {
            return new HttpJsonResult<Boolean>(systemAdminRlt.getMessage());
        }
        SystemAdmin adminDb = systemAdminRlt.getResult();

        String oldPasswordMd5 = Md5.getMd5String(oldPassword);
        if (!oldPasswordMd5.equals(adminDb.getPassword())) {
            return new HttpJsonResult<Boolean>("旧密码输入错误，请重新输入！");
        }

        SystemAdmin adminNew = new SystemAdmin();
        adminNew.setId(adminUser.getId());
        adminNew.setPassword(Md5.getMd5String(newPassword));

        ServiceResult<Integer> serviceResult = systemAdminService.updateSystemAdmin(adminNew);
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
}
