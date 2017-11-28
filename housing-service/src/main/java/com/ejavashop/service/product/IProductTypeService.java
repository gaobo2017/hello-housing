package com.ejavashop.service.product;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.product.ProductType;
import com.ejavashop.entity.product.ProductTypeAttr;

public interface IProductTypeService {
    /**
    * 保存商品类型表
    * @param  productType
    * @return
    */
    ServiceResult<Boolean> saveProductType(ProductType productType);

    /**
     * 保存商品类型
     * @param map key:type attr
     * @return
     */
    ServiceResult<Boolean> saveProductType(Map<String, Object> map);

    /**
    * 更新商品类型表
    * @param  productType
    * @return
    */
    ServiceResult<Boolean> updateProductType(ProductType productType);

    /**
     * 更新商品类型
     * @param map key:type attr
     * @return
     */
    ServiceResult<Boolean> updateProductType(Map<String, Object> map);

    /**
    * 删除商品类型表
    * @param  id
    * @return
    */
    ServiceResult<Boolean> delProductType(Integer id);

    /**
    * 根据id取得商品类型表
    * @param productTypeId
    * @return
    */
    ServiceResult<ProductType> getProductTypeById(Integer productTypeId);

    /**
     * 根据类型id获取属性信息
     * @param productTypeId
     * @return
     */
    ServiceResult<List<ProductTypeAttr>> getAttrByTypeId(Integer productTypeId);

    /**
    * 分页
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<ProductType>> pageProductType(Map<String, String> queryMap, PagerInfo pager);

}
