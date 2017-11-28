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
import com.ejavashop.entity.shopm.pcindex.PcIndexBanner;
import com.ejavashop.model.pcindex.PcIndexBannerModel;
import com.ejavashop.service.pcindex.IPcIndexBannerService;

@Service(value = "pcIndexBannerService")
public class PcIndexBannerServiceImpl implements IPcIndexBannerService {
    private static Logger      log = LogManager.getLogger(PcIndexBannerServiceImpl.class);

    @Resource
    private PcIndexBannerModel pcIndexBannerModel;

    @Override
    public ServiceResult<PcIndexBanner> getPcIndexBannerById(Integer pcIndexBannerId) {
        ServiceResult<PcIndexBanner> result = new ServiceResult<PcIndexBanner>();
        try {
            result.setResult(pcIndexBannerModel.getPcIndexBannerById(pcIndexBannerId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcIndexBannerService][getPcIndexBannerById]根据id[" + pcIndexBannerId
                      + "]取得PC端首页轮播图时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexBannerService][getPcIndexBannerById]根据id[" + pcIndexBannerId
                      + "]取得PC端首页轮播图时发生异常:",
                e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> savePcIndexBanner(PcIndexBanner pcIndexBanner) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(pcIndexBannerModel.savePcIndexBanner(pcIndexBanner));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error(
                "[IPcIndexBannerService][savePcIndexBanner]保存PC端首页轮播图时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexBannerService][savePcIndexBanner]保存PC端首页轮播图时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> updatePcIndexBanner(PcIndexBanner pcIndexBanner) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(pcIndexBannerModel.updatePcIndexBanner(pcIndexBanner));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error(
                "[IPcIndexBannerService][updatePcIndexBanner]更新PC端首页轮播图时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexBannerService][updatePcIndexBanner]更新PC端首页轮播图时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> deletePcIndexBanner(Integer pcIndexBannerId) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(pcIndexBannerModel.deletePcIndexBanner(pcIndexBannerId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error(
                "[IPcIndexBannerService][deletePcIndexBanner]删除PC端首页轮播图时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexBannerService][deletePcIndexBanner]删除PC端首页轮播图时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<PcIndexBanner>> getPcIndexBanners(Map<String, String> queryMap,
                                                                PagerInfo pager) {
        ServiceResult<List<PcIndexBanner>> serviceResult = new ServiceResult<List<PcIndexBanner>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(pcIndexBannerModel.getPcIndexBannersCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(pcIndexBannerModel.getPcIndexBanners(queryMap, start, size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error(
                "[IPcIndexBannerService][getPcIndexBanners]根据条件取得PC端首页轮播图时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexBannerService][getPcIndexBanners]param1:"
                      + JSON.toJSONString(queryMap) + " &param2:" + JSON.toJSONString(pager));
            log.error("[IPcIndexBannerService][getPcIndexBanners]根据条件取得PC端首页轮播图时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<PcIndexBanner>> getPcIndexBannerForView(Boolean isPreview) {
        ServiceResult<List<PcIndexBanner>> result = new ServiceResult<List<PcIndexBanner>>();
        try {
            result.setResult(pcIndexBannerModel.getPcIndexBannerForView(isPreview));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcIndexBannerService][getPcIndexBannerForView]取得PC端首页轮播图时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcIndexBannerService][getPcIndexBannerForView]取得PC端首页轮播图时发生异常:", e);
        }
        return result;
    }

}