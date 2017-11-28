package com.ejavashop.service.impl.settlement;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.settlement.SettlementOp;
import com.ejavashop.model.settlement.SettlementOpModel;
import com.ejavashop.service.settlement.ISettlementOpService;

@Service(value = "settlementOpService")
public class SettlementOpServiceImpl implements ISettlementOpService {
    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
        .getLogger(SettlementOpServiceImpl.class);

    @Resource
    private SettlementOpModel              settlementOpModel;

    /**
    * 根据id取得结算网单表
    * @param  settlementOpId
    * @return
    * @see com.ejavashop.entity.settlement.service.SettlementOpService#getSettlementOpById(java.lang.Integer)
    */
    @Override
    public ServiceResult<SettlementOp> getSettlementOpById(Integer settlementOpId) {
        ServiceResult<SettlementOp> result = new ServiceResult<SettlementOp>();
        try {
            result.setResult(settlementOpModel.getSettlementOpById(settlementOpId));
        } catch (Exception e) {
            log.error("[SettlementOpServiceImpl][getSettlementOpById]根据id[" + settlementOpId
                      + "]取得结算网单表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("[SettlementOpServiceImpl][getSettlementOpById]根据id[" + settlementOpId
                              + "]取得结算网单表时出现未知异常");
        }
        return result;
    }

    /**
     * 保存结算网单表
     * @param  settlementOp
     * @return
     * @see com.ejavashop.entity.settlement.service.SettlementOpService#saveSettlementOp(SettlementOp)
     */
    @Override
    public ServiceResult<Integer> saveSettlementOp(SettlementOp settlementOp) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(settlementOpModel.saveSettlementOp(settlementOp));
        } catch (Exception e) {
            log.error("[SettlementOpServiceImpl][saveSettlementOp]保存结算网单表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("[SettlementOpServiceImpl][saveSettlementOp]保存结算网单表时出现未知异常");
        }
        return result;
    }

    /**
    * 更新结算网单表
    * @param  settlementOp
    * @return
    * @see com.ejavashop.entity.settlement.service.SettlementOpService#updateSettlementOp(SettlementOp)
    */
    @Override
    public ServiceResult<Integer> updateSettlementOp(SettlementOp settlementOp) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(settlementOpModel.updateSettlementOp(settlementOp));
        } catch (Exception e) {
            log.error("[SettlementOpServiceImpl][updateSettlementOp]更新结算网单表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("[SettlementOpServiceImpl][updateSettlementOp]更新结算网单表时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<List<SettlementOp>> getSettlementOpByOId(Integer orderId) {
        ServiceResult<List<SettlementOp>> serviceResult = new ServiceResult<List<SettlementOp>>();
        try {
            serviceResult.setResult(settlementOpModel.getSettlementOpByOId(orderId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
            log.error(
                "[SettlementOpServiceImpl][getSettlementOpByOId]根据订单号获取对应的结算网单:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SettlementOpServiceImpl][getSettlementOpByOId]根据订单号获取对应的结算网单:", e);
        }
        return serviceResult;

    }
}