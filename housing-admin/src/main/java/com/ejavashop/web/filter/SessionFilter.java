//package com.ejavashop.web.filter;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.log4j.Logger;
//import org.springframework.context.ApplicationContext;
//import org.springframework.util.Assert;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import com.ejavashop.core.WebUtil;
//import com.ejavashop.entity.system.SystemAdmin;
//import com.ejavashop.entity.system.SystemResources;
//import com.ejavashop.service.system.ISystemResourcesRolesService;
//import com.ejavashop.web.util.WebAdminSession;
//
///**
// * session过滤器
// *                       
// * @Filename: SessionFilter.java
// * @Version: 1.0
// * @Author: 陈万海
// * @Email: chenwanhai@sina.com
// *
// */
//public class SessionFilter implements Filter {
//    Logger log = Logger.getLogger(this.getClass());
//
//    @Override
//    public void destroy() {
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequset, ServletResponse servletResonpse,
//                         FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequset;
//        HttpServletResponse response = (HttpServletResponse) servletResonpse;
//        SystemAdmin admin = WebAdminSession.getAdminUser(request);
//
//        response.setHeader("Pragma", "no-cache");
//        response.addHeader("Cache-Control", "must-revalidate");
//        response.addHeader("Cache-Control", "no-cache");
//        response.addHeader("Cache-Control", "no-store");
//        response.setDateHeader("Expires", 0);
//
//        String uri = request.getServletPath();
//        Map<String, String> queryMap = WebUtil.getParamMap(request);
//
//        //此项目没有规定请求的扩展名,所以只排除特定的uri
//        if (!uri.equals("/admin/login.html") && !uri.equals("/admin/doLogin")) {
//            if (admin == null) {
//                response.setContentType("text/html;charset=utf-8");
//                response.getWriter()
//                    .print("<script>top.window.location.href='" + WebUtil.getURL(request)
//                           + "/admin/login.html'</script>");
//            } else {
//                //生成菜单下的按钮
//                if ("menu".equals(queryMap.get("type"))) {
//                    String id = queryMap.get("id");
//                    Assert.notNull(id);
//                    //spring上下文
//                    ApplicationContext context = WebApplicationContextUtils
//                            .getRequiredWebApplicationContext(request.getSession().getServletContext());
//                    ISystemResourcesRolesService serv = (ISystemResourcesRolesService) context
//                            .getBean("systemResourcesRolesService");
//
//                    //此资源下的有权限的子资源
//                    List<SystemResources> list = serv.getResourceByPid(Integer.valueOf(id),
//                            WebAdminSession.getAdminUser(request).getRoleId(),
//                            SystemResources.SCOPE_ADMIN);
//                    log.info("菜单uri:[" + uri + "] 此资源下有权限的子资源数为:[" + list.size() + "]");
//
//                    //资源按钮
//                    StringBuffer sb = new StringBuffer();
//                    for (SystemResources res : list) {
//                        sb.append("<a id=\"" + res.getResId() + "\"")
//                                .append(" href=\"" + res.getUrl() + "\"")
//                                .append(" class=\"easyui-linkbutton\"")
//                                .append(" iconCls=\"" + res.getResIcon() + "\" plain=\"true\"")
//                                .append(">" + res.getContent() + "</a>");
//                    }
//                    //只放到当次请求
//                    request.getSession().setAttribute("buttons", sb.toString());
//                }
//                chain.doFilter(servletRequset, servletResonpse);
//            }
//        } else {
//            chain.doFilter(servletRequset, servletResonpse);
//        }
//    }
//
//    @Override
//    public void init(FilterConfig arg0) throws ServletException {
//
//    }
//
//}
