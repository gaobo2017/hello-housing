package com.ejavashop.model.settlement;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.dao.shop.read.settlement.SettlementOpReadDao;
import com.ejavashop.dao.shop.write.settlement.SettlementOpWriteDao;
import com.ejavashop.entity.settlement.SettlementOp;

@Component
public class SettlementOpModel {

    @Resource
    private SettlementOpWriteDao settlementOpWriteDao;
    @Resource
    private SettlementOpReadDao  settlementOpReadDao;

    /**
     * 根据id取得结算网单表
     * @param  settlementOpId
     * @return
     */
    public SettlementOp getSettlementOpById(Integer settlementOpId) {
        return settlementOpWriteDao.get(settlementOpId);
    }

    /**
     * 保存结算网单表
     * @param  settlementOp
     * @return
     */
    public Integer saveSettlementOp(SettlementOp settlementOp) {
        return settlementOpWriteDao.insert(settlementOp);
    }

    /**
    * 更新结算网单表
    * @param  settlementOp
    * @return
    */
    public Integer updateSettlementOp(SettlementOp settlementOp) {
        return settlementOpWriteDao.update(settlementOp);
    }

    /**
     * 根据订单号获取结算网单
     * @param orderId
     * @return
     */
    public List<SettlementOp> getSettlementOpByOId(Integer orderId) {
        return settlementOpReadDao.getByOrderId(orderId);
    }

    //     private void dbConstrains(SettlementOp settlementOp) {
    //		settlementOp.setOrdersSn(StringUtil.dbSafeString(settlementOp.getOrdersSn(), false, 100));
    //		settlementOp.setSpecInfo(StringUtil.dbSafeString(settlementOp.getSpecInfo(), true, 255));
    //		settlementOp.setProductName(StringUtil.dbSafeString(settlementOp.getProductName(), false, 255));
    //		settlementOp.setProductSku(StringUtil.dbSafeString(settlementOp.getProductSku(), false, 60));
    //		settlementOp.setLogisticsName(StringUtil.dbSafeString(settlementOp.getLogisticsName(), true, 100));
    //		settlementOp.setLogisticsNumber(StringUtil.dbSafeString(settlementOp.getLogisticsNumber(), true, 100));
    //		settlementOp.setSystemRemark(StringUtil.dbSafeString(settlementOp.getSystemRemark(), true, 65535));
    //		settlementOp.setSettleCycle(StringUtil.dbSafeString(settlementOp.getSettleCycle(), false, 20));
    //		settlementOp.setProductCateName(StringUtil.dbSafeString(settlementOp.getProductCateName(), true, 20));
    //     }
}