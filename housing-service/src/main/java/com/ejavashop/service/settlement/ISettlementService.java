package com.ejavashop.service.settlement;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.settlement.Settlement;

/**
 * 结算管理
 *                       
 * @Filename: ISettlementService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface ISettlementService {

    /**
     * 商家结算账单生成定时任务<br>
     * <li>查询所有商家，每个商家每个结算周期生成一条结算账单
     * <li>计算周期内商家所有的订单金额合计
     * <li>计算所有订单下网单的佣金
     * <li>计算周期内发生的退货退款（当前周期结算的订单如果在下个结算周期才退款，退款结算在下个周期计算）
     * <li>每个商家一个事务，某个商家结算时发生错误不影响其他结算
     * @return
     */
    ServiceResult<Boolean> jobSettlement();

    /**
     * 根据id取得结算表
     * @param  settlementId
     * @return
     */
    ServiceResult<Settlement> getSettlementById(Integer settlementId);

    /**
     * 保存结算表
     * @param  settlement
     * @return
     */
    ServiceResult<Integer> saveSettlement(Settlement settlement);

    /**
     * 更新结算表
     * @param settlement
     * @return
     */
    ServiceResult<Boolean> updateSettlement(Settlement settlement);

    /**
     * 根据条件取得结算账单
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<Settlement>> getSettlements(Map<String, String> queryMap, PagerInfo pager);
}