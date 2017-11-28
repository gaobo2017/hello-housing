package com.ejavashop.service.impl.settlement;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.settlement.Settlement;
import com.ejavashop.model.settlement.SettlementModel;
import com.ejavashop.service.settlement.ISettlementService;

@Service(value = "settlementService")
public class SettlementServiceImpl implements ISettlementService {
    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
        .getLogger(SettlementOpServiceImpl.class);

    @Resource
    private SettlementModel                settlementModel;

    @Override
    public ServiceResult<Boolean> jobSettlement() {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(settlementModel.jobSettlement());
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[SettlementService][jobSettlement]商家结算账单生成定时任务时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[SettlementService][jobSettlement]商家结算账单生成定时任务时出现未知异常：", e);
        }
        return serviceResult;
    }

    /**
    * 根据id取得结算表
    * @param  settlementId
    * @return
    */
    @Override
    public ServiceResult<Settlement> getSettlementById(Integer settlementId) {
        ServiceResult<Settlement> serviceResult = new ServiceResult<Settlement>();
        try {
            serviceResult.setResult(settlementModel.getSettlementById(settlementId));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[SettlementService][getSettlementById]根据id[" + settlementId + "]取得结算表时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error(
                "[SettlementService][getSettlementById]根据id[" + settlementId + "]取得结算表时出现未知异常：", e);
        }
        return serviceResult;
    }

    /**
     * 保存结算表
     * @param  settlement
     * @return
     */
    @Override
    public ServiceResult<Integer> saveSettlement(Settlement settlement) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setResult(settlementModel.saveSettlement(settlement));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[SettlementService][saveSettlement]保存结算表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[SettlementService][saveSettlement]保存结算表时出现未知异常：", e);
        }
        return serviceResult;
    }

    /**
    * 更新结算表
    * @param  settlement
    * @return
    */
    @Override
    public ServiceResult<Boolean> updateSettlement(Settlement settlement) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(settlementModel.updateSettlement(settlement));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[SettlementService][updateSettlement]更新结算表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[SettlementService][updateSettlement]更新结算表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<Settlement>> getSettlements(Map<String, String> queryMap,
                                                          PagerInfo pager) {
        ServiceResult<List<Settlement>> serviceResult = new ServiceResult<List<Settlement>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(settlementModel.getSettlementsCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(settlementModel.getSettlements(queryMap, start, size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[SettlementService][getSettlements]查询结算表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[SettlementService][getSettlements]param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[SettlementService][getSettlements]查询结算信息发生异常:", e);
        }
        return serviceResult;
    }

}