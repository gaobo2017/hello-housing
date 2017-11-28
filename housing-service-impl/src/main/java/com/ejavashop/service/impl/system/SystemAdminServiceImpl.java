package com.ejavashop.service.impl.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.model.system.SystemAdminModel;
import com.ejavashop.service.system.ISystemAdminService;

@Service(value = "systemAdminService")
public class SystemAdminServiceImpl implements ISystemAdminService {
    private static Logger                log = LogManager.getLogger(SystemAdminServiceImpl.class);

    @Resource
    private SystemAdminModel             systemAdminModel;
    @Resource
    private DataSourceTransactionManager transactionManager;

    @Override
    public ServiceResult<SystemAdmin> getSystemAdminById(Integer systemAdminId) {
        ServiceResult<SystemAdmin> result = new ServiceResult<SystemAdmin>();
        try {
            result.setResult(systemAdminModel.getSystemAdminById(systemAdminId));
        } catch (Exception e) {
            log.error("根据id[" + systemAdminId + "]取得系统管理员表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据id[" + systemAdminId + "]取得系统管理员表时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> saveSystemAdmin(SystemAdmin systemAdmin) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(systemAdminModel.saveSystemAdmin(systemAdmin));
            result.setMessage("保存成功");
        } catch (Exception e) {
            log.error("保存系统管理员表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("保存系统管理员表时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateSystemAdmin(SystemAdmin systemAdmin) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(systemAdminModel.updateSystemAdmin(systemAdmin));
            result.setMessage("更新成功");
        } catch (Exception e) {
            log.error("更新系统管理员表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("更新系统管理员表时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<List<SystemAdmin>> page(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<SystemAdmin>> serviceResult = new ServiceResult<List<SystemAdmin>>();
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(systemAdminModel.pageCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            List<SystemAdmin> list = systemAdminModel.page(queryMap, start, size);

            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SystemAdminServiceImpl][page] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[SystemAdminServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> del(Integer id) {

        ServiceResult<Boolean> sr = new ServiceResult<Boolean>();
        try {
            sr.setResult(systemAdminModel.del(id));
        } catch (Exception e) {
            log.error("[SystemAdminServiceImpl][del] exception:" + e.getMessage());
            e.printStackTrace();
        }
        return sr;
    }

    @Override
    public ServiceResult<SystemAdmin> getSystemAdminByNamePwd(String name, String password) {
        ServiceResult<SystemAdmin> result = new ServiceResult<SystemAdmin>();
        try {
            result.setResult(systemAdminModel.getSystemAdminByNamePwd(name, password));
        } catch (Exception e) {
            log.error("[SystemAdminServiceImpl][getSystemAdminByNamePwd] exception:", e);
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("用户名或密码错误");
        }
        return result;
    }

    @Override
    public ServiceResult<List<SystemAdmin>> getSystemAdminByName(String name) {
        ServiceResult<List<SystemAdmin>> result = new ServiceResult<List<SystemAdmin>>();
        try {
            result.setResult(systemAdminModel.getSystemAdminByName(name));
        } catch (Exception e) {
            log.error("[SystemAdminServiceImpl][getSystemAdminByName] exception:", e);
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}