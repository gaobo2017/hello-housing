package com.ejavashop.service.settlement;

import java.util.List;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.settlement.SettlementOp;

/**
 * 结算网单管理
 *                       
 * @Filename: ISettlementOpService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface ISettlementOpService {

    /**
     * 根据id取得结算网单表
     * @param  settlementOpId
     * @return
     */
    ServiceResult<SettlementOp> getSettlementOpById(Integer settlementOpId);

    /**
     * 保存结算网单表
     * @param  settlementOp
     * @return
     */
    ServiceResult<Integer> saveSettlementOp(SettlementOp settlementOp);

    /**
    * 更新结算网单表
    * @param  settlementOp
    * @return
    */
    ServiceResult<Integer> updateSettlementOp(SettlementOp settlementOp);

    /**
     * 根据订单号获取对应的结算网单
     * @param orderId
     * @return
     */
    ServiceResult<List<SettlementOp>> getSettlementOpByOId(Integer orderId);
}