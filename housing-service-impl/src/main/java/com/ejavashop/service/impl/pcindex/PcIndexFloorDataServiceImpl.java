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
import com.ejavashop.entity.shopm.pcindex.PcIndexFloorData;
import com.ejavashop.model.pcindex.PcIndexFloorDataModel;
import com.ejavashop.service.pcindex.IPcIndexFloorDataService;

@Service(value = "pcIndexFloorDataService")
public class PcIndexFloorDataServiceImpl implements IPcIndexFloorDataService {
    private static Logger         log = LogManager.getLogger(PcIndexFloorDataServiceImpl.class);

    @Resource
    private PcIndexFloorDataModel pcIndexFloorDataModel;

    @Override
    public ServiceResult<PcIndexFloorData> getPcIndexFloorDataById(Integer pcIndexFloorDataId) {
        ServiceResult<PcIndexFloorData> result = new ServiceResult<PcIndexFloorData>();
        try {
            result.setResult(pcIndexFloorDataModel.getPcIndexFloorDataById(pcIndexFloorDataId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcIndexFloorDataService][getPcIndexFloorDataById]根据id["
                      + pcIndexFloorDataId + "]取得PC端首页楼层分类数据时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexFloorDataService][getPcIndexFloorDataById]根据id["
                      + pcIndexFloorDataId + "]取得PC端首页楼层分类数据时发生异常:",
                e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> savePcIndexFloorData(PcIndexFloorData pcIndexFloorData) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(pcIndexFloorDataModel.savePcIndexFloorData(pcIndexFloorData));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcIndexFloorDataService][savePcIndexFloorData]保存PC端首页楼层分类数据时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexFloorDataService][savePcIndexFloorData]保存PC端首页楼层分类数据时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> updatePcIndexFloorData(PcIndexFloorData pcIndexFloorData) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(pcIndexFloorDataModel.updatePcIndexFloorData(pcIndexFloorData));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcIndexFloorDataService][updatePcIndexFloorData]更新PC端首页楼层分类数据时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexFloorDataService][updatePcIndexFloorData]更新PC端首页楼层分类数据时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> deletePcIndexFloorData(Integer pcIndexFloorDataId) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(pcIndexFloorDataModel.deletePcIndexFloorData(pcIndexFloorDataId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcIndexFloorDataService][deletePcIndexFloorData]删除PC端首页楼层分类数据时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexFloorDataService][deletePcIndexFloorData]删除PC端首页楼层分类数据时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<PcIndexFloorData>> getPcIndexFloorDatas(Map<String, String> queryMap,
                                                                      PagerInfo pager) {
        ServiceResult<List<PcIndexFloorData>> serviceResult = new ServiceResult<List<PcIndexFloorData>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(pcIndexFloorDataModel.getPcIndexFloorDatasCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult
                .setResult(pcIndexFloorDataModel.getPcIndexFloorDatas(queryMap, start, size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IPcIndexFloorDataService][getPcIndexFloorDatas]根据条件取得PC端首页楼层分类数据时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexFloorDataService][getPcIndexFloorDatas]param1:"
                      + JSON.toJSONString(queryMap) + " &param2:" + JSON.toJSONString(pager));
            log.error("[IPcIndexFloorDataService][getPcIndexFloorDatas]根据条件取得PC端首页楼层分类数据时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<PcIndexFloorData>> getPcIndexFloorDataForView(Integer floorClassId) {
        ServiceResult<List<PcIndexFloorData>> result = new ServiceResult<List<PcIndexFloorData>>();
        try {
            result.setResult(pcIndexFloorDataModel.getPcIndexFloorDataForView(floorClassId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcIndexFloorDataService][getPcIndexFloorDataForView]取得PC端首页楼层分类数据时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexFloorDataService][getPcIndexFloorDataForView]取得PC端首页楼层分类数据时发生异常:",
                e);
        }
        return result;
    }

}