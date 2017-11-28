package com.ejavashop.dao.promotion.write.group;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.group.ActGroup;

@Repository
public interface ActGroupWriteDao {

    ActGroup get(java.lang.Integer id);

    Integer insert(ActGroup actGroup);

    Integer update(ActGroup actGroup);

    int countByType(Integer id);

    /**
     * 团购分类停用或者启用的时候把团购分类下面的团购商品设置为停用或者启用
     * @param type 团购分类ID
     * @param typeState 团购分类的状态
     * @return
     */
    int updateByTypeState(@Param("type") Integer type, @Param("typeState") int typeState);

    /**
     * 更改团购的审核状态
     * @param id
     * @param state
     * @return
     */
    int updateState(@Param("id") Integer id, @Param("state") int state);

    /**
     * 更改团购的活动状态
     * @param id
     * @param activityState
     * @return
     */
    int updateActivityState(@Param("id") Integer id, @Param("activityState") int activityState);

    /**
     * 平台管理员审核，审核通过之后更改状态，并填写审核意见
     * @param id
     * @param state
     * @param auditOpinion
     * @return
     */
    int updateStateAndAuditOpinion(@Param("id") Integer id, @Param("state") int state,
                                   @Param("auditOpinion") String auditOpinion);

    /**
     * 修改活动商品的库存和销量，库存减1，销量加1，订单支付时调用<br>
     * `stock`= `stock` - saleNum<br>
     * `sale_num` = `sale_num` + saleNum<br>
     * @param id
     * @param saleNum
     * @return
     */
    Integer updateStockAndActualSales(@Param("id") Integer id, @Param("saleNum") Integer saleNum);

    /**
     * 修改活动商品的库存和销量，库存加1，销量减1，订单取消时调用<br>
     * `stock`= `stock` + saleNum<br>
     * `sale_num` = `sale_num` - saleNum<br>
     * @param id
     * @param saleNum
     * @return
     */
    Integer backStockAndActualSales(@Param("id") Integer id, @Param("saleNum") Integer saleNum);
}