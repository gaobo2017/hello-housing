package com.ejavashop.dao.shop.write.settlement;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.settlement.SettlementOp;

@Repository
public interface SettlementOpWriteDao {

    SettlementOp get(java.lang.Integer id);

    Integer insert(SettlementOp settlementOp);

    Integer update(SettlementOp settlementOp);

    /**
     * 根据结算周期和结算商家ID删除结算网单表
     * @param settleCycle
     * @param sellerId
     * @return
     */
    Integer deleteByCysleAndSellerId(@Param("settleCycle") String settleCycle,
                                     @Param("sellerId") Integer sellerId);
}