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
import com.ejavashop.entity.promotion.full.ActFull;
import com.ejavashop.model.promotion.ActFullModel;
import com.ejavashop.service.promotion.IActFullService;

@Service(value = "actFullService")
public class ActFullServiceImpl implements IActFullService {
    private static Logger log = LogManager.getLogger(ActFullServiceImpl.class);

    @Resource
    private ActFullModel  actFullModel;

    @Override
    public ServiceResult<ActFull> getActFullById(Integer actFullId) {
        ServiceResult<ActFull> result = new ServiceResult<ActFull>();
        try {
            result.setResult(actFullModel.getActFullById(actFullId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IActFullService][getActFullById]根据id[" + actFullId + "]取得满减活动时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActFullService][getActFullById]根据id[" + actFullId + "]取得满减活动时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> saveActFull(ActFull actFull) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actFullModel.saveActFull(actFull));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IActFullService][saveActFull]保存满减活动时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActFullService][saveActFull]保存满减活动时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> updateActFull(ActFull actFull) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actFullModel.updateActFull(actFull));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IActFullService][updateActFull]更新满减活动时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActFullService][updateActFull]更新满减活动时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> updateActFullStatus(ActFull actFull) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actFullModel.updateActFullStatus(actFull));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IActFullService][updateActFullStatus]更新满减活动状态时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActFullService][updateActFullStatus]更新满减活动状态时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> deleteActFull(Integer actFullId, Integer userId,
                                                String userName) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actFullModel.deleteActFull(actFullId, userId, userName));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IActFullService][deleteActFull]删除满减活动时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActFullService][deleteActFull]删除满减活动时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<ActFull>> getActFulls(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<ActFull>> serviceResult = new ServiceResult<List<ActFull>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(actFullModel.getActFullsCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(actFullModel.getActFulls(queryMap, start, size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IActFullService][getActFulls]根据条件取得满减活动时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActFullService][getActFulls]param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[IActFullService][getActFulls]根据条件取得满减活动时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<ActFull> getEffectiveActFull(Integer sellerId, Integer channel) {
        ServiceResult<ActFull> result = new ServiceResult<ActFull>();
        try {
            result.setResult(actFullModel.getEffectiveActFull(sellerId, channel));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error(
                "[IActFullService][getEffectiveActFull]根据商家ID和渠道取得满减活动时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActFullService][getEffectiveActFull]根据商家ID和渠道取得满减活动时发生异常:", e);
        }
        return result;
    }

}