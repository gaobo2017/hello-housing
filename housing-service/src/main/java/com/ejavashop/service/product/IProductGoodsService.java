package com.ejavashop.service.product;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.product.ProductGoods;

public interface IProductGoodsService {

    /**
     * 根据商品ID取其下所有货品
     * @param productId
     * @return
     */
    ServiceResult<List<ProductGoods>> getGoodSByProductId(Integer productId);

    /**
    * 保存货品表
    * @param  productGoods
    * @return
    */
    ServiceResult<Boolean> saveProductGoods(ProductGoods productGoods);

    /**
    * 更新货品表
    * @param  productGoods
    * @return
    */
    ServiceResult<Boolean> updateProductGoods(ProductGoods productGoods);

    /**
    * 删除货品表
    * @param  id
    * @return
    */
    ServiceResult<Boolean> delProductGoods(Integer id);

    /**
    * 根据id取得货品表
    * @param productGoodsId
    * @return
    */
    ServiceResult<ProductGoods> getProductGoodsById(Integer productGoodsId);

    /**
     * 分页
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<ProductGoods>> pageProductGoods(Map<String, String> queryMap,
                                                       PagerInfo pager);

    ServiceResult<ProductGoods> getProductGoodsByCondition(Map<String, String> queryMap);

    /**
     * 以给定的货品信息批量更新这些货品<br>
     * @return
     */
    ServiceResult<Boolean> updateProductGoods(List<ProductGoods> productgoods);
}