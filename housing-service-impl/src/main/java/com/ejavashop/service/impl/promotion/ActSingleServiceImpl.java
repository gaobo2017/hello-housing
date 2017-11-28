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
import com.ejavashop.entity.promotion.single.ActSingle;
import com.ejavashop.model.promotion.ActSingleModel;
import com.ejavashop.service.promotion.IActSingleService;

@Service(value = "actSingleService")
public class ActSingleServiceImpl implements IActSingleService {
    private static Logger  log = LogManager.getLogger(ActSingleServiceImpl.class);

    @Resource
    private ActSingleModel actSingleModel;

    @Override
    public ServiceResult<ActSingle> getActSingleById(Integer actSingleId) {
        ServiceResult<ActSingle> result = new ServiceResult<ActSingle>();
        try {
            result.setResult(actSingleModel.getActSingleById(actSingleId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IActSingleService][getActSingleById]根据id[" + actSingleId + "]取得单品立减活动时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error(
                "[IActSingleService][getActSingleById]根据id[" + actSingleId + "]取得单品立减活动时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> saveActSingle(ActSingle actSingle) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actSingleModel.saveActSingle(actSingle));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IActSingleService][saveActSingle]保存单品立减活动时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActSingleService][saveActSingle]保存单品立减活动时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> updateActSingle(ActSingle actSingle) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actSingleModel.updateActSingle(actSingle));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IActSingleService][updateActSingle]更新单品立减活动时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActSingleService][updateActSingle]更新单品立减活动时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> updateActSingleStatus(ActSingle actSingle) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actSingleModel.updateActSingleStatus(actSingle));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error(
                "[IActSingleService][updateActSingleStatus]更新单品立减活动状态时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActSingleService][updateActSingleStatus]更新单品立减活动状态时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> deleteActSingle(Integer actSingleId, Integer userId,
                                                  String userName) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actSingleModel.deleteActSingle(actSingleId, userId, userName));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IActSingleService][deleteActSingle]删除单品立减活动时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActSingleService][deleteActSingle]删除单品立减活动时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<ActSingle>> getActSingles(Map<String, String> queryMap,
                                                        PagerInfo pager) {
        ServiceResult<List<ActSingle>> serviceResult = new ServiceResult<List<ActSingle>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(actSingleModel.getActSinglesCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(actSingleModel.getActSingles(queryMap, start, size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IActSingleService][getActSingles]根据条件取得单品立减活动时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActSingleService][getActSingles]param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[IActSingleService][getActSingles]根据条件取得单品立减活动时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<ActSingle> getEffectiveActSingle(Integer sellerId, Integer channel,
                                                          Integer productId) {
        ServiceResult<ActSingle> result = new ServiceResult<ActSingle>();
        try {
            result.setResult(actSingleModel.getEffectiveActSingle(sellerId, channel, productId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IActSingleService][getEffectiveActSingle]根据商家ID、渠道、商品ID取得单品立减活动时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActSingleService][getEffectiveActSingle]根据商家ID、渠道、商品ID取得单品立减活动时发生异常:", e);
        }
        return result;
    }

}