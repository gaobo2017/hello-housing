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
import com.ejavashop.entity.shopm.pcseller.PcSellerIndexBanner;
import com.ejavashop.model.pcseller.PcSellerIndexBannerModel;
import com.ejavashop.service.pcseller.IPcSellerIndexBannerService;

@Service(value = "pcSellerIndexBannerService")
public class PcSellerIndexBannerServiceImpl implements IPcSellerIndexBannerService {
    private static Logger            log = LogManager
        .getLogger(PcSellerIndexBannerServiceImpl.class);

    @Resource
    private PcSellerIndexBannerModel pcSellerIndexBannerModel;

    @Override
    public ServiceResult<PcSellerIndexBanner> getPcSellerIndexBannerById(Integer pcSellerIndexBannerId) {
        ServiceResult<PcSellerIndexBanner> result = new ServiceResult<PcSellerIndexBanner>();
        try {
            result.setResult(
                pcSellerIndexBannerModel.getPcSellerIndexBannerById(pcSellerIndexBannerId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcSellerIndexBannerService][getPcSellerIndexBannerById]根据id["
                      + pcSellerIndexBannerId + "]取得PC端商家首页轮播图时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcSellerIndexBannerService][getPcSellerIndexBannerById]根据id["
                      + pcSellerIndexBannerId + "]取得PC端商家首页轮播图时发生异常:",
                e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> savePcSellerIndexBanner(PcSellerIndexBanner pcSellerIndexBanner) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(pcSellerIndexBannerModel.savePcSellerIndexBanner(pcSellerIndexBanner));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcSellerIndexBannerService][savePcSellerIndexBanner]保存PC端商家首页轮播图时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcSellerIndexBannerService][savePcSellerIndexBanner]保存PC端商家首页轮播图时发生异常:",
                e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> updatePcSellerIndexBanner(PcSellerIndexBanner pcSellerIndexBanner) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result
                .setResult(pcSellerIndexBannerModel.updatePcSellerIndexBanner(pcSellerIndexBanner));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcSellerIndexBannerService][updatePcSellerIndexBanner]更新PC端商家首页轮播图时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcSellerIndexBannerService][updatePcSellerIndexBanner]更新PC端商家首页轮播图时发生异常:",
                e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> deletePcSellerIndexBanner(Integer pcSellerIndexBannerId) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(
                pcSellerIndexBannerModel.deletePcSellerIndexBanner(pcSellerIndexBannerId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcSellerIndexBannerService][deletePcSellerIndexBanner]删除PC端商家首页轮播图时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcSellerIndexBannerService][deletePcSellerIndexBanner]删除PC端商家首页轮播图时发生异常:",
                e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<PcSellerIndexBanner>> getPcSellerIndexBanners(Map<String, String> queryMap,
                                                                            PagerInfo pager) {
        ServiceResult<List<PcSellerIndexBanner>> serviceResult = new ServiceResult<List<PcSellerIndexBanner>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(pcSellerIndexBannerModel.getPcSellerIndexBannersCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult
                .setResult(pcSellerIndexBannerModel.getPcSellerIndexBanners(queryMap, start, size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IPcSellerIndexBannerService][getPcSellerIndexBanners]根据条件取得PC端商家首页轮播图时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcSellerIndexBannerService][getPcSellerIndexBanners]param1:"
                      + JSON.toJSONString(queryMap) + " &param2:" + JSON.toJSONString(pager));
            log.error(
                "[IPcSellerIndexBannerService][getPcSellerIndexBanners]根据条件取得PC端商家首页轮播图时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<PcSellerIndexBanner>> getPcSellerIndexBannerForView(Integer sellerId,
                                                                                  Boolean isPreview) {
        ServiceResult<List<PcSellerIndexBanner>> result = new ServiceResult<List<PcSellerIndexBanner>>();
        try {
            result.setResult(
                pcSellerIndexBannerModel.getPcSellerIndexBannerForView(sellerId, isPreview));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error(
                "[IPcSellerIndexBannerService][getPcSellerIndexBannerForView]取得PC端商家首页轮播图时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error(
                "[IPcSellerIndexBannerService][getPcSellerIndexBannerForView]取得PC端商家首页轮播图时发生异常:",
                e);
        }
        return result;
    }

}