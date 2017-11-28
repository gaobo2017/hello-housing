package com.ejavashop.web.controller.system;

import java.util.Enumeration;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.Md5;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.system.ISystemAdminService;
import com.ejavashop.service.system.ISystemResourcesRolesService;
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
@RequestMapping(value = "admin")
public class AdminLoginController extends BaseController {
    Logger                               log = Logger.getLogger(this.getClass());
    @Resource(name = "systemAdminService")
    private ISystemAdminService          systemAdminService;
    @Resource
    private ISystemRolesService          rolesService;
    @Resource
    private ISystemResourcesRolesService systemResourcesRolesService;

    @RequestMapping(value = "/login.html", method = { RequestMethod.GET })
    public String login() throws Exception {
        return "admin/login";
    }

    @RequestMapping(value = "/doLogin", method = { RequestMethod.POST })
    public String doLogin(HttpServletRequest request, HttpServletResponse response,
                          Map<String, Object> dataMap) throws Exception {
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        if (name == null) {
            dataMap.put("message", "用户名不能为空");
            return "admin/login";
        }
        if (password == null) {
            dataMap.put("message", "用户名不能为空");
            return "admin/login";
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name,
            Md5.getMd5String(password).toCharArray());
        // token.setRememberMe(true);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            log.error("账号不存在：{}", e);
            dataMap.put("message", "账号不存在");
            return "admin/login";
        } catch (DisabledAccountException e) {
            log.error("账号未启用：{}", e);
            dataMap.put("message", "账号未启用");
            return "admin/login";
        } catch (IncorrectCredentialsException e) {
            log.error("密码错误：{}", e);
            dataMap.put("message", "账号或密码错误");
            return "admin/login";
        } catch (RuntimeException e) {
            log.error("未知错误,请联系管理员：{}", e);
            dataMap.put("message", "未知错误,请联系管理员");
            return "admin/login";
        }

        //        password = Md5.getMd5String(password);
        //        ServiceResult<SystemAdmin> serviceResult = systemAdminService.getSystemAdminByNamePwd(name,
        //            password);
        //        if (!serviceResult.getSuccess()) {
        //            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
        //                throw new RuntimeException(serviceResult.getMessage());
        //            } else {
        //                return "admin/login";
        //            }
        //        }
        //
        //        SystemAdmin adminUser = serviceResult.getResult();
        SystemAdmin adminUser = (SystemAdmin) subject.getPrincipal();
        //        if (adminUser == null) {
        //            dataMap.put("message", "用户名或者密码错误");
        //            return "admin/login";
        //        }
        if (adminUser.getStatus().intValue() != ConstantsEJS.SYSTEM_ADMIN_STATUS_NORM) {
            dataMap.put("message", "账号停用不能登录");
            return "admin/login";
        }

        WebAdminSession.putAdminUser(request, adminUser);

        //        //资源树
        //        ServiceResult<List<SystemResources>> serviceResult1 = systemResourcesRolesService
        //            .getResourceByRoleId(adminUser.getRoleId(), SystemResources.SCOPE_ADMIN);
        //        if (serviceResult1.getSuccess() && serviceResult1.getResult() != null) {
        //            request.getSession().setAttribute("resourceTree",
        //                createTree(serviceResult1.getResult(), request));
        //        }

        return "redirect:/admin/index.html";
    }

    //    /**
    //     * 查找当前用户角色拥有的菜单权限  并生成左边的tree
    //     * @param listResources
    //     * @return
    //     */
    //    public String createTree(List<SystemResources> listResources, HttpServletRequest request) {
    //        SystemResources parentRes = generateResTree(listResources, new SystemResources(),
    //            ConstantsEJS.SYSTEM_RESOURCE_ROOT);
    //
    //        List<SystemResources> children = parentRes.getChildren();
    //        StringBuilder html = new StringBuilder();
    //        for (SystemResources tr : children) {
    //
    //            log.info("父资源菜单:" + tr.getContent());
    //            html.append("<div title=\"").append(tr.getContent()).append("\" class=\"ra_div\">");
    //
    //            for (SystemResources tbr : tr.getChildren()) {
    //                log.info("子资源菜单:" + tbr.getContent());
    //
    //                //                html.append(
    //                //                    "<a id='" + tbr.getId() + "' href=\"javascript:void(0);\" onclick=\"addTab('")
    //                //                    .append(tbr.getContent()).append("', '").append(WebUtil.getURL(request))
    //                //                    .append(tbr.getUrl().indexOf("?") > 0 ? tbr.getUrl() + "&id=" + tbr.getId()
    //                //                        : tr.getUrl() + "?id=" + tbr.getId())
    //                //                    .append("')\">").append(tbr.getContent()).append("</a>");
    //                // 用逗号分隔资源表的url字段，该字段可存储多个url，并用英文逗号（，）分隔
    //                String[] split = tbr.getUrl().split(",");
    //                html.append(
    //                    "<a id='" + tbr.getId() + "' href=\"javascript:void(0);\" onclick=\"addTab('")
    //                    .append(tbr.getContent()).append("', '").append(WebUtil.getURL(request))
    //                    .append(split[0]).append("')\">").append(tbr.getContent()).append("</a>");
    //            }
    //            html.append("</div>");
    //        }
    //        return html.toString();
    //    }
    //
    //    /**
    //     * 递归 treeGrid节点
    //     * @param listResources
    //     * @param parentRes
    //     * @param id
    //     * @return
    //     */
    //    private SystemResources generateResTree(List<SystemResources> listResources,
    //                                            SystemResources parentRes, Integer id) {
    //        for (SystemResources res : listResources) {
    //            if (res.getPid().intValue() == id.intValue()) {
    //                parentRes.getChildren().add(res);
    //                generateResTree(listResources, res, res.getId());
    //            }
    //        }
    //        return parentRes;
    //    }

    @RequestMapping(value = "/index.html", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        // 如果没有登录，去登录页
        if (!subject.isAuthenticated()) {
            response.getWriter().print("<script>top.window.location.href='"
                                       + request.getContextPath() + "/admin/login.html'</script>");
            return null;
        }
        return "admin/index";
    }

    @RequestMapping(value = "/exit", method = { RequestMethod.GET })
    public String exit(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        HttpSession session = request.getSession();
        Enumeration<?> em = session.getAttributeNames();
        //清空session
        while (em.hasMoreElements()) {
            session.removeAttribute(em.nextElement().toString());
        }
        //清除cookie
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
        }
        //重定向
        return "redirect:/admin/login";
    }

    /**
     * 访问无权限URL时跳转路径
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/unauth.html", method = { RequestMethod.GET })
    public String unAuth() throws Exception {
        return "admin/unauth";
    }

}
