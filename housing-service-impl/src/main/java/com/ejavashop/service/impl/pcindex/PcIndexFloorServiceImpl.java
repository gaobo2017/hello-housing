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
import com.ejavashop.entity.shopm.pcindex.PcIndexFloor;
import com.ejavashop.model.pcindex.PcIndexFloorModel;
import com.ejavashop.service.pcindex.IPcIndexFloorService;

@Service(value = "pcIndexFloorService")
public class PcIndexFloorServiceImpl implements IPcIndexFloorService {
    private static Logger     log = LogManager.getLogger(PcIndexFloorServiceImpl.class);

    @Resource
    private PcIndexFloorModel pcIndexFloorModel;

    @Override
    public ServiceResult<PcIndexFloor> getPcIndexFloorById(Integer pcIndexFloorId) {
        ServiceResult<PcIndexFloor> result = new ServiceResult<PcIndexFloor>();
        try {
            result.setResult(pcIndexFloorModel.getPcIndexFloorById(pcIndexFloorId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcIndexFloorService][getPcIndexFloorById]根据id[" + pcIndexFloorId
                      + "]取得PC端首页楼层时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexFloorService][getPcIndexFloorById]根据id[" + pcIndexFloorId
                      + "]取得PC端首页楼层时发生异常:",
                e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> savePcIndexFloor(PcIndexFloor pcIndexFloor) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(pcIndexFloorModel.savePcIndexFloor(pcIndexFloor));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcIndexFloorService][savePcIndexFloor]保存PC端首页楼层时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexFloorService][savePcIndexFloor]保存PC端首页楼层时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> updatePcIndexFloor(PcIndexFloor pcIndexFloor) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(pcIndexFloorModel.updatePcIndexFloor(pcIndexFloor));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error(
                "[IPcIndexFloorService][updatePcIndexFloor]更新PC端首页楼层时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexFloorService][updatePcIndexFloor]更新PC端首页楼层时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> deletePcIndexFloor(Integer pcIndexFloorId) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(pcIndexFloorModel.deletePcIndexFloor(pcIndexFloorId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error(
                "[IPcIndexFloorService][deletePcIndexFloor]删除PC端首页楼层时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexFloorService][deletePcIndexFloor]删除PC端首页楼层时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<PcIndexFloor>> getPcIndexFloors(Map<String, String> queryMap,
                                                              PagerInfo pager) {
        ServiceResult<List<PcIndexFloor>> serviceResult = new ServiceResult<List<PcIndexFloor>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(pcIndexFloorModel.getPcIndexFloorsCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(pcIndexFloorModel.getPcIndexFloors(queryMap, start, size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error(
                "[IPcIndexFloorService][getPcIndexFloors]根据条件取得PC端首页楼层时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexFloorService][getPcIndexFloors]param1:"
                      + JSON.toJSONString(queryMap) + " &param2:" + JSON.toJSONString(pager));
            log.error("[IPcIndexFloorService][getPcIndexFloors]根据条件取得PC端首页楼层时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<PcIndexFloor>> getPcIndexFloorForView(Boolean isPreview) {
        ServiceResult<List<PcIndexFloor>> result = new ServiceResult<List<PcIndexFloor>>();
        try {
            result.setResult(pcIndexFloorModel.getPcIndexFloorForView(isPreview));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error(
                "[IPcIndexFloorService][getPcIndexFloorForView]取得PC端首页楼层时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexFloorService][getPcIndexFloorForView]取得PC端首页楼层时发生异常:", e);
        }
        return result;
    }

}