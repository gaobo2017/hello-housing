package com.ejavashop.service.impl.promotion;

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
import com.ejavashop.entity.promotion.group.ActGroupBanner;
import com.ejavashop.model.promotion.ActGroupBannerModel;
import com.ejavashop.service.promotion.IActGroupBannerService;

@Service(value = "actGroupBannerService")
public class ActGroupBannerServiceImpl implements IActGroupBannerService {
    private static Logger       log = LogManager.getLogger(ActGroupBannerServiceImpl.class);

    @Resource
    private ActGroupBannerModel actGroupBannerModel;

    @Override
    public ServiceResult<ActGroupBanner> getActGroupBannerById(Integer actGroupBannerId) {
        ServiceResult<ActGroupBanner> result = new ServiceResult<ActGroupBanner>();
        try {
            result.setResult(actGroupBannerModel.getActGroupBannerById(actGroupBannerId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IActGroupBannerService][getActGroupBannerById]根据id[" + actGroupBannerId
                      + "]取得团购首页轮播图时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActGroupBannerService][getActGroupBannerById]根据id[" + actGroupBannerId
                      + "]取得团购首页轮播图时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> saveActGroupBanner(ActGroupBanner actGroupBanner) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actGroupBannerModel.saveActGroupBanner(actGroupBanner));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IActGroupBannerService][saveActGroupBanner]保存团购首页轮播图时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActGroupBannerService][saveActGroupBanner]保存团购首页轮播图时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> updateActGroupBanner(ActGroupBanner actGroupBanner) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actGroupBannerModel.updateActGroupBanner(actGroupBanner));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IActGroupBannerService][updateActGroupBanner]更新团购首页轮播图时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActGroupBannerService][updateActGroupBanner]更新团购首页轮播图时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> deleteActGroupBanner(Integer actGroupBannerId) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actGroupBannerModel.deleteActGroupBanner(actGroupBannerId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IActGroupBannerService][deleteActGroupBanner]删除团购首页轮播图时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActGroupBannerService][deleteActGroupBanner]删除团购首页轮播图时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<ActGroupBanner>> getActGroupBanners(Map<String, String> queryMap,
                                                                  PagerInfo pager) {
        ServiceResult<List<ActGroupBanner>> serviceResult = new ServiceResult<List<ActGroupBanner>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(actGroupBannerModel.getActGroupBannersCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(actGroupBannerModel.getActGroupBanners(queryMap, start, size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IActGroupBannerService][getActGroupBanners]根据条件取得团购首页轮播图时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActGroupBannerService][getActGroupBanners]param1:"
                      + JSON.toJSONString(queryMap) + " &param2:" + JSON.toJSONString(pager));
            log.error("[IActGroupBannerService][getActGroupBanners]根据条件取得团购首页轮播图时发生异常:", e);
        }
        return serviceResult;
    }

    /**
     * 查询团购首页轮播图
     * @param pcMobile
     * @return
     * @see com.ejavashop.service.promotion.IActGroupBannerService#getActGroupBannersPcMobile(int)
     */
    @Override
    public ServiceResult<List<ActGroupBanner>> getActGroupBannersPcMobile(int pcMobile) {
        ServiceResult<List<ActGroupBanner>> serviceResult = new ServiceResult<List<ActGroupBanner>>();
        try {
            serviceResult.setResult(actGroupBannerModel.getActGroupBannersPcMobile(pcMobile));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IActGroupBannerService][getActGroupBannersPcMobile]根据条件取得团购首页轮播图时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActGroupBannerService][getActGroupBannersPcMobile]根据条件取得团购首页轮播图时发生异常:", e);
        }
        return serviceResult;
    }

}