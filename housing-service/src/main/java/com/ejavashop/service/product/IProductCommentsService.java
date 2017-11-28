package com.ejavashop.service.product;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.product.ProductComments;

/**
 * 商品评论管理接口
 * 
 * @Filename: IProductCommentsService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface IProductCommentsService {

    /**
     * 根据id取得商品评论管理
     * @param  productCommentsId
     * @return
     */
    ServiceResult<ProductComments> getProductCommentsById(Integer productCommentsId);

    /**
     * 审核商品评论
     * @param id
     * @param state
     * @return
     */
    public ServiceResult<Boolean> auditProductComments(Integer id, Integer state);

    /**
     * 根据查询条件查询商品评论
     * @param queryMap
     * @param pager
     * @return
     */
    public ServiceResult<List<ProductComments>> getProductComments(Map<String, String> queryMap,
                                                                   PagerInfo pager);

    /**
     * 根据查询条件查询商品评论，附加查询了商品名称和所属商家名称
     * @param queryMap
     * @param pager
     * @return
     */
    public ServiceResult<List<ProductComments>> getProductCommentsWithInfo(Map<String, String> queryMap,
                                                                           PagerInfo pager);

    /**
     * 根据订单编号及产品id,货品ID   取得商品评论  1个订单可以有多个产品评论
     * @param orderSn
     * @param productId
     * @param request
     * @return
     */
    public ServiceResult<ProductComments> getProductCommentsByOrderSn(String orderSn,
                                                                      String productId,
                                                                      String productGoodsId,
                                                                      Integer memberId);

    /**
     * 保存商品评论管理
     * @param productComments
     * @param request
     * @return
     */
    ServiceResult<Boolean> saveProductComments(ProductComments productComments);

    /**
    * 更新商品评论管理
    * @param  productComments
    * @return
    */
    ServiceResult<Integer> updateProductComments(ProductComments productComments);

    /**
     * 根据查询条件取所有的评论 单品页使用 
     * @param request
     * @param pager
     * @return
     */
    public ServiceResult<List<ProductComments>> getCommentsByCondition(Map<String, Object> queryMap,
                                                                       PagerInfo pager);
}