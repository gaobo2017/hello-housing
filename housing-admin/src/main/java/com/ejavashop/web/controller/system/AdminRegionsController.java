package com.ejavashop.web.controller.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.entity.system.Regions;
import com.ejavashop.service.system.IRegionsService;
import com.ejavashop.web.controller.BaseController;
import com.google.gson.Gson;

/**
 * 区域controller
 *                       
 * @Filename: AdminRegionsController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/regions")
public class AdminRegionsController extends BaseController {

    @Resource
    private IRegionsService regionsService;

    Logger                  log = Logger.getLogger(this.getClass());

    /**
     * 获取省<br>
     * 查询一次后不再查询数据库，放入ServletContext以减少数据库访问
     * @param request
     * @param response
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "getProvince.html", method = { RequestMethod.GET })
    public void getProvince(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");

        ServletContext sc = request.getSession().getServletContext();

        List<Regions> list = new ArrayList<Regions>();

        Object pros = sc.getAttribute("provinceList");
        if (pros == null) {
            log.debug("没有缓存，查询数据库。。。");
            list = regionsService.getProvince();
            sc.setAttribute("provinceList", list);
        } else {
            log.debug("有缓存，直接返回。。。");
            list = (List<Regions>) pros;
        }

        Gson obj = new Gson();
        String json = obj.toJson(list);
        PrintWriter pw;
        try {
            pw = response.getWriter();
            pw.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 以上级地区id获取其子地区
     * @param request
     * @param response
     * @param p 上级地区id
     * @param type 地区类型
     */
    @RequestMapping(value = "getChildrenArea.html", method = { RequestMethod.GET })
    public void getCity(HttpServletRequest request, HttpServletResponse response, String p,
                        String type) {
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");

        if (StringUtil.isEmpty(p))
            return;
        List<Regions> list = regionsService.getChildrenArea(Integer.valueOf(p), type);

        Gson obj = new Gson();
        String json = obj.toJson(list);
        PrintWriter pw;
        try {
            pw = response.getWriter();
            pw.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 以下级id获取其完整地区字符串，地区形式形如 甘肃省 兰州市 城中区
     * @param areaid 地区id
     * @param sep 默认分隔符
     * @return
     */
    @RequestMapping(value = "getRequireArea.html", method = { RequestMethod.GET })
    public void getArea(HttpServletRequest request, HttpServletResponse response, String areaid,
                        String sep) {
        try {
            if (StringUtil.isEmpty(areaid))
                return;
            Gson obj = new Gson();

            response.setContentType("text/plain");
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding("UTF-8");

            PrintWriter pw;
            pw = response.getWriter();

            pw.println(obj.toJson(getAreaNameByParent(areaid, sep, true)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 递归获得给定地区的所有上级地区，组成新的完整的省市县名称
     * @param areaid
     * @param sep
     * @return
     */
    private String getAreaNameByParent(String areaid, String sep, boolean finish) {
        int len = sep.length();
        String areaName = "";
        Regions area = regionsService.getRegionsById(Integer.valueOf(areaid)) == null ? null
            : regionsService.getRegionsById(Integer.valueOf(areaid)).getResult();
        if (area == null)
            return areaName;
        areaName += area.getRegionName() + sep;
        //非顶级
        if (area.getParentId() != 0 && area.getRegionType() != 1) {
            //此地区的上级地区
            String parent = getAreaNameByParent(area.getParentId().toString(), sep, false);
            areaName = parent + areaName;
        }
        if (finish)
            areaName = areaName.substring(0, areaName.length() - len);
        finish = true;
        return areaName;
    }

    /**
     * 以下级id获取其完整地区字符串,默认分隔符“-”
     * @param areaid
     * @return
     */
    @RequestMapping(value = "getArea.html", method = { RequestMethod.GET })
    public void getArea(HttpServletRequest request, HttpServletResponse response, String areaid) {
        getArea(request, response, areaid, "-");
    }

    @RequestMapping(value = "getRegionByParentId", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Regions>> getRegionByParentId(Integer parentId,
                                                                           HttpServletRequest request,
                                                                           HttpServletResponse response,
                                                                           Map<String, Object> dataMap) {

        ServiceResult<List<Regions>> serviceResult = regionsService.getRegionsByParentId(parentId);
        if (!serviceResult.getSuccess()) {
            log.error("[RegionController][getRegionByParentId]根据父ID获取区域信息失败："
                      + serviceResult.getMessage());
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<Regions>> jsonResult = new HttpJsonResult<List<Regions>>();
        jsonResult.setData(serviceResult.getResult());
        return jsonResult;
    }
}
