package com.ejavashop.service.impl.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.system.SystemResources;
import com.ejavashop.entity.system.SystemResourcesRoles;
import com.ejavashop.model.system.SystemResourcesRolesModel;
import com.ejavashop.service.system.ISystemResourcesRolesService;

@Service(value = "systemResourcesRolesService")
public class SystemResourcesRolesServiceImpl implements ISystemResourcesRolesService {
    private static Logger             log = LogManager
        .getLogger(SystemResourcesRolesServiceImpl.class);

    @Resource
    private SystemResourcesRolesModel systemResourcesRolesModel;

    @Override
    public ServiceResult<SystemResourcesRoles> getSystemResourcesRolesById(Integer systemResourcesRolesId) {
        ServiceResult<SystemResourcesRoles> result = new ServiceResult<SystemResourcesRoles>();
        try {
            result.setResult(
                systemResourcesRolesModel.getSystemResourcesRolesById(systemResourcesRolesId));
        } catch (Exception e) {
            log.error("根据id[" + systemResourcesRolesId + "]取得角色资源对应表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据id[" + systemResourcesRolesId + "]取得角色资源对应表时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> saveSystemResourcesRoles(SystemResourcesRoles systemResourcesRoles) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(
                systemResourcesRolesModel.saveSystemResourcesRoles(systemResourcesRoles));
            result.setMessage("保存成功！");
        } catch (Exception e) {
            log.error("保存角色资源对应表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("保存角色资源对应表时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateSystemResourcesRoles(SystemResourcesRoles systemResourcesRoles) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(
                systemResourcesRolesModel.updateSystemResourcesRoles(systemResourcesRoles));
            result.setMessage("修改成功！");
        } catch (Exception e) {
            log.error("更新角色资源对应表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("更新角色资源对应表时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<List<SystemResourcesRoles>> page(Map<String, String> queryMap,
                                                          PagerInfo pager) {
        ServiceResult<List<SystemResourcesRoles>> serviceResult = new ServiceResult<List<SystemResourcesRoles>>();
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(systemResourcesRolesModel.pageCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            queryMap.put("start", start + "");
            queryMap.put("size", size + "");
            List<SystemResourcesRoles> list = systemResourcesRolesModel.page(queryMap);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SystemResourcesRolesServiceImpl][page] param1:"
                      + JSON.toJSONString(queryMap) + " &param2:" + JSON.toJSONString(pager));
            log.error("[SystemResourcesRolesServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> del(Integer id) {

        ServiceResult<Boolean> sr = new ServiceResult<Boolean>();
        try {
            sr.setResult(systemResourcesRolesModel.del(id));
        } catch (Exception e) {
            log.error("[SystemResourcesRolesServiceImpl][del] exception:" + e.getMessage());
            e.printStackTrace();
        }
        return sr;
    }

    @Override
    public ServiceResult<Boolean> save(String roleId, String[] resArr) {
        ServiceResult<Boolean> serRes = new ServiceResult<Boolean>();
        try {

            serRes.setResult(systemResourcesRolesModel.save(roleId, resArr));
            serRes.setMessage("保存成功。");
        } catch (Exception e) {
            serRes.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, e.getMessage());
            e.printStackTrace();
        }
        return serRes;
    }

    @Override
    public ServiceResult<List<SystemResources>> getResourceByRoleId(Integer roleId, Integer scope) {
        ServiceResult<List<SystemResources>> serRes = new ServiceResult<List<SystemResources>>();
        try {

            serRes.setResult(systemResourcesRolesModel.getResourceByRoleId(roleId, scope));
        } catch (Exception e) {
            serRes.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, e.getMessage());
            e.printStackTrace();
        }
        return serRes;
    }

    @Override
    public List<SystemResources> getResourceByPid(Integer pid, Integer roleId,
                                                  Integer scope) throws BusinessException {
        return systemResourcesRolesModel.getResourceByPid(pid, roleId, scope);
    }
}