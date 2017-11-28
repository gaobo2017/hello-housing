package com.ejavashop.service.impl.pcindex;

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
import com.ejavashop.entity.shopm.pcindex.PcIndexFloorClass;
import com.ejavashop.model.pcindex.PcIndexFloorClassModel;
import com.ejavashop.service.pcindex.IPcIndexFloorClassService;

@Service(value = "pcIndexFloorClassService")
public class PcIndexFloorClassServiceImpl implements IPcIndexFloorClassService {
    private static Logger          log = LogManager.getLogger(PcIndexFloorClassServiceImpl.class);

    @Resource
    private PcIndexFloorClassModel pcIndexFloorClassModel;

    @Override
    public ServiceResult<PcIndexFloorClass> getPcIndexFloorClassById(Integer pcIndexFloorClassId) {
        ServiceResult<PcIndexFloorClass> result = new ServiceResult<PcIndexFloorClass>();
        try {
            result.setResult(pcIndexFloorClassModel.getPcIndexFloorClassById(pcIndexFloorClassId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcIndexFloorClassService][getPcIndexFloorClassById]根据id["
                      + pcIndexFloorClassId + "]取得PC端首页楼层分类时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexFloorClassService][getPcIndexFloorClassById]根据id["
                      + pcIndexFloorClassId + "]取得PC端首页楼层分类时发生异常:",
                e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> savePcIndexFloorClass(PcIndexFloorClass pcIndexFloorClass) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(pcIndexFloorClassModel.savePcIndexFloorClass(pcIndexFloorClass));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcIndexFloorClassService][savePcIndexFloorClass]保存PC端首页楼层分类时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexFloorClassService][savePcIndexFloorClass]保存PC端首页楼层分类时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> updatePcIndexFloorClass(PcIndexFloorClass pcIndexFloorClass) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(pcIndexFloorClassModel.updatePcIndexFloorClass(pcIndexFloorClass));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcIndexFloorClassService][updatePcIndexFloorClass]更新PC端首页楼层分类时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexFloorClassService][updatePcIndexFloorClass]更新PC端首页楼层分类时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> deletePcIndexFloorClass(Integer pcIndexFloorClassId) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(pcIndexFloorClassModel.deletePcIndexFloorClass(pcIndexFloorClassId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcIndexFloorClassService][deletePcIndexFloorClass]删除PC端首页楼层分类时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexFloorClassService][deletePcIndexFloorClass]删除PC端首页楼层分类时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<PcIndexFloorClass>> getPcIndexFloorClasss(Map<String, String> queryMap,
                                                                        PagerInfo pager) {
        ServiceResult<List<PcIndexFloorClass>> serviceResult = new ServiceResult<List<PcIndexFloorClass>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(pcIndexFloorClassModel.getPcIndexFloorClasssCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult
                .setResult(pcIndexFloorClassModel.getPcIndexFloorClasss(queryMap, start, size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IPcIndexFloorClassService][getPcIndexFloorClasss]根据条件取得PC端首页楼层分类时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexFloorClassService][getPcIndexFloorClasss]param1:"
                      + JSON.toJSONString(queryMap) + " &param2:" + JSON.toJSONString(pager));
            log.error("[IPcIndexFloorClassService][getPcIndexFloorClasss]根据条件取得PC端首页楼层分类时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<PcIndexFloorClass>> getPcIndexFloorClassForView(Integer floorId,
                                                                              Boolean isPreview) {
        ServiceResult<List<PcIndexFloorClass>> result = new ServiceResult<List<PcIndexFloorClass>>();
        try {
            result
                .setResult(pcIndexFloorClassModel.getPcIndexFloorClassForView(floorId, isPreview));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcIndexFloorClassService][getPcIndexFloorClassForView]取得PC端首页楼层分类时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexFloorClassService][getPcIndexFloorClassForView]取得PC端首页楼层分类时发生异常:",
                e);
        }
        return result;
    }

}