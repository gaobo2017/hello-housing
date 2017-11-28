package com.ejavashop.service.product;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.product.ProductAsk;

/**
 * 商品咨询管理接口
 * 
 * @Filename: IProductAskService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface IProductAskService {

    /**
     * 根据id取得商品咨询管理
     * @param  productAskId
     * @return
     */
    ServiceResult<ProductAsk> getProductAskById(Integer productAskId);

    /**
    * 更新商品咨询管理
    * @param  productAsk
    * @return
    */
    ServiceResult<Boolean> updateProductAsk(ProductAsk productAsk);

    /**
     * 根据查询条件取所有的咨询，只返回评论表内容
     * @param queryMap
     * @param pager
     * @return
     */
    public ServiceResult<List<ProductAsk>> getProductAsks(Map<String, String> queryMap,
                                                          PagerInfo pager);

    /**
     * 根据查询条件取所有的咨询，一起返回该商品的名称、所属商家名称
     * @param queryMap
     * @param pager
     * @return
     */
    public ServiceResult<List<ProductAsk>> getProductAsksWithInfo(Map<String, String> queryMap,
                                                                  PagerInfo pager);

    /**
     * 根据产品及用户id取得商品咨询
     * @param productId
     * @param request
     * @return
     */
    ServiceResult<ProductAsk> getProductAskByProductId(String productId, Integer memberId);

    /**
     * 保存商品咨询管理
     * @param productAsk
     * @param request
     * @return
     */
    ServiceResult<ProductAsk> saveProductAsk(ProductAsk productAsk, Member member);

}