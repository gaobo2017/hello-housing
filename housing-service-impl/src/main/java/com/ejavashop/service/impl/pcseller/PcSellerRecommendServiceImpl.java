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
import com.ejavashop.entity.shopm.pcseller.PcSellerRecommend;
import com.ejavashop.model.pcseller.PcSellerRecommendModel;
import com.ejavashop.service.pcseller.IPcSellerRecommendService;

@Service(value = "pcSellerRecommendService")
public class PcSellerRecommendServiceImpl implements IPcSellerRecommendService {
    private static Logger          log = LogManager.getLogger(PcSellerRecommendServiceImpl.class);

    @Resource
    private PcSellerRecommendModel pcSellerRecommendModel;

    @Override
    public ServiceResult<PcSellerRecommend> getPcSellerRecommendById(Integer pcSellerRecommendId) {
        ServiceResult<PcSellerRecommend> result = new ServiceResult<PcSellerRecommend>();
        try {
            result.setResult(pcSellerRecommendModel.getPcSellerRecommendById(pcSellerRecommendId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcSellerRecommendService][getPcSellerRecommendById]根据id["
                      + pcSellerRecommendId + "]取得PC端商家推荐类型时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcSellerRecommendService][getPcSellerRecommendById]根据id["
                      + pcSellerRecommendId + "]取得PC端商家推荐类型时发生异常:",
                e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> savePcSellerRecommend(PcSellerRecommend pcSellerRecommend) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(pcSellerRecommendModel.savePcSellerRecommend(pcSellerRecommend));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcSellerRecommendService][savePcSellerRecommend]保存PC端商家推荐类型时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcSellerRecommendService][savePcSellerRecommend]保存PC端商家推荐类型时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> updatePcSellerRecommend(PcSellerRecommend pcSellerRecommend) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(pcSellerRecommendModel.updatePcSellerRecommend(pcSellerRecommend));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcSellerRecommendService][updatePcSellerRecommend]更新PC端商家推荐类型时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcSellerRecommendService][updatePcSellerRecommend]更新PC端商家推荐类型时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> deletePcSellerRecommend(Integer pcSellerRecommendId) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(pcSellerRecommendModel.deletePcSellerRecommend(pcSellerRecommendId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcSellerRecommendService][deletePcSellerRecommend]删除PC端商家推荐类型时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcSellerRecommendService][deletePcSellerRecommend]删除PC端商家推荐类型时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<PcSellerRecommend>> getPcSellerRecommends(Map<String, String> queryMap,
                                                                        PagerInfo pager) {
        ServiceResult<List<PcSellerRecommend>> serviceResult = new ServiceResult<List<PcSellerRecommend>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(pcSellerRecommendModel.getPcSellerRecommendsCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult
                .setResult(pcSellerRecommendModel.getPcSellerRecommends(queryMap, start, size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IPcSellerRecommendService][getPcSellerRecommends]根据条件取得PC端商家推荐类型时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcSellerRecommendService][getPcSellerRecommends]param1:"
                      + JSON.toJSONString(queryMap) + " &param2:" + JSON.toJSONString(pager));
            log.error("[IPcSellerRecommendService][getPcSellerRecommends]根据条件取得PC端商家推荐类型时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<PcSellerRecommend>> getPcSellerRecommendForView(Integer sellerId,
                                                                              Boolean isPreview) {
        ServiceResult<List<PcSellerRecommend>> result = new ServiceResult<List<PcSellerRecommend>>();
        try {
            result
                .setResult(pcSellerRecommendModel.getPcSellerRecommendForView(sellerId, isPreview));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcSellerRecommendService][getPcSellerRecommendForView]取得PC端商家推荐类型时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcSellerRecommendService][getPcSellerRecommendForView]取得PC端商家推荐类型时发生异常:",
                e);
        }
        return result;
    }

}