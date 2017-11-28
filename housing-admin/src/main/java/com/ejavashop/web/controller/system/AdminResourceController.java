package com.ejavashop.web.controller.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.system.ResourceTree;
import com.ejavashop.entity.system.SystemResources;
import com.ejavashop.entity.system.SystemResourcesRoles;
import com.ejavashop.service.system.ISystemResourcesRolesService;
import com.ejavashop.service.system.ISystemResourcesService;
import com.ejavashop.web.controller.BaseController;

/**
 * 资源管理controller
 *                       
 * @Filename: AdminResourceController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/system/resource")
@Scope("prototype")
public class AdminResourceController extends BaseController {
    @Resource
    private ISystemResourcesService      resourcesService;
    @Resource
    private ISystemResourcesRolesService resourcesRolesService;
    private List<Integer>                resRoleIds = new ArrayList<Integer>();
    private List<SystemResources>        allres;
    private byte[]                       token      = new byte[0];

    Logger log = Logger.getLogger(this.getClass());

    /**
     * 默认页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        dataMap.put("queryMap", queryMap);
        return "admin/system/resource/list";
    }

    @RequestMapping(value = "addWin", method = { RequestMethod.GET })
    public String addWin(HttpServletRequest request, Map<String, Object> dataMap) throws Exception {
        dataMap.put("id", request.getParameter("id"));
        return "admin/system/resource/addWin";
    }

    /**
     * 编辑页面
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "editWin", method = { RequestMethod.GET })
    public String editWin(HttpServletRequest request,
                          Map<String, Object> dataMap) throws Exception {
        Integer id = Integer.valueOf(request.getParameter("id"));
        ServiceResult<SystemResources> serviceResult = resourcesService.getSystemResourcesById(id);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        dataMap.put("res", serviceResult.getResult());
        return "admin/system/resource/editWin";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody List<SystemResources> list(@RequestParam(value = "id", required = true) Integer pid,
                                                    HttpServletRequest request,
                                                    Map<String, Object> dataMap) {
        ServiceResult<List<SystemResources>> serviceResult = resourcesService.getByPid(pid);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        return serviceResult.getResult();
    }

    @RequestMapping(value = "resTree", method = { RequestMethod.GET })
    public @ResponseBody List<ResourceTree> resTree(HttpServletRequest request,
                                                    Map<String, Object> dataMap) {
        List<ResourceTree> tree = new ArrayList<ResourceTree>();
        ServiceResult<List<SystemResources>> serviceResult = resourcesService
            .page(new HashMap<String, String>(), null);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        synchronized (token) {
            this.allres = serviceResult.getResult();
            generateTree(tree, getByPid(0));
        }
        return tree;
    }

    @RequestMapping(value = "roleResTree", method = { RequestMethod.GET })
    public @ResponseBody List<ResourceTree> roleResTree(HttpServletRequest request,
                                                        Map<String, Object> dataMap,
                                                        String roleId) {
        List<ResourceTree> tree = new ArrayList<ResourceTree>();
        ServiceResult<List<SystemResources>> serviceResult = resourcesService
            .getByPid(ConstantsEJS.SYSTEM_RESOURCE_ROOT);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("rolesId", roleId);//TODO
        List<SystemResourcesRoles> srrlist = resourcesRolesService.page(map, null).getResult();
        for (SystemResourcesRoles sr : srrlist) {
            this.resRoleIds.add(sr.getResourcesId());
        }
        synchronized (token) {
            this.allres = resourcesService.page(new HashMap<String, String>(), null).getResult();
            generateTree(tree, serviceResult.getResult());
        }
        return tree;
    }

    /**
     * 递归生成树
     * @param treelist
     * @param data
     * @return
     */
    private List<ResourceTree> generateTree(List<ResourceTree> treelist,
                                            List<SystemResources> data) {
        for (SystemResources sr : data) {
            ResourceTree tree = new ResourceTree();
            tree.setId(sr.getId());
            tree.setText(sr.getContent());
            if (this.resRoleIds != null && this.resRoleIds.contains(sr.getId()))
                tree.setChecked(true);
            tree.setChildren(generateTree(new ArrayList<ResourceTree>(), getByPid(sr.getId())));
            tree.setState(tree.getChildren().size() > 0 ? "closed" : "open");
            treelist.add(tree);
        }
        return treelist;
    }

    private List<SystemResources> getByPid(Integer pid) {
        if (this.allres == null || this.allres.size() < 1)
            return null;
        List<SystemResources> reslist = new ArrayList<SystemResources>();
        for (SystemResources res : this.allres) {
            if (res.getPid().intValue() == pid.intValue())
                reslist.add(res);
        }
        return reslist;
    }

    @RequestMapping(value = "save", method = { RequestMethod.POST })
    public void save(HttpServletRequest request, HttpServletResponse response,
                     SystemResources res) {
        ServiceResult<Integer> serviceResult = null;
        String msg = "";
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            if (res.getId() != null && res.getId() != 0) {
                //编辑
                serviceResult = resourcesService.updateSystemResources(res);
            } else {
                //新增
                res.setStatus(ConstantsEJS.SYSTEM_SYSTEM_STATUS_1);
                serviceResult = resourcesService.saveSystemResources(res);
            }
            if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    throw new BusinessException(serviceResult.getMessage());
                }
            }
            response.setContentType("text/plain;charset=utf-8");
            msg = serviceResult.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        pw.print(msg);
    }

    @RequestMapping(value = "del", method = { RequestMethod.GET })
    public void del(HttpServletRequest request, HttpServletResponse response, Integer id) {
        response.setContentType("text/html;charset=utf-8");
        if (id == null) {
            throw new BusinessException("请选择要删除的资源");
        }
        PrintWriter pw = null;
        String msg = "";
        try {
            ServiceResult<Boolean> serviceResult = resourcesService.del(id);
            if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    throw new BusinessException(serviceResult.getMessage());
                }
            }
            msg = serviceResult.getMessage();
            pw = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BusinessException be) {
            msg = be.getMessage();
        }
        pw.print(msg);
    }

}
