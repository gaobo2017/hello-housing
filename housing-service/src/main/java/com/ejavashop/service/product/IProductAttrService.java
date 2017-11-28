package com.ejavashop.service.product;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.product.ProductAttr;

public interface IProductAttrService {
    /**
    * 保存商品对应属性表
    * @param  productAttr
    * @return
    */
    ServiceResult<Boolean> saveProductAttr(ProductAttr productAttr);

    /**
    * 更新商品对应属性表
    * @param  productAttr
    * @return
    */
    ServiceResult<Boolean> updateProductAttr(ProductAttr productAttr);

    /**
    * 删除商品对应属性表
    * @param  id
    * @return
    */
    ServiceResult<Boolean> delProductAttr(Integer id);

    /**
    * 根据id取得商品对应属性表
    * @param productAttrId
    * @return
    */
    ServiceResult<ProductAttr> getProductAttrById(Integer productAttrId);

    /**
     * 根据商品id查询商品属性
     * @param productId
     * @return
     */
    ServiceResult<List<ProductAttr>> getProductAttrByProductId(Integer productId);

    /**
    * 分页
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<ProductAttr>> pageProductAttr(Map<String, String> queryMap, PagerInfo pager);
}