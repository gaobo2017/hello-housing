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
import com.ejavashop.model.system.SystemResourcesModel;
import com.ejavashop.service.system.ISystemResourcesService;

@Service(value = "systemResourcesService")
public class SystemResourcesServiceImpl implements ISystemResourcesService {
    private static Logger        log = LogManager.getLogger(SystemResourcesServiceImpl.class);

    @Resource
    private SystemResourcesModel systemResourcesModel;

    /**
    * 根据id取得资源表
    * @param  systemResourcesId
    * @return
    */
    @Override
    public ServiceResult<SystemResources> getSystemResourcesById(Integer systemResourcesId) {
        ServiceResult<SystemResources> result = new ServiceResult<SystemResources>();
        try {
            result.setResult(systemResourcesModel.getSystemResourcesById(systemResourcesId));
        } catch (Exception e) {
            log.error("根据id[" + systemResourcesId + "]取得资源表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据id[" + systemResourcesId + "]取得资源表时出现未知异常");
        }
        return result;
    }

    /**
     * 保存资源表
     * @param  systemResources
     * @return
     */
    @Override
    public ServiceResult<Integer> saveSystemResources(SystemResources systemResources) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(systemResourcesModel.saveSystemResources(systemResources));
            result.setMessage("保存成功");
        } catch (Exception e) {
            log.error("保存资源表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("保存资源表时出现未知异常");
        }
        return result;
    }

    /**
    * 更新资源表
    * @param  systemResources
    * @return
    */
    @Override
    public ServiceResult<Integer> updateSystemResources(SystemResources systemResources) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(systemResourcesModel.updateSystemResources(systemResources));
            result.setMessage("更新成功");
        } catch (Exception e) {
            log.error("更新资源表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("更新资源表时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<List<SystemResources>> page(Map<String, String> queryMap,
                                                     PagerInfo pager) {
        ServiceResult<List<SystemResources>> serviceResult = new ServiceResult<List<SystemResources>>();
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(systemResourcesModel.pageCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            queryMap.put("start", start + "");
            queryMap.put("size", size + "");
            List<SystemResources> list = systemResourcesModel.page(queryMap);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SystemResourcesServiceImpl][page] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[SystemResourcesServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> del(Integer id) {

        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(systemResourcesModel.del(id));
            result.setMessage("删除成功");
        } catch (Exception e) {
            log.error("[SystemResourcesServiceImpl][del] exception:" + e.getMessage());
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ServiceResult<List<SystemResources>> getByPid(Integer pid) {
        ServiceResult<List<SystemResources>> serviceResult = new ServiceResult<List<SystemResources>>();
        try {
            serviceResult.setResult(systemResourcesModel.getByPid(pid));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SystemResourcesServiceImpl][getByPid] pid:" + pid);
            log.error("[SystemResourcesServiceImpl][getByPid] exception:" + e.getMessage());
        }
        return serviceResult;
    }
}