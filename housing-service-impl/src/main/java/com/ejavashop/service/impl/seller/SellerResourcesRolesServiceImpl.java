package com.ejavashop.service.impl.seller;

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
import com.ejavashop.entity.seller.SellerResourcesRoles;
import com.ejavashop.entity.system.SystemResources;
import com.ejavashop.model.seller.SellerResourcesRolesModel;
import com.ejavashop.service.seller.ISellerResourcesRolesService;

@Service(value = "sellerResourcesRolesService")
public class SellerResourcesRolesServiceImpl implements ISellerResourcesRolesService {
    private static Logger             log = LogManager
        .getLogger(SellerResourcesRolesServiceImpl.class);

    @Resource
    private SellerResourcesRolesModel sellerResourcesRolesModel;

    @Override
    public ServiceResult<SellerResourcesRoles> getSellerResourcesRolesById(Integer sellerResourcesRolesId) {
        ServiceResult<SellerResourcesRoles> result = new ServiceResult<SellerResourcesRoles>();
        try {
            result.setResult(
                sellerResourcesRolesModel.getSellerResourcesRolesById(sellerResourcesRolesId));
        } catch (Exception e) {
            log.error("根据id[" + sellerResourcesRolesId + "]取得角色资源对应表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据id[" + sellerResourcesRolesId + "]取得角色资源对应表时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> saveSellerResourcesRoles(SellerResourcesRoles sellerResourcesRoles) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(
                sellerResourcesRolesModel.saveSellerResourcesRoles(sellerResourcesRoles));
        } catch (Exception e) {
            log.error("保存角色资源对应表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("保存角色资源对应表时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateSellerResourcesRoles(SellerResourcesRoles sellerResourcesRoles) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(
                sellerResourcesRolesModel.updateSellerResourcesRoles(sellerResourcesRoles));
        } catch (Exception e) {
            log.error("更新角色资源对应表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("更新角色资源对应表时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<List<SellerResourcesRoles>> page(Map<String, String> queryMap,
                                                          PagerInfo pager) {
        ServiceResult<List<SellerResourcesRoles>> serviceResult = new ServiceResult<List<SellerResourcesRoles>>();
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(sellerResourcesRolesModel.pageCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            queryMap.put("start", start + "");
            queryMap.put("size", size + "");
            List<SellerResourcesRoles> list = sellerResourcesRolesModel.page(queryMap);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SellerResourcesRolesServiceImpl][page] param1:"
                      + JSON.toJSONString(queryMap) + " &param2:" + JSON.toJSONString(pager));
            log.error("[SellerResourcesRolesServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> del(Integer id) {

        ServiceResult<Boolean> sr = new ServiceResult<Boolean>();
        try {
            sr.setResult(sellerResourcesRolesModel.del(id));
        } catch (Exception e) {
            log.error("[SellerResourcesRolesServiceImpl][del] exception:" + e.getMessage());
            e.printStackTrace();
        }
        return sr;
    }

    @Override
    public ServiceResult<Boolean> save(String roleId, String[] resArr) {
        ServiceResult<Boolean> serRes = new ServiceResult<Boolean>();
        try {
            serRes.setResult(sellerResourcesRolesModel.save(roleId, resArr));
            serRes.setMessage("保存成功！");
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

            serRes.setResult(sellerResourcesRolesModel.getResourceByRoleId(roleId, scope));
        } catch (Exception e) {
            serRes.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, e.getMessage());
            e.printStackTrace();
        }
        return serRes;
    }

    @Override
    public List<SystemResources> getResourceByPid(Integer pid, Integer roleId,
                                                  Integer scope) throws BusinessException {
        return sellerResourcesRolesModel.getResourceByPid(pid, roleId, scope);
    }
}