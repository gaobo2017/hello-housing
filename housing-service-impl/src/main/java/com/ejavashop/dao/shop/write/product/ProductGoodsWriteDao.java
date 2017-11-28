package com.ejavashop.dao.shop.write.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.ProductGoods;

@Repository
public interface ProductGoodsWriteDao {

    /**
     * 修改货品库存<br>
     * set `product_stock`= `product_stock` - #{number},
     * @param id 货品ID
     * @param number 修改的数量（如果是负数，则表示可能由于退货等原因还原库存）
     * @return
     */
    Integer updateStock(@Param("id") Integer id, @Param("number") Integer number);

    /**
     * 修改货品销量<br>
     * set `actual_sales`= `actual_sales` + #{number},
     * @param id 货品ID
     * @param number 修改的数量（如果是负数，则表示可能由于取消订单等原因还原销量）
     * @return
     */
    Integer updateActualSales(@Param("id") Integer id, @Param("number") Integer number);

    ProductGoods getByProductIdAndAttrId(@Param("productId") Integer productId,
                                         @Param("attrId") String attrId);

    ProductGoods getByProductId(@Param("productId") Integer productId);

    Integer update(ProductGoods productGoods);

    Integer insert(ProductGoods productGoods);

    Integer del(Integer id);

    ProductGoods get(Integer id);

    Integer count(@Param("param1") Map<String, String> queryMap);

    List<ProductGoods> page(@Param("param1") Map<String, String> queryMap,
                            @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 
     * @param queryMap
     * @return
     */
    ProductGoods getByCondition(@Param("param1") Map<String, String> queryMap);

    Integer deleteByProductId(Integer proid);

    Integer batchUpdate(List<ProductGoods> pgs);

}
