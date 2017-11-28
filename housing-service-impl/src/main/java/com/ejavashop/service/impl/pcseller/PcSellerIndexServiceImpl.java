package com.ejavashop.service.impl.pcseller;

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
import com.ejavashop.entity.shopm.pcseller.PcSellerIndex;
import com.ejavashop.model.pcseller.PcSellerIndexModel;
import com.ejavashop.service.pcseller.IPcSellerIndexService;

@Service(value = "pcSellerIndexService")
public class PcSellerIndexServiceImpl implements IPcSellerIndexService {
    private static Logger      log = LogManager.getLogger(PcSellerIndexServiceImpl.class);

    @Resource
    private PcSellerIndexModel pcSellerIndexModel;

    @Override
    public ServiceResult<PcSellerIndex> getPcSellerIndexById(Integer pcSellerIndexId) {
        ServiceResult<PcSellerIndex> result = new ServiceResult<PcSellerIndex>();
        try {
            result.setResult(pcSellerIndexModel.getPcSellerIndexById(pcSellerIndexId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcSellerIndexService][getPcSellerIndexById]根据id[" + pcSellerIndexId
                      + "]取得PC端商家首页信息时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcSellerIndexService][getPcSellerIndexById]根据id[" + pcSellerIndexId
                      + "]取得PC端商家首页信息时发生异常:",
                e);
        }
        return result;
    }

    @Override
    public ServiceResult<PcSellerIndex> getPcSellerIndexBySellerId(Integer sellerId) {
        ServiceResult<PcSellerIndex> result = new ServiceResult<PcSellerIndex>();
        try {
            result.setResult(pcSellerIndexModel.getPcSellerIndexBySellerId(sellerId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcSellerIndexService][getPcSellerIndexById]根据商家id[" + sellerId
                      + "]取得PC端商家首页信息时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcSellerIndexService][getPcSellerIndexById]根据商家id[" + sellerId
                      + "]取得PC端商家首页信息时发生异常:",
                e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> savePcSellerIndex(PcSellerIndex pcSellerIndex) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(pcSellerIndexModel.savePcSellerIndex(pcSellerIndex));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error(
                "[IPcSellerIndexService][savePcSellerIndex]保存PC端商家首页信息时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcSellerIndexService][savePcSellerIndex]保存PC端商家首页信息时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> updatePcSellerIndex(PcSellerIndex pcSellerIndex) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(pcSellerIndexModel.updatePcSellerIndex(pcSellerIndex));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error(
                "[IPcSellerIndexService][updatePcSellerIndex]更新PC端商家首页信息时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcSellerIndexService][updatePcSellerIndex]更新PC端商家首页信息时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> deletePcSellerIndex(Integer pcSellerIndexId) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(pcSellerIndexModel.deletePcSellerIndex(pcSellerIndexId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error(
                "[IPcSellerIndexService][deletePcSellerIndex]删除PC端商家首页信息时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcSellerIndexService][deletePcSellerIndex]删除PC端商家首页信息时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<PcSellerIndex>> getPcSellerIndexs(Map<String, String> queryMap,
                                                                PagerInfo pager) {
        ServiceResult<List<PcSellerIndex>> serviceResult = new ServiceResult<List<PcSellerIndex>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(pcSellerIndexModel.getPcSellerIndexsCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(pcSellerIndexModel.getPcSellerIndexs(queryMap, start, size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error(
                "[IPcSellerIndexService][getPcSellerIndexs]根据条件取得PC端商家首页信息时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcSellerIndexService][getPcSellerIndexs]param1:"
                      + JSON.toJSONString(queryMap) + " &param2:" + JSON.toJSONString(pager));
            log.error("[IPcSellerIndexService][getPcSellerIndexs]根据条件取得PC端商家首页信息时发生异常:", e);
        }
        return serviceResult;
    }

}