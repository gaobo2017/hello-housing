package com.ejavashop.dao.promotion.write.flash;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.flash.ActFlashSaleProduct;

@Repository
public interface ActFlashSaleProductWriteDao {

    ActFlashSaleProduct get(java.lang.Integer id);

    Integer insert(ActFlashSaleProduct actFlashSaleProduct);

    Integer update(ActFlashSaleProduct actFlashSaleProduct);

    Integer delete(java.lang.Integer id);

    /**
     * 只修改活动商品状态、审核意见、修改者信息
     * @param actFlashSaleProduct
     * @return
     */
    Integer updateStatus(ActFlashSaleProduct actFlashSaleProduct);

    /**
     * 根据阶段ID、商家ID、商品ID获取活动商品信息
     * @param actFlashSaleStageId
     * @param sellerId
     * @param productId
     * @return
     */
    ActFlashSaleProduct getForSeller(@Param("actFlashSaleStageId") Integer actFlashSaleStageId,
                                     @Param("sellerId") Integer sellerId,
                                     @Param("productId") Integer productId);

    /**
     * 修改活动商品的库存和销量，库存减saleNum，销量加saleNum，订单支付时调用<br>
     * `stock`= `stock` - saleNum<br>
     * `actual_sales` = `actual_sales` + saleNum<br>
     * @param id
     * @return
     */
    Integer updateStockAndActualSales(@Param("id") Integer id, @Param("saleNum") Integer saleNum);

    /**
     * 修改活动商品的库存和销量，库存加saleNum，销量减saleNum，订单取消时调用<br>
     * `stock`= `stock` + saleNum<br>
     * `actual_sales` = `actual_sales` - saleNum<br>
     * @param id
     * @return
     */
    Integer backStockAndActualSales(@Param("id") Integer id, @Param("saleNum") Integer saleNum);

    /**
     * 根据阶段ID、商品ID，获取审核通过的活动商品信息
     * @param actFlashSaleStageId 阶段ID
     * @param productId 商品ID
     * @return
     */
    ActFlashSaleProduct getByStageIdAndProductId(@Param("actFlashSaleStageId") Integer actFlashSaleStageId,
                                                 @Param("productId") Integer productId);
}