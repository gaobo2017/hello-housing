package com.ejavashop.service.product;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.product.ProductTypeAttr;
import com.ejavashop.vo.product.ProductTypeAttrVO;

public interface IProductTypeAttrService {
    /**
    * 保存商品分类属性表
    * @param  productTypeAttr
    * @return
    */
    ServiceResult<Boolean> saveProductTypeAttr(ProductTypeAttr productTypeAttr);

    /**
    * 更新商品分类属性表
    * @param  productTypeAttr
    * @return
    */
    ServiceResult<Boolean> updateProductTypeAttr(ProductTypeAttr productTypeAttr);

    /**
    * 删除商品分类属性表
    * @param  id
    * @return
    */
    ServiceResult<Boolean> delProductTypeAttr(Integer id);

    /**
    * 根据id取得商品分类属性表
    * @param productTypeAttrId
    * @return
    */
    ServiceResult<ProductTypeAttr> getProductTypeAttrById(Integer productTypeAttrId);

    /**
     * 根据商品类型id查询商品类型属性
      * @param productTypeId
     * @return
     */
    ServiceResult<List<ProductTypeAttr>> getProductTypeAttrByTypeId(Integer productTypeId);

    /**
    * 分页
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<ProductTypeAttrVO>> pageProductTypeAttr(Map<String, String> queryMap,
                                                               PagerInfo pager);
}